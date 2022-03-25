package com.rodrigues.rodrigues.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.comm.CommPortIdentifier;
import javax.comm.UnsupportedCommOperationException;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.gui.util.Alerts;
import com.rodrigues.rodrigues.securit.DataSecurit;
import com.rodrigues.rodrigues.serial.controller.SerialController;
import com.rodrigues.rodrigues.serial.properties.SerialProperties;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryViewController implements Initializable {

	private static final Object defautPort = "COM4";
	private String lastPort = "";
	private SerialController serialController;
	private SerialProperties serialProperties;
	@SuppressWarnings("unused")
	private Boolean comunicationOn;
	private Timeline timeline;
	@SuppressWarnings("unused")
	private boolean time;
	private DataSecurit securit;

	private List<String> avaliablePorts;
	

	@FXML
	public Text cf1, cf2, cf3;
	@FXML
	public Text cm1, cm2, cm3;
	@FXML
	public Text cq1, cq2, cq3;
	@FXML
	public Text s1, s2, s3;
	@FXML
	public Text topoE, topoD, coroaE, coroaD;
	@FXML
	public Text topoE1, topoD1, coroaE1, coroaD1;
	@FXML
	public Text pcoroa1, ptopo1;
	@FXML
	public Text pcoroa, ptopo;
	@FXML
	public Text vazao, vazao1;
	@FXML
	public Text psm, psm1;
	@FXML
	public Text pirometro, pirometro1;
	@FXML
	public Text txLog, txLog1;
	@FXML
	public Text Balanca01, Balanca02, Balanca03, Balanca04, Balanca05;
	@FXML
	public Text Balanca06, Balanca07, Balanca08, Balanca09, Balanca10;
	@FXML
	public LineChart<String, Integer> lineChart;

	@FXML
	private void comPort(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		loadView("propertiesCom", null, "Configuração de Set Points", MainApp.getStage(),
				DependencyInjection.getSetPointController());
	}

	@FXML
	private void SetPointB1(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		loadView("setPointConfigView", null, "Status License", MainApp.getStage(),
				DependencyInjection.getCheckLicenseController());
		

	}
	
	@FXML
	private void checkLicense(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		loadView("checkLicense", null, "Status License", MainApp.getStage(),
				DependencyInjection.getCheckLicenseController());
	}

	@FXML
	public void stopComunication(ActionEvent event) throws UnsupportedCommOperationException, IOException {

		if (securit.validateData()) {
			serialController.stopCommunication();
			// serialController.timerCancel();
			txLog.setText("Comunication Stop");
			txLog1.setText("Comunication Stop");
		} else {
			showError();
		}

	}

	@FXML
	private void startComunication(ActionEvent event) throws UnsupportedCommOperationException, InterruptedException {
		if (securit.validateData()) {
			serialController.startCommunication();
		} else {
			showError();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		instanciates();
	
		if(securit.validateData()) {
			
			
			avaliablePorts = new ArrayList<>();
			@SuppressWarnings("unchecked")
			Enumeration<CommPortIdentifier> enume = CommPortIdentifier.getPortIdentifiers();		
			while(enume.hasMoreElements()) {
				avaliablePorts.add(enume.nextElement().getName());		
			}
			DependencyInjection.setAvaliablePortsNames(avaliablePorts);
			
			for(String e : avaliablePorts)
				if(e.equals(defautPort))
					serialProperties.setPorta("COM4");
				else if(e.equals(lastPort))
				serialProperties.setPorta(lastPort);
			
			DependencyInjection.setPrimaryViewController(this);
			serialController = DependencyInjection.getSerialController();
			//controller.setFxmlController(this);
			//controller = new SerialController(PrimaryViewController.this);
			
			serialController.startCommunication();
		}else {
			showError();
		}
		LineChartSample();
	}
	/*
	 * @SuppressWarnings("unused") private void beginTimer() { timeline = new
	 * Timeline(new KeyFrame(javafx.util.Duration.seconds(2), ev -> { //
	 * sComm.WriteData(); //sComm.formatDados();
	 * //lblOut.setText(sComm.getDisplay()); }));
	 * 
	 * timeline.setCycleCount(Animation.INDEFINITE); timeline.play(); time = true;
	 * 
	 * }
	 */

	@SuppressWarnings("unused")
	private void cancelTimer() {
		timeline.stop();
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction, String title,
			Stage stageEvent, Object controller) {
		try {
			UtilitarioNewView.getNewViewModal(title, (Pane) UtilitarioNewView.loadFXML(absoluteName, controller),
					stageEvent);
		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(), AlertType.ERROR);
		}
	}

	private void instanciates() {
		if (serialController == null)
			serialController = DependencyInjection.getSerialController();
		if (serialProperties == null)
			serialProperties = DependencyInjection.getSerialProperties();
		if (securit == null)
			securit = DependencyInjection.getDataSecurit();
	}

	private void showError() {
		Alerts.showAlert("Securit", "Error, validação da licença ",
				"Erro ao validar a licença, entre em contato com o adim", AlertType.ERROR);
	}

	public void LineChartSample() {
		
		lineChart.setTitle("Grafico Ativo");
		
		//lineChart.setVisible(true);
		Series<String, Integer> series = new XYChart.Series<>();
		series.setName("Pressao da Coroa");
        
        series.getData().add(new Data<String, Integer>("10:10", 23));
        series.getData().add(new Data<String, Integer>("11:10", 14));
        series.getData().add(new Data<String, Integer>("12:10", 15));
        series.getData().add(new Data<String, Integer>("13:10", 24));
        series.getData().add(new Data<String, Integer>("14:10", 34));
        series.getData().add(new Data<String, Integer>("15:10", 36));
        series.getData().add(new Data<String, Integer>("16:10", 22));
        series.getData().add(new Data<String, Integer>("17:10", 45));
        series.getData().add(new Data<String, Integer>("18:10", 43));
        series.getData().add(new Data<String, Integer>("19:10", 17));
        series.getData().add(new Data<String, Integer>("20:10", 29));
        series.getData().add(new Data<String, Integer>("21:10", 100));
		
		lineChart.getData().add(series);

	}

}


