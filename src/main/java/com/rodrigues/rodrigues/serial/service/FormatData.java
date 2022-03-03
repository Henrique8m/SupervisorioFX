package com.rodrigues.rodrigues.serial.service;

import com.rodrigues.rodrigues.serial.utilitary.Gadgets;

public class FormatData {
	private String display;
	private Integer division;
	private int value;
	private double valueDouble;
	
	public String formatData(byte[] bufferRead, String gatgets, String format) {
		try {
		division = Gadgets.valueOf(gatgets).getValor();	
		if(bufferRead != null ) {			
			if (Byte.toUnsignedInt(bufferRead[3]) > 0) {
				if(format == "int")
					display = Integer.toString((Byte.toUnsignedInt(bufferRead[4]) + (Byte.toUnsignedInt(bufferRead[3]) * 256)) / division);
				
				else if(format == "double") {
					valueDouble = (Byte.toUnsignedInt(bufferRead[4]) + (Byte.toUnsignedInt(bufferRead[3]) * 256) / division);
					display = Double.toString(valueDouble / 100);
				}
				
			} else {
				if(format == "int")
					display = Integer.toString((Byte.toUnsignedInt(bufferRead[4])) / division);
				else if(format == "double") {
					valueDouble = (Byte.toUnsignedInt(bufferRead[4])) / division;
					display = Double.toString(valueDouble / 100);
				}
			}
			
		}else System.out.println("buffer Read null na classe FormatData");
		
		}catch(IllegalArgumentException e ) {
			e.printStackTrace();
			return ("E " + gatgets);
		}catch (Exception e) {
			e.printStackTrace();
			return ("E " + gatgets);
		}
		return display;
	}
}
