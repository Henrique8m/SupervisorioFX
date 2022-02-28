package com.rodrigues.rodrigues.serial.utilitary;

import java.util.List;

import com.rodrigues.rodrigues.gui.CheckLicenseController;
import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.gui.PropertiesComController;
import com.rodrigues.rodrigues.serial.controller.ReadController;
import com.rodrigues.rodrigues.serial.controller.SerialController;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.service.ReadService;
import com.rodrigues.rodrigues.serial.service.SerialService;

public class DependencyInjection {
	
	private static final ReadController readController = new ReadController();
	private static final SerialController serialController = new SerialController();
	private static final ReadService readService = new ReadService();
	private static final SerialService serialService = new SerialService();
	private static final SerialProperties serialProperties = new SerialProperties();
	private static final PropertiesComController propertiesComController = new PropertiesComController();
	private static final CheckLicenseController checkLicenseController = new CheckLicenseController();
	private static PrimaryViewController primaryViewController;
	private static List<String> portName;
	
	public DependencyInjection() {
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

	public static PrimaryViewController getPrimaryViewController() {
		return primaryViewController;
	}
	
	public static PropertiesComController getPropertiesComController() {
		return propertiesComController;
	}
	public static CheckLicenseController getCheckLicenseController() {
		return checkLicenseController;
	}
	
	public static void setPrimaryViewController(PrimaryViewController primaryViewController) {
		DependencyInjection.primaryViewController = primaryViewController;
	}
	
	public static List<String> getPortName() {
		return portName;
	}
	
	public static void setPortName(List<String> portName) {
		DependencyInjection.portName = portName;
	}
}
