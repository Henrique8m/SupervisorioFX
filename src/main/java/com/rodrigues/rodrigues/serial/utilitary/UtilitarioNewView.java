package com.rodrigues.rodrigues.serial.utilitary;

import java.io.IOException;

import com.rodrigues.rodrigues.MainApp;

import javafx.fxml.FXMLLoader;

public class UtilitarioNewView {
	
	public static synchronized <T> Object loadFXML(String fxml, Object controller) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("gui/" + fxml + ".fxml"));
		fxmlLoader.setController(controller);
		return fxmlLoader.load();
	}

}
