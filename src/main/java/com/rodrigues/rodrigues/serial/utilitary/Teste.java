package com.rodrigues.rodrigues.serial.utilitary;

public enum Teste {
	N2000(1, 8, 7),
	N1500(1, 8, 7),
	N1540(10, 8, 7),
	N1500_4_a_20(1, 8, 7),
	N1540_4_a_20(1, 8, 7),
	ALFA(10, 27, 17);
	
	
	int divisao;
	int bufferWrite;
	int bufferRead;

	
	Teste(int divisao, int bufferWrite, int bufferRead) {
		this.divisao = divisao;
		this.bufferWrite = bufferWrite;
		this.bufferRead = bufferRead;
	}


	public int getDivisao() {
		return divisao;
	}
	public int getBufferWrite() {
		return bufferWrite;
	}
	public int getBufferRead() {
		return bufferRead;
	}
}
