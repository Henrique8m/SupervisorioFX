package com.rodrigues.rodrigues;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
	private static String strForno = "FundoAltoForno.png";
	private static String strGlendon = "FundoGlendon.png";

	public static Scene getMainScene() {
		return mainScene;
	}

	@Override
	public void start(Stage s) throws IOException {
		stage = s;
		setRoot("primary", "");
	}

	static void setRoot(String fxml) throws IOException {
		setRoot(fxml, stage.getTitle());
	}

	static void setRoot(String fxml, String title) throws IOException {
		//Teste dimension
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//carregar imagens de fundo
		imageAltoForno = new ImageView(new Image(MainApp.class.getResource(strForno).toString()));
		imageGlendon = new ImageView(new Image(MainApp.class.getResource(strGlendon).toString()));
		
		//definir tamanho para manter a dimension
		imageAltoForno.setFitHeight(709);
		imageAltoForno.setFitWidth(1360);
		imageGlendon.setFitHeight(709);
		imageGlendon.setFitWidth(1360);
		
		//Carregar o scrollPane que vai ser a base de tudo
		ScrollPane scrollPane = loadFXML(fxml);
		
		//Navegar ate a imagem para inserir o fundo
		VBox vbox = (VBox) scrollPane.getContent();
		TabPane tabPane = (TabPane) vbox.getChildren().get(1);
		AnchorPane anchorAltoForno = (AnchorPane) tabPane.getTabs().get(0).getContent();
		AnchorPane anchorGlendons = (AnchorPane) tabPane.getTabs().get(1).getContent();
		anchorAltoForno.getChildren().add(0, imageAltoForno);
		anchorGlendons.getChildren().add(0, imageGlendon);
		
		//Preparar a sena e showww
		mainScene = new Scene(scrollPane);
		stage.setMaximized(true);
		stage.setTitle(" Supervisorio 1.0");
		stage.setScene(mainScene);
		stage.show();
	}

	public static ScrollPane loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));

		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
