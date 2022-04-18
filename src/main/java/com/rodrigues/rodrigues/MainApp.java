package com.rodrigues.rodrigues;

import java.io.IOException;
import java.util.function.Consumer;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
	private static Stage stage;
	private static Scene mainScene;
	private static ImageView imageAltoForno;
	private static ImageView imageGlendon;
	
	private static String strForno = "FundoAltoForno";
	private static String strGlendon = "FundoGlendon";
	private final static String nameSupervisorio = "Supervisorio 3.0";
		
	@SuppressWarnings("unused")
	private static Consumer<?> controller;

	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	@Override
	public void start(Stage s) throws IOException {
		stage = s;
		setRoot("primaryView", "");
	}

	static void setRoot(String fxml) throws IOException {
		setRoot(fxml, stage.getTitle());
	}

	static void setRoot(String fxml, String title) throws IOException {

		//Teste dimension
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//carregar imagens de fundo
		try {
			imageAltoForno = new ImageView(new Image(MainApp.class.getResource("gui/resources/" + strForno + ".png").toString()));
			imageGlendon = new ImageView(new Image(MainApp.class.getResource("gui/resources/" + strGlendon + ".png").toString()));
		}catch (NullPointerException e){
			e.printStackTrace();
		}
		//definir tamanho para manter a dimension
		imageAltoForno.setFitHeight(709);
		imageAltoForno.setFitWidth(1360);
		imageGlendon.setFitHeight(709);
		imageGlendon.setFitWidth(1360);
		
		//Carregar o scrollPane que vai ser a base de tudo
		ScrollPane scrollPane = (ScrollPane) UtilitarioNewView.loadFXML(fxml, new PrimaryViewController());
		
		//Navegar ate a imagem para inserir o fundo
		VBox vbox = (VBox) scrollPane.getContent();
		TabPane tabPane = (TabPane) vbox.getChildren().get(1);
		AnchorPane anchorAltoForno = (AnchorPane) tabPane.getTabs().get(0).getContent();
		AnchorPane anchorGlendons = (AnchorPane) tabPane.getTabs().get(1).getContent();
		anchorAltoForno.getChildren().add(0, imageAltoForno);
		anchorGlendons.getChildren().add(0, imageGlendon);
		
		//Preparar a sena e showww
		stage = UtilitarioNewView.getNewView(nameSupervisorio, mainScene = new Scene(scrollPane));
	}
	
	public static void main(String[] args) {
		launch(args);
		System.exit(1);
	}
}
