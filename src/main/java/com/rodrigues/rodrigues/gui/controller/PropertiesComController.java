package com.rodrigues.rodrigues.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class PropertiesComController implements Initializable {
	private SerialProperties serialProperties;

	@FXML
	private Text txBaud, txParidade, txStopBits;
	@FXML
	private ComboBox<String> comboBoxPort;
	@FXML
	private Button btSave;

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		

	}
	
	@FXML
	public void onComboBoxAction(ActionEvent event) {
		System.out.println(comboBoxPort.getValue());

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		comboBoxPort.setItems(FXCollections.observableList(DependencyInjection.getPortName()));

		serialProperties = DependencyInjection.getSerialProperties();
		
		txBaud.setText(Integer.toString(serialProperties.getBaud()));
		txParidade.setText(serialProperties.getParidade());
		txStopBits.setText(Integer.toString(serialProperties.getStopBits()));
	}
}
