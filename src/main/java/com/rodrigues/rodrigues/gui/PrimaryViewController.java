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
import com.rodrigues.rodrigues.serial.controller.SerialController;
import com.rodrigues.rodrigues.serial.utilitary.DependencyInjection;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PrimaryViewController implements Initializable {

//	private SerialService sComm;
	public final SerialController getcontroller() {
		return this.controller;
	}

	private SerialController controller;
	@SuppressWarnings("unused")
	private Boolean comunicationOn;
	private Timeline timeline;
	@SuppressWarnings("unused")
	private boolean time;
	
	private List<String> portName;

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

	//@FXML
	//private void onMenuItemPortCom(ActionEvent event) throws UnsupportedCommOperationException {
	//	//loadView("/fxml/" + "portcom" + ".fxml", null);
	//}
	@FXML
	private void comPort(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		loadView("propertiesCom", null, "Configuração Porta Com", MainApp.getStage());
	}
	
	@FXML
	public void stopComunication(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		controller.timerCancel();
		txLog.setText("Comunication Stop");
		txLog1.setText("Comunication Stop");
	}

	@FXML
	private void startComunication(ActionEvent event) throws UnsupportedCommOperationException, InterruptedException {
		controller.startCommunication();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		portName = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> enume = CommPortIdentifier.getPortIdentifiers();		
		while(enume.hasMoreElements()) {
			portName.add(enume.nextElement().getName());		
		}
		DependencyInjection.setPortName(portName);
		System.out.println(portName);
		
		DependencyInjection.setPrimaryViewController(this);
		controller = DependencyInjection.getSerialController();
		//controller.setFxmlController(this);
		//controller = new SerialController(PrimaryViewController.this);
		
		controller.startCommunication();
	}

	@SuppressWarnings("unused")
	private void beginTimer() {
		timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(2), ev -> {
			// sComm.WriteData();
			//sComm.formatDados();
			//lblOut.setText(sComm.getDisplay());
		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		time = true;

	}

	@SuppressWarnings("unused")
	private void cancelTimer() {
		timeline.stop();
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction, String title, Stage stageEvent) {
		try {
			UtilitarioNewView.getNewViewModal(title, (Pane) UtilitarioNewView.loadFXML(absoluteName, new PropertiesComController(this)), stageEvent);
		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(), AlertType.ERROR);
		}	
	}
}

/*	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVBox = loader.load();
		Scene mainScene = MainApp.getMainScene();
		VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

		Node mainMenu = mainVBox.getChildren().get(0);
		mainVBox.getChildren().clear();
		mainVBox.getChildren().add(mainMenu);
		mainVBox.getChildren().addAll(newVBox.getChildren());

		// T controller = loader.getController();
		// initializingAction.accept(controller);
	} catch (IOException e) {
		Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(), AlertType.ERROR);
	}	
}*/
