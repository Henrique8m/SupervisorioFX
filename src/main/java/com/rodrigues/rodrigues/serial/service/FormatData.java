package com.rodrigues.rodrigues.serial.service;

import com.rodrigues.rodrigues.serial.utilitary.Gadgets;

public class FormatData {
	private String display;
	private Integer division;
	private double valueDouble;
	
	public String formatData(byte[] bufferRead, String gatgets, String format) {
		try {
		division = Gadgets.valueOf(gatgets).getValor();	
		if(bufferRead != null ) {			
			if (Byte.toUnsignedInt(bufferRead[3]) > 0) {
				if(format == "int")
					display = Integer.toString(((Byte.toUnsignedInt(bufferRead[4])) + ((Byte.toUnsignedInt(bufferRead[3]) * 256))) / division);
				
				else if(format == "double") {
					valueDouble = (((Byte.toUnsignedInt(bufferRead[4])) + ((Byte.toUnsignedInt(bufferRead[3]) * 256))) / division);
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
	
	
	@SuppressWarnings("unused")
	public String formatDataAlfa(byte[] bufferRead) {
		//Primeiro endereço
		//Segundo Registrador
		
		//do quarto ao nono dados
		//Quarto byte, bit 4 = 1 balança em movimento
		
		//sexto e setimo peso = sexto = msword, setimo = lsword
		
		if(bufferRead != null ) {			
			if (Byte.toUnsignedInt(bufferRead[9]) > 0) {
			
				valueDouble = (Byte.toUnsignedInt(bufferRead[10])) + ((Byte.toUnsignedInt(bufferRead[9]) * 256)) ;
				
			} else {

				valueDouble = (Byte.toUnsignedInt(bufferRead[10]));
			}
			
		}else {
			System.out.println("buffer Read null na classe FormatDataAlda");
			return "1.02";
		}

		return Double.toString(valueDouble/10);
	}
}
