package com.rodrigues.rodrigues.gui;

import com.rodrigues.rodrigues.serial.controller.SerialController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.comm.UnsupportedCommOperationException;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLController implements Initializable {

//	private SerialService sComm;

	private final SerialController controller = new SerialController();
	private Boolean comunicationOn;
	private Timeline timeline;
	private boolean time;

	@FXML
	private Label cq1, cq2, cq3;
	@FXML
	private Label s1, s2, s3;
	@FXML
	private Label topo, coroa;
	@FXML
	private Label pcoroa, ptopo;
	@FXML
	private Label vazao;
	@FXML
	private Label psm;

	//@FXML
	//private MenuItem menuItemportCom;

	//@FXML
	//private void onMenuItemPortCom(ActionEvent event) throws UnsupportedCommOperationException {
	//	//loadView("/fxml/" + "portcom" + ".fxml", null);
	//}

	@FXML
	private void stopComunication(ActionEvent event) throws UnsupportedCommOperationException {
	}

	@FXML
	private void startComunication(ActionEvent event) throws UnsupportedCommOperationException, InterruptedException {
		comunicationOn = controller.startCommunication();

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {}

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
/*
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
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
}
