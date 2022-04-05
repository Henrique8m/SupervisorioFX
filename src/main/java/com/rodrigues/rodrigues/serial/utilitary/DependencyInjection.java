package com.rodrigues.rodrigues.serial.utilitary;

import java.util.List;

import com.rodrigues.rodrigues.controller.ReadController;
import com.rodrigues.rodrigues.controller.SerialController;
import com.rodrigues.rodrigues.gui.CheckLicenseController;
import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.gui.PropertiesComController;
import com.rodrigues.rodrigues.gui.RelatorioViewController;
import com.rodrigues.rodrigues.gui.SetPointController;
import com.rodrigues.rodrigues.securit.DataSecurit;
import com.rodrigues.rodrigues.securit.EncryptionAES;
import com.rodrigues.rodrigues.securit.SerialMotherboard;
import com.rodrigues.rodrigues.serial.dao.WriteSetPoints;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.resources.PortComResurce;
import com.rodrigues.rodrigues.service.FormatData;
import com.rodrigues.rodrigues.service.PrimaryViewService;
import com.rodrigues.rodrigues.service.ReadService;
import com.rodrigues.rodrigues.service.SerialService;

public class DependencyInjection {
	
	private static final ReadController readController = new ReadController();
	private static final SerialController serialController = new SerialController();
	private static final ReadService readService = new ReadService();
	private static final SerialService serialService = new SerialService();
	private static final SerialProperties serialProperties = new SerialProperties();
	private static final PropertiesComController propertiesComController = new PropertiesComController();
	private static final CheckLicenseController checkLicenseController = new CheckLicenseController();
	private static final SerialMotherboard serialMotherboard = new SerialMotherboard();
	private static final EncryptionAES encryptionAES = new EncryptionAES();
	private static final FormatData formatData = new FormatData();
	private static final WriteSetPoints writeSetPoints = new WriteSetPoints();
	private static final PrimaryViewService primaryViewService = new PrimaryViewService();
	private static final DataSecurit dataSecurit = new DataSecurit();
	private static final SetPointController setPointController = new SetPointController();
	private static final PortComResurce portComResurces = new PortComResurce();
	private static final RelatorioViewController relatorioViewController = new RelatorioViewController();
	public static RelatorioViewController getRelatorioviewcontroller() {
		return relatorioViewController;
	}

	private static PrimaryViewController primaryViewController;
	private static List<String> avaliablePorts;
	
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
	
	public static SerialMotherboard getSerialmotherboard() {
		return serialMotherboard;
	}

	public static EncryptionAES getEncryptionaes() {
		return encryptionAES;
	}

	public static void setPrimaryViewController(PrimaryViewController primaryViewController) {
		DependencyInjection.primaryViewController = primaryViewController;
	}
	
	public static List<String> getPortName() {
		return avaliablePorts;
	}
	
	public static void setAvaliablePortsNames(List<String> avaliablePorts) {
		DependencyInjection.avaliablePorts = avaliablePorts;
	}

	public static FormatData getFormatData() {
		return formatData;
	}

	public static WriteSetPoints getWritesetpoints() {
		return writeSetPoints;
	}

	public static PrimaryViewService getPrimaryViewService() {
		return primaryViewService;
	}

	public static DataSecurit getDataSecurit() {
		return dataSecurit;
	}

	public static SetPointController getSetPointController() {
		return setPointController;
	}

	public static PortComResurce getPortComResurce() {
		return portComResurces;
	}
}
