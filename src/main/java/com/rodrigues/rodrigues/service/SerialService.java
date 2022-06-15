package com.rodrigues.rodrigues.service;

import java.io.IOException;

import javax.comm.NoSuchPortException;
import javax.comm.SerialPort;

import com.rodrigues.rodrigues.serial.dao.ReadGenericData;
import com.rodrigues.rodrigues.serial.dao.WriteGenericData;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.resources.PortComResurce;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

public class SerialService{
	private PortComResurce resurce = new PortComResurce();
	private WriteGenericData write = new WriteGenericData();
	private ReadGenericData read = new ReadGenericData();
	private SerialProperties serialProperties;
		
	


	
	public void writeDataAlfa(byte[] bufferWrite) {
		//serialPort.close();
	}
	

	public SerialPort enablePortCom() throws NoSuchPortException {
		if(serialProperties==null)serialProperties = DependencyInjection.getSerialProperties();
		SerialPort serial;
		if(resurce.getPortIdentifier(serialProperties.getPorta())) {
			serial = resurce.openPort(serialProperties.getBaud(), serialProperties.getTimeout(), serialProperties.getStopBits());
		}else return null;
		
		return serial;
	}

	
	public void writeData(byte[] bufferWrite, SerialPort serial, int BufferSize) throws NullPointerException, IOException{
		write.writeData(bufferWrite, serial, BufferSize);
		
	}

	
	public byte[] readData(SerialPort serial, int bufferSize) {
		return read.readData(serial, bufferSize);
	}
	
	public void closePort() {
		
	}
	
}