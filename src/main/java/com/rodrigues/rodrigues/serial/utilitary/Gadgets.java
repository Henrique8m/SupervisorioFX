package com.rodrigues.rodrigues.serial.utilitary;

public enum Gadgets {	
	N2000(1, 8, 7, 0, 4, 3),
	N1500(1, 8, 7),
	N1540(10, 8, 7),
	N1500_4_a_20(1, 8, 7),
	N1540_4_a_20(1, 8, 7),
	ALFA(10, 27, 17);
	
	
	int divisao;
	int bufferWrite;
	int bufferRead;
	int bufferRead1Ms;
	int bufferReadMs;
	int bufferReadLs;

	
	Gadgets(int divisao, int bufferWrite, int bufferRead, int bufferRead1Ms, int bufferRead1Ms, int bufferReadLs ) {
		this.divisao = divisao;
		this.bufferWrite = bufferWrite;
		this.bufferRead = bufferRead;
		this.bufferRead1Ms;
		this.bufferRead1Ms;
		this.bufferReadLs;
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
