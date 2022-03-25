package com.rodrigues.rodrigues.serial.dao;

import com.rodrigues.rodrigues.serial.utilitary.CalculatorCRC;

public class WriteSetPoints {
	private byte[] bufferWrite = new byte[27];
	
	public byte[] seletroraWrite(int endereco) {
		int registradorInicial = 30;
		int totalRegistradores = 9;
		int bytesEsperados = 18;
		
		int seletoraMs = 4;
		int seletoraLs = 64;
		
		bufferWrite[0] = (byte) endereco;
		bufferWrite[1] = (byte) 16;
		
		bufferWrite[2] = (byte) 0;
		bufferWrite[3] = (byte) registradorInicial;
		
		bufferWrite[4] = (byte) 0;
		bufferWrite[5] = (byte) totalRegistradores;
		
		bufferWrite[6] = (byte) bytesEsperados;		
		
		bufferWrite[7] = (byte) seletoraMs;		
		bufferWrite[8] = (byte) seletoraLs;
		
		bufferWrite[9] =  0;		bufferWrite[10] =  0;		bufferWrite[11] =  0;		bufferWrite[12] =  0;
		bufferWrite[13] =  0;		bufferWrite[14] =  0;		bufferWrite[15] =  0;		bufferWrite[16] =  0;
		bufferWrite[17] =  0;		bufferWrite[18] =  0;		bufferWrite[19] =  0;		bufferWrite[20] =  0;
		bufferWrite[21] =  0;		bufferWrite[22] =  0;		bufferWrite[23] =  0;		bufferWrite[24] =  0;
		
		int[] crc = CalculatorCRC.calculateCRC(bufferWrite, 0, 6);
		bufferWrite[25] = (byte) crc[0];
		bufferWrite[26] = (byte) crc[1];
		
		return bufferWrite;
	}
	
	public byte[] Write(int endereco, byte[] sp_1, byte[] sp_2, byte[] sp_3, byte[] vazia) {
		int registradorInicial = 30;
		int totalRegistradores = 9;
		int bytesEsperados = 18;
		
		bufferWrite[0] = (byte) endereco;
		bufferWrite[1] = (byte) 16;
		
		bufferWrite[2] = (byte) 0;
		bufferWrite[3] = (byte) registradorInicial;
		
		bufferWrite[4] = (byte) 0;
		bufferWrite[5] = (byte) totalRegistradores;
		
		bufferWrite[6] = (byte) bytesEsperados;		
		
		bufferWrite[7] = (byte) 0;		
		bufferWrite[8] = (byte) 0;
		
		bufferWrite[9] = sp_1[3];		bufferWrite[10] = sp_1[2];	bufferWrite[11] = sp_1[1];		bufferWrite[12] = sp_1[0];
		bufferWrite[13] = sp_2[3]; 		bufferWrite[14] = sp_2[2];	bufferWrite[15] = sp_2[1];		bufferWrite[16] = sp_2[0];
		bufferWrite[17] = sp_3[3];		bufferWrite[18] = sp_3[2];	bufferWrite[19] = sp_3[1];		bufferWrite[20] = sp_3[0];
		bufferWrite[21] = vazia[3];		bufferWrite[22] = vazia[2];	bufferWrite[23] = vazia[1];		bufferWrite[24] = vazia[0];
		
		int[] crc = CalculatorCRC.calculateCRC(bufferWrite, 0, 25);
		bufferWrite[25] = (byte) crc[0];
		bufferWrite[26] = (byte) crc[1];
		
		return bufferWrite;
	}
}