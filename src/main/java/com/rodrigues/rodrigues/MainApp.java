package com.rodrigues.rodrigues;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends ResizeItens {
	static Stage stage;
	static Scene mainScene;
	
	//--------------------------------------------- Configuração modbus -------------------------------------------------------------------//	
	
	public static final Object defautPort = "COM4";	
	public static Integer threadSleep = 150;
	
	//--------------------------------------------- Configuração do relatorio de media-----------------------------------------------------//
	
	public static final int tempoRelatorio = 1; //Em Horas
	
	//--------------------------------------------- Configuração dos aparelhos ------------------------------------------------------------//
	
	public static int varredurasAlfaMais = 1 ;
	public static String[] aparelhos = new String[]{
			"N1540","N1540","N1540",
			"N1540","N1540","N1540",
			"N1540","N1540","N1540",
			"N1540","N1540","N1540_4_a_20",
			"N1540","N1540","N2000",
			"N1500","N1540_4_a_20","N1540_4_a_20",
			"ALFA","ALFA","ALFA","ALFA","ALFA",
			"ALFA","ALFA","ALFA","ALFA","ALFA"};
	
	
	public static int
	idPressaoCoroa = 15 -1,
	idPressaoTopo = 12-1,
	idTempCoroa = 13-1,
	idTempTopo = 10-1,
	idVazao = 17-1,
	idSecador = 18-1;
	
	//--------------------------------------------- Configuração da view ------------------------------------------------------------------//
	
	public static String strForno = "FundoAltoForno";
	public static String strGlendon = "FundoGlendon";
	final static String nameSupervisorio = "Supervisorio 3.0";	
	static double tamanhoWidth = 13.56D;
	static double tamanhoHeigt = 7.07D;

	//--------------------------------------------- Configuração dos Relatorios-------------------------------------------------------------//
	
	public static String caminhoPDF = "\\Desktop\\Relatorios";
	
	public static String caminhoDbProperties = "C:\\Program Files\\Java\\resources\\db.properties";
	public static String properties = "Properties.properties";
	public static String Params = "Params.properties";
	
	public static String diretorioStr1 = "\\AppData\\Local\\YggDrasil";
	public static String diretorioStr2 = "\\AppData\\Local\\YggDrasil\\serial";
	
	
	
	
	
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
