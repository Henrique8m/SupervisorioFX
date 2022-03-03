package com.rodrigues.rodrigues.serial.service;

public class FormatData {
	private byte[] bufferRead;
	private String display;
	
	public void formatData() {
		if (Byte.toUnsignedInt(this.bufferRead[3]) > 0) {
			try {
				this.display = Integer
						.toString(Byte.toUnsignedInt(bufferRead[4]) + (Byte.toUnsignedInt(bufferRead[3]) * 256));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			this.display = Integer.toString(Byte.toUnsignedInt(this.bufferRead[4]));
		}
	}
}
