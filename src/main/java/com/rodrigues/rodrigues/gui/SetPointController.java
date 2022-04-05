package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.rodrigues.rodrigues.controller.ReadController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class SetPointController implements Initializable{
	private ReadController controller;
	private PrimaryViewController primaryView;
	@FXML
	public TextField setPointVazia, setPoint01, setPoint02, setPoint03;
	@FXML
	public Text balanca;
	
	@FXML
	public void gravarValores(ActionEvent event) {
		String[] setPoints = new String[4];
		setPoints[0] = setPointVazia.getText().replaceAll("[^0-9]+", "");
		setPoints[1] = setPoint01.getText().replaceAll("[^0-9]+", "");
		setPoints[2] = setPoint02.getText().replaceAll("[^0-9]+", "");
		setPoints[3] = setPoint03.getText().replaceAll("[^0-9]+", "");
		controller.setWriteSetPoints(true, setPoints);
		
	}
	
	@FXML
	public void format(KeyEvent event) {

		if(event.getTarget().equals(setPoint01)){
			setPoint01.setText(replace(setPoint01.getText()));			
			setPoint01.end();	
			
		}else if(event.getTarget().equals(setPointVazia)){
			setPointVazia.setText(replace(setPointVazia.getText()));
			setPointVazia.end();	
				
		}else if(event.getTarget().equals(setPoint02)){
			setPoint02.setText(replace(setPoint02.getText()));
			setPoint02.end();	
			
		}else if(event.getTarget().equals(setPoint03)){			
			setPoint03.setText(replace(setPoint03.getText()));
			setPoint03.end();	
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		instanciates();
		balanca.setText(primaryView.balancaName);
		controller.setReadSetPoints(true);
			
	}

	private void instanciates() {
		if(controller == null)controller = DependencyInjection.getReadController();

		if(primaryView == null)primaryView = DependencyInjection.getPrimaryViewController();
		
	}
	private String replace(String input) {
		String replace;
		input = input.replaceAll("[^0-9]+", "");
		StringBuilder stringBuilder = new StringBuilder(input);;
		
		if(input.length()>1) {
			replace = stringBuilder.insert(input.length()-1, '.').toString();
			if(input.length()>2)
				if(replace.charAt(0)=='0') {
					replace = replace.replaceFirst("0", "");				
				}
			
		}else if(input.length() == 1 ) {
			replace = stringBuilder.insert(input.length()-1, "0.").toString();
		}else {
			replace = stringBuilder.insert(input.length(), "0.0").toString();
		}
		return replace;
	}
}
