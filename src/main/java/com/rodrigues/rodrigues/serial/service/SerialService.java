package com.rodrigues.rodrigues.serial.service;

import javax.comm.SerialPort;

import com.rodrigues.rodrigues.serial.dao.ReadGenericData;
import com.rodrigues.rodrigues.serial.dao.WriteGenericData;
import com.rodrigues.rodrigues.serial.resources.PortComResurce;

public class SerialService{
	private PortComResurce resurce = new PortComResurce();
	private WriteGenericData write = new WriteGenericData();
	private ReadGenericData read = new ReadGenericData();
		
	


	
	public void writeDataAlfa(byte[] bufferWrite) {
		//serialPort.close();
	}
	

	public SerialPort enablePortCom(String porta, int baud, int timeout, int stopBits) {
		SerialPort serial;
		if(resurce.getPortIdentifier(porta)) {
			serial = resurce.openPort(baud, timeout, stopBits);
		}else return null;
		
		return serial;
	}

	
	public void writeData(byte[] bufferWrite, SerialPort serial, int BufferSize) {
		write.writeData(bufferWrite, serial, BufferSize);
		
	}

	
	public byte[] readData(SerialPort serial, int bufferSize) {
		return read.readData(serial, bufferSize);
	}

}