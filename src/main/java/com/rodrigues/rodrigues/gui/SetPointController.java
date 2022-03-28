package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.rodrigues.rodrigues.serial.controller.ReadController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SetPointController implements Initializable{
	private ReadController controller;
	
	@FXML
	public TextField setPointVazia, setPoint01, setPoint02, setPoint03;
	
	@FXML
	public void gravarValores(ActionEvent event) {
		System.out.println("passou");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instanciates();
		controller.setReadSetPoints(true, 19);
			
	}

	private void instanciates() {
		if(controller == null)controller = DependencyInjection.getReadController();
		
	}
}
