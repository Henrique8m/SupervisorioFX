package com.rodrigues.rodrigues.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.securit.EncryptionAES;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CheckLicenseController implements Initializable {
	//private SerialProperties serialProperties;
	private EncryptionAES crypt;
	/*
	@FXML
	private Button btSave;


	
	@FXML
	public void onComboBoxAction(ActionEvent event) {
		System.out.println(comboBoxPort.getValue());

	}
*/	
	@FXML
	public TextField fieldData;
	
	@FXML
	public void onBtAction(ActionEvent event) {
		crypt = DependencyInjection.getEncryptionaes();
		crypt.testEncrypt();
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String str = Integer.toString(MainApp.dataSpired);
		//System.out.println(str.substring(6,8) + "/" + str.substring(4,6) + "/" + str.substring(0,4));
		String strFormated = str.substring(6,8) + "/" + str.substring(4,6) + "/" + str.substring(0,4);
		
		fieldData.setText(strFormated);
	}
}
