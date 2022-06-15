package com.rodrigues.rodrigues.serial.dao;

import java.io.IOException;
import java.io.OutputStream;

import javax.comm.SerialPort;

import com.rodrigues.rodrigues.MainApp;

public class WriteGenericData {	
	private OutputStream saida;
	private Integer threadSleep = MainApp.threadSleep;
	
	public void writeData(byte[] bufferWrite, SerialPort serialPort, int BufferSize) throws NullPointerException ,IOException {

		//Padrao do bufer = 8
		//Indicadores alfa = 27
		try {
			Thread.sleep(threadSleep);
			serialPort.setOutputBufferSize(BufferSize);
			saida = serialPort.getOutputStream();
			saida.write(bufferWrite);
			//Thread.sleep(0);
			saida.flush();
			//saida.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
