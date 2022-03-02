package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.rodrigues.rodrigues.securit.EncryptionAES;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CheckLicenseController implements Initializable {
	private SerialProperties serialProperties;

	/*
	@FXML
	private Button btSave;


	
	@FXML
	public void onComboBoxAction(ActionEvent event) {
		System.out.println(comboBoxPort.getValue());

	}
*/	
	@FXML
	public void onBtAction(ActionEvent event) {
		
		EncryptionAES.testEncrypt();
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {}
}
