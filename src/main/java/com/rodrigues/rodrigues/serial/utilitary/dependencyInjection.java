package com.rodrigues.rodrigues.serial.utilitary;

import com.rodrigues.rodrigues.serial.controller.ReadController;
import com.rodrigues.rodrigues.serial.controller.SerialController;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.ReadService;
import com.rodrigues.rodrigues.serial.service.SerialService;

public class dependencyInjection {
	private static ReadController readController = new ReadController();
	private static SerialController serialController = new SerialController();
	private static ReadService readService = new ReadService();
	private static SerialService serialService = new SerialService();
	private static SerialProperties serialProperties = new SerialProperties();
	
	public dependencyInjection() {
	}

	public static ReadController getReadController() {
		return readController;
	}

	public static SerialController getSerialController() {
		return serialController;
	}

	public static ReadService getReadService() {
		return readService;
	}

	public static SerialService getSerialService() {
		return serialService;
	}

	public static SerialProperties getSerialProperties() {
		return serialProperties;
	}

	
}
