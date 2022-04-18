package com.rodrigues.rodrigues.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.rodrigues.rodrigues.controller.HistoryController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RelatorioViewController implements Initializable{
	private HistoryController history;
	
	private Timeline timeline;
	
	@FXML
	public Text 
				B1_AC_5, B1_AC_4, B1_AC_3, B1_AC_2, B1_AC_1, B1_AC,
				B2_AC_5, B2_AC_4, B2_AC_3, B2_AC_2, B2_AC_1, B2_AC,	
				B3_AC_5, B3_AC_4, B3_AC_3, B3_AC_2, B3_AC_1, B3_AC,
				B4_AC_5, B4_AC_4, B4_AC_3, B4_AC_2, B4_AC_1, B4_AC,
				B5_AC_5, B5_AC_4, B5_AC_3, B5_AC_2, B5_AC_1, B5_AC,
				B6_AC_5, B6_AC_4, B6_AC_3, B6_AC_2, B6_AC_1, B6_AC,
				B7_AC_5, B7_AC_4, B7_AC_3, B7_AC_2, B7_AC_1, B7_AC,
				B8_AC_5, B8_AC_4, B8_AC_3, B8_AC_2, B8_AC_1, B8_AC,
				B9_AC_5, B9_AC_4, B9_AC_3, B9_AC_2, B9_AC_1, B9_AC,
				B10_AC_5, B10_AC_4, B10_AC_3, B10_AC_2, B10_AC_1, B10_AC,
				ritmoDeCarga, ritmoDeCargaUltimoH;

	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		history = new HistoryController();
		beginTimer();	
	}	

	@SuppressWarnings("unused")
	 private void beginTimer() { 
		  timeline = new
		 Timeline(new KeyFrame(javafx.util.Duration.seconds(2), ev -> { 
			 
			 if(!stage.isShowing())
				 timeline.stop();
			 else {
				 history.updatedValue();
				 System.out.println("Updated View");
			 }
		 }));
		
		 timeline.setCycleCount(Animation.INDEFINITE); timeline.play();

	}
	  
	  public void setStage(Stage stage) {
		  this.stage = stage;
	  }

}
