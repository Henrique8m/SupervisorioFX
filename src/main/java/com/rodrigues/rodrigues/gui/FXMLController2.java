package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import com.rodrigues.rodrigues.serial.Port;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;


public class FXMLController2 implements Initializable {
	private Port port;
	
	public FXMLController2(Port port) {
		this.port = port;
	}
	
	@FXML
	private Text txBaud, txParidade, txStopBits;	
	@FXML
	private ComboBox<Port> comboBoxPort;	
	@FXML
	private Button btSave;
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {

	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		

	}


}
