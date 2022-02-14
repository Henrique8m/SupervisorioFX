package com.rodrigues.rodrigues.gui;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.serial.controller.SerialController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.comm.UnsupportedCommOperationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLController implements Initializable {

//	private SerialService sComm;

	private final SerialController controller = new SerialController(this);
	private Boolean comunicationOn;
	private Timeline timeline;
	private boolean time;
	private String strImage = "file:///C:/SupervisorioFx/FundoSupervisorio.png";
	private String str = "FundoSupervisorio.png";
	private Image image2;

	@FXML
	private ImageView imageView;
	
	Image image = new Image(strImage);
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
	public void initialize(URL url, ResourceBundle rb) {
		try {
			URI urlss= FXMLController.class.getProtectionDomain().getCodeSource().getLocation().toURI();
			File files2 = new File(urlss);
			System.out.println(urlss + str);
			String caminho = urlss + str;
			System.out.println(caminho);
			image = new Image(caminho);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		imageView.setImage(image);
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
