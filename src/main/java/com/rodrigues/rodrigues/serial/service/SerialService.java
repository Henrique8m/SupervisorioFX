package com.rodrigues.rodrigues.serial.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

public class SerialService{
	
	public SerialService() {}
	
	private final byte[] bufferRead = new byte[7];

	private String display;
	
	private String portName;
	private int baudRate;
	private int timeout;

	private CommPortIdentifier cp;
	private SerialPort serialPort;

	private OutputStream saida;
	private InputStream entrada;

	private Timer timer = new Timer(true);
	private TimerTask timerTask;

	public String getDisplay() {
		return display;
	}

	
	
	@SuppressWarnings({ "unchecked", "static-access" })
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

	public byte[] readData(){
		try {
				timerTask = new TimerTask() {
				@Override
				public void run() {serialPort.close();}}; timer.schedule(timerTask,100);
				serialPort.setInputBufferSize(7);
				entrada = serialPort.getInputStream();
				entrada.read(bufferRead);
	
			} catch (IOException e) {
				System.out.println("Erro na leitura dos dados! STATUS: " + e.getMessage());
				this.display = "Error";
				timerTask.cancel();
			}
		
		serialPort.close();
		timerTask.cancel();
		return bufferRead;
	}

	
	public void setPortName(String portName) {
		this.portName = portName;
	}

	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}