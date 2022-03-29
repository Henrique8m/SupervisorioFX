package com.rodrigues.rodrigues.serial.utilitary;

public enum EndGadgets {
	Balança_01(19),
	Balança_02(20),
	Balança_03(21),
	Balança_04(22),
	Balança_05(23),
	Balança_06(24),
	Balança_07(25),
	Balança_08(26),
	Balança_09(27),
	Balança_10(28);

	int end;
	
	EndGadgets(int end) {
		this.end = end;
	}
	
	public int getEnd() {
		return this.end;
	}

}
