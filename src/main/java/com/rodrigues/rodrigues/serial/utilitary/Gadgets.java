package com.rodrigues.rodrigues.serial.utilitary;

public enum Gadgets {	
	N2000(1),
	N1500(1),
	N1540(10),
	N1500_4_a_20(1),
	N1540_4_a_20(1);
	
	
	int divisao;
	
	Gadgets(int valorOpcao) {
		divisao = valorOpcao;
	}

	public int getValor() {
		return divisao;
	}
}
