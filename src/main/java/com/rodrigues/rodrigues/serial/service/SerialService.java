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
	
	private byte[] bufferRead = new byte[7];
	private byte[] bufferReadAlfa = new byte[17];


	private String display;
	
	private String portName;
	private int baudRate;
	private int timeout;
	private int stopBits;
	
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
			serialPort.setSerialPortParams(baudRate, serialPort.DATABITS_8, stopBits, serialPort.PARITY_NONE);
		}
		catch (PortInUseException e) {System.out.println("Erro ao abrir a porta! STATUS: " + e.getMessage());}
		catch (UnsupportedCommOperationException e) {System.out.println("Erro com os parametros da porta! STATUS: " + e.getMessage());}
	}

	public void writeData(byte[] bufferWrite) {

		try {
			Thread.sleep(200);
			serialPort.setOutputBufferSize(8);
			saida = serialPort.getOutputStream();
			saida.write(bufferWrite);
			Thread.sleep(10);
			saida.flush();
			saida.close();
		} catch (IOException e) {System.out.println("Erro ao enviar os dados! STATUS: ");e.printStackTrace();} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void writeDataAlfa(byte[] bufferWrite) {

		try {
			serialPort.setOutputBufferSize(27);
			saida = serialPort.getOutputStream();
			saida.write(bufferWrite);
			Thread.sleep(10);
			saida.flush();
			saida.close();
			serialPort.close();
		} catch (IOException e) {System.out.println("Erro ao enviar os dados! STATUS: ");e.printStackTrace();} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public byte[] readData(){
		try {
			serialPort.setInputBufferSize(7);
			entrada = serialPort.getInputStream();
				
			timerTask = new TimerTask() {
				@Override
				public void run() {
					serialPort.close(); 
					}}; 
			timer.schedule(timerTask,50);
			entrada.read(bufferRead);
			
			timerTask.cancel();	
			serialPort.close();

			} catch (IOException e) {				
				return null;
			} catch(NullPointerException e) {
				return null;
			}
		
		return bufferRead;
	}
	
	public byte[] readDataAlfa(){
		try {
			serialPort.setInputBufferSize(17);
			entrada = serialPort.getInputStream();
				
			timerTask = new TimerTask() {
				@Override
				public void run() {
					serialPort.close(); 
					}}; 
			timer.schedule(timerTask,50);

			entrada.read(bufferReadAlfa);

			
			timerTask.cancel();	
			serialPort.close();

			} catch (IOException e) {				
				return null;
			} catch(NullPointerException e) {
				return null;
			}

		return bufferReadAlfa;
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
	
	public void setStopBits(int stopBits) {
		this.stopBits = stopBits;
	}
}