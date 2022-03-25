package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SetPointController implements Initializable{
	
	@FXML
	private TextField setPointVazia, setPoint01, setPoint02, setPoint03;
	
	@FXML
	public void gravarValores(ActionEvent event) {
		System.out.println("passou");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
	}

}
