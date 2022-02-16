package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.serial.Port;
import com.rodrigues.rodrigues.serial.controller.SerialController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class PropertiesComController implements Initializable {
	private List<Port> port = new ArrayList<>();
	private SerialController controller;
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
	}

	public void setController(SerialController controller) {
		this.controller = controller;

	}

}
