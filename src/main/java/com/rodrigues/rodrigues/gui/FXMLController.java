package com.rodrigues.rodrigues.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.comm.UnsupportedCommOperationException;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.gui.util.Alerts;
import com.rodrigues.rodrigues.serial.controller.SerialController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class FXMLController implements Initializable {

//	private SerialService sComm;
	public final SerialController getcontroller() {
		return this.controller;
	}

	private SerialController controller = new SerialController(this);
	private Boolean comunicationOn;
	private Timeline timeline;
	private boolean time;
	private Stage stage;

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

	//@FXML
	//private void onMenuItemPortCom(ActionEvent event) throws UnsupportedCommOperationException {
	//	//loadView("/fxml/" + "portcom" + ".fxml", null);
	//}
	@FXML
	private void comPort(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		loadView("configscene", null, "Configuração Porta Com", MainApp.getStage());
	}
	
	@FXML
	private void stopComunication(ActionEvent event) throws UnsupportedCommOperationException, IOException {
		
	}

	@FXML
	private void startComunication(ActionEvent event) throws UnsupportedCommOperationException, InterruptedException {
		comunicationOn = controller.startCommunication();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			comunicationOn = controller.startCommunication();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void beginTimer() {
		timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(2), ev -> {
			// sComm.WriteData();
			//sComm.formatDados();
			//lblOut.setText(sComm.getDisplay());
		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		time = true;

	}

	public void cancelTimer() {
		timeline.stop();
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction, String title, Stage stageEvent) {
		try {
			
			Pane pane = new Pane((Pane) MainApp.loadFXML(absoluteName));
						
			stage = new Stage();
			stage.setTitle(title);
			stage.setScene(new Scene(pane, 400, 400));
			stage.setResizable(false);
			stage.initOwner(stageEvent);
			stage.initModality(Modality.WINDOW_MODAL);			
			stage.initStyle(StageStyle.UTILITY);
			stage.setAlwaysOnTop(true);						
			stage.showAndWait();
			
			
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
