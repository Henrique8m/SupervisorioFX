package com.rodrigues.rodrigues.service;

import java.util.EventListener;

import com.rodrigues.rodrigues.controller.ReadController;
import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;

public class MediaPirometriaService extends Thread {
	private PrimaryViewController controller;
	private ReadController read;
	
	@Override
	public void run() {
		if(read ==null) read = DependencyInjection.getReadController();
		
		while(true) {
			synchronized (this) {
				System.out.println("Teste Synchronized wait");
				try {
					this.wait();
					
					System.out.println("Passou do wait");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
		
	

		public void list() {
			EventListener ev = new EventListener() {
			
			};		
			
		}
		
		
		public void startMedia() {
			instantiate();
			/*
			timeVazaoMedia = new Timeline(new KeyFrame(javafx.util.Duration.seconds(timeVazaoMediaSecunds), ev -> {
				if((controller.vazao.getText()!= null) && (controller.vazao.getText() != "Error")) {
					contVazao++;
					vazaoAC += Integer.parseInt(controller.vazao.getText().replaceAll("[^0-9]+", ""));
				}	
			}));	
			
			timeCoroaMedia = new Timeline(new KeyFrame(javafx.util.Duration.seconds(timeCoroaMediaSecunds), ev -> {
				if((controller.pcoroa.getText()!= null) && (controller.pcoroa.getText() != "Error")) {
					contPressao++;
					pressaoAC += Integer.parseInt(controller.pcoroa.getText().replaceAll("[^0-9]+", ""));
				}
				
			}));		
			
			timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(plotSecunds), ev -> {

				if((contVazao != 0) && (vazaoAC != 0))
				mediaVazao = (double) ((vazaoAC / contVazao)/100);
				if((pressaoAC != 0) && (contPressao != 0))
				mediaPressao = (double) ((pressaoAC / contPressao)/100);
											
					if(serieVazao!=null) {
						if(serieVazao.getData().size()< sizeSeries) {
							newSerie(mediaVazao,mediaPressao);
						}else 
							overwrite(mediaVazao,mediaPressao);
					}else 
						inicializeSerie(mediaVazao,mediaPressao);
					
					pressaoAC = 0;
					vazaoAC = 0;
					contVazao =0;
					contPressao = 0;
				}));
			
			timeVazaoMedia.setCycleCount(Animation.INDEFINITE); 
			timeVazaoMedia.play(); 
			
			timeCoroaMedia.setCycleCount(Animation.INDEFINITE); 
			timeCoroaMedia.play(); 
			
			timeline.setCycleCount(Animation.INDEFINITE); 
			timeline.play(); 
		}
		

		private void overwrite(Double mediaVazao, Double mediaPressao) {
			serieVazao.getData().remove(0);
			seriePressao.getData().remove(0);
			
			newSerie(mediaVazao, mediaPressao);		
		}


		private void inicializeSerie(Double mediaVazao, Double mediaPressao) {
			seriePressao = new XYChart.Series<>();
			seriePressao.setName("Pressao da Coroa");
			
	    	serieVazao = new XYChart.Series<>();
	    	serieVazao.setName("Vazão Ar Combustão");
	    	
	    	controller.lineChart.getData().add(seriePressao);
	    	controller.lineChart.getData().add(serieVazao);
	    	
	    	seriePressao.getData().add(new Data<String, Double>(getData(), mediaPressao));	
	    	serieVazao.getData().add(new Data<String, Double>(getData(), mediaVazao));		
		}


		private void newSerie(Double mediaVazao2, Double mediaPressao2) {
			seriePressao.getData().add(new Data<String, Double>(getData(), mediaPressao2));	
			serieVazao.getData().add(new Data<String, Double>(getData(), mediaVazao2));		
		}
		
		
		*/
		}
		

		
		private void instantiate() {
			if(controller == null)controller = DependencyInjection.getPrimaryViewController();		
		}
		
		/*
		
		public void cancelTimer() {
			timeline.stop();
			timeCoroaMedia.stop();
			timeVazaoMedia.stop();
		}	
		
		*/
		
	}
