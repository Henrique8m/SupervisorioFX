package com.rodrigues.rodrigues.serial.utilitary.calc;

public class CalculatorByteInt {
	
	public static byte[] intToByteWord16(int value) {
		
		byte[] valueByte = new byte[4];
		int cont = 0;
		valueByte[3] = 0;
		
		if(value != 0) {
			if(value > 65536) {
				value = value - 65536;
				valueByte[2] = 1;
			}
			
			if(value > 256) {
				for(;value > 256; value = value - 256 ){
					cont++;	
		        	}
				
				valueByte[1] = (byte) cont;
				valueByte[0] = (byte) value;
				
			}else {
				valueByte[1] = 0;
				valueByte[0] = (byte) value;
			}
			
//	        System.out.println("valueByte[0] = " + valueByte[0] + " \nvalueByte[1] = "
//	                + valueByte[1] + "\nvalueByte[2] = " + valueByte[2] + "\nvalueByte[3] = " + valueByte[3]);
		}else {
			valueByte[0] = 0;
			valueByte[1] = 0;
			valueByte[2] = 0;
			System.out.println("Value its null in calculatorByteWord16");
		}
		
//		for(byte x : valueByte)
//			System.out.println(x);
		
		return valueByte;
	}



}
