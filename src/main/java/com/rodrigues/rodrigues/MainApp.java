package com.rodrigues.rodrigues;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainApp extends ResizeItens {
	static Stage stage;
	static Scene mainScene;
	
	public static String strForno = "FundoAltoForno";
	public static String strGlendon = "FundoGlendon";
	final static String nameSupervisorio = "Supervisorio 3.0";
	
	static double tamanhoWidth = 13.56D;
	static double tamanhoHeigt = 7.07D;
		
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

	public static void main(String[] args) {
		launch(args);
		System.exit(1);
	}
}
