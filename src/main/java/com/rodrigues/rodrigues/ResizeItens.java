package com.rodrigues.rodrigues;

import java.io.IOException;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public abstract class ResizeItens extends Application {
	
	
	
	protected static void setRoot(String fxml, String title) throws IOException {
		Image imageAltoForno = null;
		Image imageGlendon = null;
	
			try {
				imageAltoForno = new Image(MainApp.class.getResource("gui/resources/" + MainApp.strForno + ".png").toString() );
				imageGlendon = new Image(MainApp.class.getResource("gui/resources/" + MainApp.strGlendon + ".png").toString() );
		
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true ,false);			
			BackgroundImage backgroundAltoForno = new BackgroundImage(imageAltoForno , BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT ,backgroundSize );
			BackgroundImage backgroundGlendon = new BackgroundImage(imageGlendon, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT ,backgroundSize );
			TabPane tabPane = (TabPane) UtilitarioNewView.loadFXML(fxml, new PrimaryViewController());
			AnchorPane anchorAltoForno = (AnchorPane) tabPane.getTabs().get(0).getContent();
			AnchorPane anchorGlendon = (AnchorPane) tabPane.getTabs().get(1).getContent();
			
			Background backgroud = new Background(backgroundAltoForno);				
			anchorAltoForno.setBackground(backgroud);
			anchorGlendon.setBackground( new Background( backgroundGlendon) );
			
			ResizeListener<Number> resizePgPrincipal = new ResizeListener<Number>(anchorAltoForno, anchorAltoForno.getChildren().size() -2 );			
			anchorAltoForno.widthProperty().addListener(resizePgPrincipal);			
			anchorAltoForno.heightProperty().addListener(resizePgPrincipal);
			ResizeListener<Number> resizeGlendon = new ResizeListener<Number>(anchorGlendon, anchorGlendon.getChildren().size() );			
			anchorGlendon.widthProperty().addListener(resizeGlendon);			
			anchorGlendon.heightProperty().addListener(resizeGlendon);
	
			MainApp.stage = UtilitarioNewView.getNewView(MainApp.nameSupervisorio, MainApp.mainScene = new Scene(tabPane));
			
		}

	public ResizeItens() {
		super();
	}

}