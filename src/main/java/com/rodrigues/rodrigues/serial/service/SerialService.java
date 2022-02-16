package com.rodrigues.rodrigues.serial.service;

import javax.comm.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class SerialService{

	private final byte[] bufferRead = new byte[7];

	private String display;
	private final String portName;

	private final int baudRate;
	private final int timeout;

	private CommPortIdentifier cp;
	private SerialPort serialPort;

	private OutputStream saida;
	private InputStream entrada;

	private Timer timer = new Timer(true);
	private TimerTask timerTask;

	public String getDisplay() {
		return display;
	}

	public SerialService(String p, int b, int t) {
		this.portName = p;
		this.baudRate = b;
		this.timeout = t;

	}
	
	public Enumeration<CommPortIdentifier> getPortIdentifiers(){
		return cp.getPortIdentifiers();
	}

	public Boolean getPortIdentifier() {
		
		try {
			if(cp==null) cp = CommPortIdentifier.getPortIdentifier(portName);
			return true;
		} catch (NoSuchPortException e) {
			System.out.println("Porta não existe! STATUS: " + e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("static-access")
	public void openPort() {

		try {
			serialPort = (SerialPort) cp.open("SerialService", timeout);
			serialPort.setSerialPortParams(baudRate, serialPort.DATABITS_8, serialPort.STOPBITS_2, serialPort.PARITY_NONE);
		}
		catch (PortInUseException e) {System.out.println("Erro ao abrir a porta! STATUS: " + e.getMessage());}
		catch (UnsupportedCommOperationException e) {System.out.println("Erro com os parametros da porta! STATUS: " + e.getMessage());}
	}

	public void writeData(byte[] bufferWrite) {

		try {
			serialPort.setOutputBufferSize(8);
			saida = serialPort.getOutputStream();
			saida.write(bufferWrite);
			Thread.sleep(200);
			saida.flush();
			saida.close();
		} catch (IOException e) {System.out.println("Erro ao enviar os dados! STATUS: ");e.printStackTrace();} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Boolean readData(){
		try {
				timerTask = new TimerTask() {
				@Override
				public void run() {serialPort.close();}}; timer.schedule(timerTask,100);
			serialPort.setInputBufferSize(7);
			entrada = serialPort.getInputStream();
			entrada.read(bufferRead);
			formatData();

		} catch (IOException e) {
			System.out.println("Erro na leitura dos dados! STATUS: ");
			timerTask.cancel();
		}
		serialPort.close();
		timerTask.cancel();
		return true;
	}

	public void formatData() {
		if (Byte.toUnsignedInt(this.bufferRead[3]) > 0) {
			try {
				this.display = Integer
						.toString(Byte.toUnsignedInt(bufferRead[4]) + (Byte.toUnsignedInt(bufferRead[3]) * 256));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			this.display = Integer.toString(Byte.toUnsignedInt(this.bufferRead[4]));
		}
	}
}