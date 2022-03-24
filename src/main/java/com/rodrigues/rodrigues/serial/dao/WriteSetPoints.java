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
		
		//SetPoint 1 e 4
		//primeiro vezes o segundo vezes o terceiro, vezes o quarto
		int DsWord1_SP_1_e_4 = 0;
		int LsWord1_SP_1_e_4 = 0;
		int DsWord0_SP_1_e_4 = 0;
		int LsWord0_SP_1_e_4 = 0;
		
		//SetPoint 2 e 5
		int DsWord1_SP_2_e_5 = 0;
		int LsWord1_SP_2_e_5 = 0;
		int DsWord0_SP_2_e_5 = 0;
		int LsWord0_SP_2_e_5 = 0;
		
		//SetPoint 3 e 6
		int DsWord1_SP_3_e_6 = 0;
		int LsWord1_SP_3_e_6 = 0;
		int DsWord0_SP_3_e_6 = 0;
		int LsWord0_SP_3_e_6 = 0;
		
		//SetPoint Vazia e 7
		int DsWord1_SP_V_e_7 = 0;
		int LsWord1_SP_V_e_7 = 0;
		int DsWord0_SP_V_e_7 = 0;
		int LsWord0_SP_V_e_7 = 0;
		
		
		bufferWrite[0] = (byte) endereco;
		bufferWrite[1] = (byte) 16; // Metodo de escrita
		
		bufferWrite[2] = (byte) 0;
		bufferWrite[3] = (byte) registradorInicial;
		
		bufferWrite[4] = (byte) 0;
		bufferWrite[5] = (byte) totalRegistradores;
		
		bufferWrite[6] = (byte) bytesEsperados;		
		
		//Seletora de SetPoints, tambem abilita a escrita
		bufferWrite[7] = (byte) seletoraMs;		
		bufferWrite[8] = (byte) seletoraLs;
		
		//Escrita nos SerPoints
		//1 e 4
		bufferWrite[9] = (byte) DsWord1_SP_1_e_4;		
		bufferWrite[10] = (byte) LsWord1_SP_1_e_4;
		bufferWrite[11] = (byte) DsWord0_SP_1_e_4;		
		bufferWrite[12] = (byte) LsWord0_SP_1_e_4;
		
		//2 e 5
		bufferWrite[13] = (byte) DsWord1_SP_2_e_5;		
		bufferWrite[14] = (byte) LsWord1_SP_2_e_5;
		bufferWrite[15] = (byte) DsWord0_SP_2_e_5;		
		bufferWrite[16] = (byte) LsWord0_SP_2_e_5;
		
		//3 e 6
		bufferWrite[17] = (byte) DsWord1_SP_3_e_6;		
		bufferWrite[18] = (byte) LsWord1_SP_3_e_6;
		bufferWrite[19] = (byte) DsWord0_SP_3_e_6;		
		bufferWrite[20] = (byte) LsWord0_SP_3_e_6;
		
		//Vazia e 7
		bufferWrite[21] = (byte) DsWord1_SP_V_e_7;		
		bufferWrite[22] = (byte) LsWord1_SP_V_e_7;
		bufferWrite[23] = (byte) DsWord0_SP_V_e_7;		
		bufferWrite[24] = (byte) LsWord0_SP_V_e_7;
		
		int[] crc = CalculatorCRC.calculateCRC(bufferWrite, 0, 6); //calcular o crc
		bufferWrite[25] = (byte) crc[0];
		bufferWrite[26] = (byte) crc[1];
		
		return bufferWrite;
	}
	
	public byte[] Write(int endereco) {
		int registradorInicial = 30;
		int totalRegistradores = 9;
		int bytesEsperados = 18;
		
		int seletoraMs = 0;
		int seletoraLs = 0;
		
		//SetPoint 1 e 4
		//primeiro vezes o segundo vezes o terceiro, vezes o quarto
		int DsWord1_SP_1_e_4 = 0;
		int LsWord1_SP_1_e_4 = 0;
		int DsWord0_SP_1_e_4 = 0;
		int LsWord0_SP_1_e_4 = 110;
		
		//SetPoint 2 e 5
		int DsWord1_SP_2_e_5 = 0;
		int LsWord1_SP_2_e_5 = 0;
		int DsWord0_SP_2_e_5 = 0;
		int LsWord0_SP_2_e_5 = 210;
		
		//SetPoint 3 e 6
		int DsWord1_SP_3_e_6 = 0;
		int LsWord1_SP_3_e_6 = 0;
		int DsWord0_SP_3_e_6 = 1;
		int LsWord0_SP_3_e_6 = 54;
		
		//SetPoint Vazia e 7
		int DsWord1_SP_V_e_7 = 0;
		int LsWord1_SP_V_e_7 = 0;
		int DsWord0_SP_V_e_7 = 0;
		int LsWord0_SP_V_e_7 = 123;
		
		
		bufferWrite[0] = (byte) endereco;
		bufferWrite[1] = (byte) 16; // Metodo de escrita
		
		bufferWrite[2] = (byte) 0;
		bufferWrite[3] = (byte) registradorInicial;
		
		bufferWrite[4] = (byte) 0;
		bufferWrite[5] = (byte) totalRegistradores;
		
		bufferWrite[6] = (byte) bytesEsperados;		
		
		//Seletora de SetPoints, tambem abilita a escrita
		bufferWrite[7] = (byte) seletoraMs;		
		bufferWrite[8] = (byte) seletoraLs;
		
		//Escrita nos SerPoints
		//1 e 4
		bufferWrite[9] = (byte) DsWord1_SP_1_e_4;		
		bufferWrite[10] = (byte) LsWord1_SP_1_e_4;
		bufferWrite[11] = (byte) DsWord0_SP_1_e_4;		
		bufferWrite[12] = (byte) LsWord0_SP_1_e_4;
		
		//2 e 5
		bufferWrite[13] = (byte) DsWord1_SP_2_e_5;		
		bufferWrite[14] = (byte) LsWord1_SP_2_e_5;
		bufferWrite[15] = (byte) DsWord0_SP_2_e_5;		
		bufferWrite[16] = (byte) LsWord0_SP_2_e_5;
		
		//3 e 6
		bufferWrite[17] = (byte) DsWord1_SP_3_e_6;		
		bufferWrite[18] = (byte) LsWord1_SP_3_e_6;
		bufferWrite[19] = (byte) DsWord0_SP_3_e_6;		
		bufferWrite[20] = (byte) LsWord0_SP_3_e_6;
		
		//Vazia e 7
		bufferWrite[21] = (byte) DsWord1_SP_V_e_7;		
		bufferWrite[22] = (byte) LsWord1_SP_V_e_7;
		bufferWrite[23] = (byte) DsWord0_SP_V_e_7;		
		bufferWrite[24] = (byte) LsWord0_SP_V_e_7;
		
		int[] crc = CalculatorCRC.calculateCRC(bufferWrite, 0, 25); //calcular o crc
		bufferWrite[25] = (byte) crc[0];
		bufferWrite[26] = (byte) crc[1];
		
		return bufferWrite;
	}
	
	
	
}

/*
static byte[] addressReadAlfa(int endereco, int registrador, int totalRegistradores) {
	byte[] bufferWrite = new byte[8];
	if ((endereco > 0) && (endereco < 256)) {
		bufferWrite[0] = (byte) endereco;
		bufferWrite[1] = (byte) 3; // Metodo de escrita
		bufferWrite[2] = (byte) 0;
		bufferWrite[3] = (byte) registrador;
		bufferWrite[4] = (byte) 0;
		bufferWrite[5] = (byte) totalRegistradores; // registrador
		
		int[] crc = CalculatorCRC.calculateCRC(bufferWrite, 0, 6); //calcular o crc
		
		bufferWrite[6] = (byte) crc[0];
		bufferWrite[7] = (byte) crc[1];

	} else {
		System.out.println("Endereço fora da faixa");
	}
	return bufferWrite;
}*/