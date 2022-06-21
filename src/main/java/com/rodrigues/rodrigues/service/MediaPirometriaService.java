package com.rodrigues.rodrigues.service;

import java.sql.Date;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.controller.ReadController;
import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.Format;

public class MediaPirometriaService extends Thread {
	private PrimaryViewController controller;
	private ReadController read;
	private String[] displayVetor;
	private Integer mPCoroa = 0, mPTopo = 0, mTCoroa = 0, mTTopo = 0, mVazao = 0, mSecador = 0;
	private int[] contCiclos = new int[6];
	
	@Override
	public void run() {
		if(read ==null) read = DependencyInjection.getReadController();
        Date date = new Date(System.currentTimeMillis()); 			
        Integer time = Integer.parseInt(Format.formatHora.format(date));
		
		while(true) {
			synchronized (this) {
				try {
					this.wait();
					displayVetor = ReadController.displayVetor;					
					if(displayVetor[MainApp.idPressaoCoroa]!= "Error") {
						contCiclos[1] ++;
						Integer pCoroa = Integer.parseInt(displayVetor[MainApp.idPressaoCoroa].replaceAll("[^0-9]+", ""));
						mPCoroa = calcMedia(pCoroa, mPCoroa, contCiclos[1]);
					}
					if(displayVetor[MainApp.idPressaoTopo]!= "Error") {
						contCiclos[2] ++;
						Integer pTopo = Integer.parseInt(displayVetor[MainApp.idPressaoTopo].replaceAll("[^0-9]+", ""));
						mPTopo = calcMedia(pTopo, mPTopo, contCiclos[2]);
						
					}
					if(displayVetor[MainApp.idTempCoroa]!= "Error") {
						Integer tCoroa = Integer.parseInt(displayVetor[MainApp.idTempCoroa].replaceAll("[^0-9]+", ""));
						contCiclos[3] ++;
						mTCoroa = calcMedia(tCoroa, mTCoroa, contCiclos[3]);
						
					}
					if(displayVetor[MainApp.idTempTopo]!= "Error") {
						contCiclos[4] ++;
						Integer tTopo = Integer.parseInt(displayVetor[MainApp.idTempTopo].replaceAll("[^0-9]+", ""));						
						mTTopo = calcMedia(tTopo, mTTopo, contCiclos[4]);
						
					}
					if(displayVetor[MainApp.idVazao]!= "Error") {
						contCiclos[5] ++;
						Integer vazao = Integer.parseInt(displayVetor[MainApp.idVazao].replaceAll("[^0-9]+", ""));
						mVazao = calcMedia(vazao, mVazao, contCiclos[5]);
						
					}
					if(displayVetor[MainApp.idSecador]!= "Error") {
						contCiclos[6] ++;
						Integer secador = Integer.parseInt(displayVetor[MainApp.idSecador].replaceAll("[^0-9]+", ""));
						mSecador = calcMedia(secador, mSecador, contCiclos[6]);
						
					}
					
					
					
					
					
					
					
					
					
					

				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}


		private Integer calcMedia(Integer newValue, Integer mediaValue, int contCiclos) {
			Integer media = mediaValue + newValue;
		return media / contCiclos;
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
