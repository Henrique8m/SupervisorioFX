package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.comm.CommPortIdentifier;

import com.rodrigues.rodrigues.serial.Port;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class PropertiesComController implements Initializable {
	private SerialProperties serialProperties;
	private PrimaryViewController primaryViewController;

	public PropertiesComController(PrimaryViewController primaryViewController) {
		this.primaryViewController = primaryViewController;
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
		serialProperties = primaryViewController.getcontroller().getReadControler().getSerialProperties();
		txBaud.setText(Integer.toString(serialProperties.getBaud()));
		txParidade.setText(serialProperties.getParidade());
		txStopBits.setText(Integer.toString(serialProperties.getStopBits()));
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> enume = CommPortIdentifier.getPortIdentifiers();
		if(enume.hasMoreElements()) {
			System.out.println(enume.nextElement().getName());
		}
		
		
	}
}
