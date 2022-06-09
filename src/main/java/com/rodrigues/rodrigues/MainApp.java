package com.rodrigues.rodrigues;

import java.io.IOException;
import java.util.function.Consumer;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {
	private static Stage stage;
	private static Scene mainScene;
	private static Image imageAltoForno;
	private static ImageView imageGlendon;
	
	private static String strForno = "FundoAltoForno";
	private static String strGlendon = "FundoGlendon";
	private final static String nameSupervisorio = "Supervisorio 3.0";
	
	private double tamanhoWidth = 13.56;
	private double tamanhoHeigt = 7.07;
		
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
		

		try {
			imageAltoForno = new Image(MainApp.class.getResource("gui/resources/" + strForno + ".png").toString());
	
		}catch (NullPointerException e){
			e.printStackTrace();
		}
		BackgroundSize size = new BackgroundSize(100, 100, true, true, true ,false);
		
		BackgroundImage back = new BackgroundImage(imageAltoForno, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT ,size );

		TabPane tabPane = (TabPane) UtilitarioNewView.loadFXML(fxml, new PrimaryViewController());
		AnchorPane anchorAltoForno = (AnchorPane) tabPane.getTabs().get(0).getContent();		
		Background backgroud = new Background(back);		
		anchorAltoForno.setBackground(backgroud);
		
		Text text = (Text) anchorAltoForno.getChildren().get(0);
		Double layoutX = text.getLayoutX();
		Double layoutY = text.getLayoutY();	
		
		
		Double porcentXText = ( layoutX / 13.56 ) ;
		Double porcentYText = ( layoutY / 7.07 ) ;
		Double wrappingWidth = text.getWrappingWidth();
		Double porcentXwrapping = ( wrappingWidth / 13.56 ) ;

		
		
		System.out.println(text.getWrappingWidth());
		
		anchorAltoForno.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
	        	Double porcentWidth = anchorAltoForno.getWidth() / 100;	        	
	        	text.setLayoutX( porcentXText * porcentWidth);
	        	text.setWrappingWidth( porcentXwrapping * porcentWidth );
	        	
	        	System.out.println(anchorAltoForno.getWidth() + "  " +  text.getLayoutX() );
	
	        }
		});
		anchorAltoForno.heightProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
	        	Double porcentHeight = anchorAltoForno.getHeight() / 100;
	        	
	        	text.setLayoutY(porcentHeight * porcentYText);
	        	
	        	System.out.println(anchorAltoForno.getHeight() + "  " +  text.getLayoutX() );
	
	        }
		});

		stage = UtilitarioNewView.getNewView(nameSupervisorio, mainScene = new Scene(tabPane));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*

		//Teste dimension
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//carregar imagens de fundo
		try {
			imageAltoForno = new Image(MainApp.class.getResource("gui/resources/" + strForno + ".png").toString());
			imageGlendon = new ImageView(new Image(MainApp.class.getResource("gui/resources/" + strGlendon + ".png").toString()));
		}catch (NullPointerException e){
			e.printStackTrace();
		}
		

		//definir tamanho para manter a dimension
//		imageAltoForno.setFitHeight(709);
//		imageAltoForno.setFitWidth(1360);
		imageGlendon.setFitHeight(709);
		imageGlendon.setFitWidth(1360);
		
		BackgroundImage back = new BackgroundImage(imageAltoForno, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT );
		
		//Carregar o scrollPane que vai ser a base de tudo
		ScrollPane scrollPane = (ScrollPane) UtilitarioNewView.loadFXML(fxml, new PrimaryViewController());
		
		//Navegar ate a imagem para inserir o fundo
		VBox vbox = (VBox) scrollPane.getContent();
		TabPane tabPane = (TabPane) vbox.getChildren().get(1);
		AnchorPane anchorAltoForno = (AnchorPane) tabPane.getTabs().get(0).getContent();
		AnchorPane anchorGlendons = (AnchorPane) tabPane.getTabs().get(1).getContent();	
		
//		anchorAltoForno.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
//	        @Override
//	        public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
//	        	System.out.println("Teste " + anchorAltoForno.getWidth() );
//	        	imageAltoForno.setFitWidth(anchorAltoForno.getWidth() / 3);
//
//	        }
//	    });
		Background backgroud = new Background(back);
		anchorAltoForno.setBackground(backgroud);
//		anchorAltoForno.getChildren().add(0, imageAltoForno);
		anchorGlendons.getChildren().add(0, imageGlendon);
		
		//Preparar a sena e showww
		stage = UtilitarioNewView.getNewView(nameSupervisorio, mainScene = new Scene(scrollPane));
	*/
		}
	
	public static void main(String[] args) {
		launch(args);
		System.exit(1);
	}
}
