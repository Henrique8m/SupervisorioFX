package com.rodrigues.rodrigues.serial.resources;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

public class PortComResurce {

	
	private CommPortIdentifier cp;
	private SerialPort serialPort;
	
	public Boolean getPortIdentifier(String portName) throws NoSuchPortException {
		
	
			if(cp==null) cp = CommPortIdentifier.getPortIdentifier(portName);
			return true;
		
	}

	@SuppressWarnings("static-access")
	public SerialPort openPort(int baudRate, int timeout, int stopBits) {

		try {
			serialPort = (SerialPort) cp.open("SerialService", timeout);
			serialPort.setSerialPortParams(baudRate, serialPort.DATABITS_8, stopBits, serialPort.PARITY_NONE);
		}
		catch (PortInUseException e) {
			System.out.println("Erro ao abrir a porta! STATUS: " + e.getMessage());
			return null;
		}
		catch (UnsupportedCommOperationException e) {
			System.out.println("Erro com os parametros da porta! STATUS: " + e.getMessage());
			return null;
		}
	return serialPort;
	}
	
}
