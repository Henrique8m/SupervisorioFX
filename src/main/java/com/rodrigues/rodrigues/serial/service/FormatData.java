package com.rodrigues.rodrigues.serial.service;

public class FormatData {
	private String display;
	
	public void formatData(byte[] bufferRead, Enum<> gadget) {
		if(bufferRead != null ) {
			
			if (Byte.toUnsignedInt(bufferRead[3]) > 0) {
				try {
					this.display = Integer
							.toString(Byte.toUnsignedInt(bufferRead[4]) + (Byte.toUnsignedInt(bufferRead[3]) * 256));
				} catch (Exception e) {
					e.printStackTrace();
				}
	
			} else {
				this.display = Integer.toString(Byte.toUnsignedInt(bufferRead[4]));
			}
		}else System.out.println("buffer Read null na classe FormatData");
	}
}
