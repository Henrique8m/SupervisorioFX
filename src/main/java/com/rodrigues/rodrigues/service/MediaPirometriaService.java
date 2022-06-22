package com.rodrigues.rodrigues.service;

import java.sql.Date;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.controller.ReadController;
import com.rodrigues.rodrigues.entities.Pyrometry;
import com.rodrigues.rodrigues.gui.controller.PrimaryViewController;
import com.rodrigues.rodrigues.relatorio.servicies.RelatorioService;
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
        String timeStarStr = Format.formataTimeString.format(new Date(System.currentTimeMillis()));
		Integer nextTime = Integer.parseInt(Format.formatHora.format(new Date(System.currentTimeMillis()))) + MainApp.tempoRelatorio;
		if(nextTime >= 24) 
			nextTime -= 24;
		while(true) {
			synchronized (this) {
				try {
					this.wait();
					displayVetor = ReadController.displayVetor;	
					Integer time = Integer.parseInt(Format.formatHora.format(new Date(System.currentTimeMillis())));					

					
					if(displayVetor[MainApp.idPressaoCoroa ]!= "Error" && displayVetor[MainApp.idPressaoCoroa] != null) {
						contCiclos[0] ++;
						Integer pCoroa = Integer.parseInt(displayVetor[MainApp.idPressaoCoroa].replaceAll("[^0-9]+", ""));
						mPCoroa = calcMedia(pCoroa, mPCoroa);						
					}
					if(displayVetor[MainApp.idPressaoTopo ]!= "Error" && displayVetor[MainApp.idPressaoTopo] != null) {
						contCiclos[1] ++;
						Integer pTopo = Integer.parseInt(displayVetor[MainApp.idPressaoTopo].replaceAll("[^0-9]+", ""));
						mPTopo = calcMedia(pTopo, mPTopo);
						
					}
					if(displayVetor[MainApp.idTempCoroa]!= "Error" && displayVetor[MainApp.idTempCoroa] != null) {
						Integer tCoroa = Integer.parseInt(displayVetor[MainApp.idTempCoroa].replaceAll("[^0-9]+", ""));
						contCiclos[2] ++;
						mTCoroa = calcMedia(tCoroa, mTCoroa);
						
					}
					if(displayVetor[MainApp.idTempTopo]!= "Error" && displayVetor[MainApp.idTempTopo] != null) {
						contCiclos[3] ++;
						Integer tTopo = Integer.parseInt(displayVetor[MainApp.idTempTopo].replaceAll("[^0-9]+", ""));						
						mTTopo = calcMedia(tTopo, mTTopo);
						
					}
					if(displayVetor[MainApp.idVazao]!= "Error" && displayVetor[MainApp.idVazao] != null) {
						contCiclos[4] ++;
						Integer vazao = Integer.parseInt(displayVetor[MainApp.idVazao].replaceAll("[^0-9]+", ""));
						mVazao = calcMedia(vazao, mVazao);
						
					}
					if(displayVetor[MainApp.idSecador]!= "Error" && displayVetor[MainApp.idSecador] != null) {
						contCiclos[5] ++;
						Integer secador = Integer.parseInt(displayVetor[MainApp.idSecador].replaceAll("[^0-9]+", ""));
						mSecador = calcMedia(secador, mSecador);
						
					}if(time == nextTime) {
						String horaInicioFim = timeStarStr + " / " + Format.formataTimeString.format(new Date(System.currentTimeMillis()));
						String pressaoCoroa = mPCoroa + " mmH2O";
						String pressaoTopo = mPTopo + " mmH2O";
						String temperaturaCoroa = mTCoroa + "°C";
						String temperaturaTopo = mTTopo + "°C";
						String vazao = mVazao + "m³/h";
						String secador = mSecador + "°C";
						RelatorioService.addListPyrometry(new Pyrometry( horaInicioFim,pressaoCoroa ,pressaoTopo ,temperaturaCoroa ,temperaturaTopo ,vazao ,secador ));
						timeStarStr = Format.formataTimeString.format(new Date(System.currentTimeMillis()));
						nextTime = Integer.parseInt(Format.formatHora.format(new Date(System.currentTimeMillis()))) + MainApp.tempoRelatorio;						
						if(nextTime >= 24) 
							nextTime -= 24;
					}			

				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}catch (NullPointerException e2) {
					System.out.println("NullPointer Media Pirometria Service");
					e2.printStackTrace();
				}
			}
		}
	}


		private Integer calcMedia(Integer newValue, Integer mediaValue) {
			if(mediaValue == 0 )return newValue;
			Integer media = mediaValue + newValue;
		return media / 2;
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
