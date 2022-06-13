package com.rodrigues.rodrigues;

import java.io.IOException;

import com.rodrigues.rodrigues.gui.PrimaryViewController;
import com.rodrigues.rodrigues.serial.utilitary.UtilitarioNewView;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class RecizeItens extends Application {
	protected static void setRoot(String fxml, String title) throws IOException {
		Text cf1, cf2, cf3, 
		cm1, cm2, cm3, 
		cq1, cq2, cq3,
		s1, s2, s3,
		topoE, topoD, topoE1, topoD1,
		coroaE, coroaD, coroaE1, coroaD1,
		pcoroa1, ptopo1, pcoroa, ptopo,
		vazao, vazao1,
		psm, psm1,
		pirometro, pirometro1,
		txLog, txLog1,
		Balanca01, Balanca02, Balanca03, Balanca04, Balanca05,
		Balanca06, Balanca07, Balanca08, Balanca09, Balanca10; 
	
			try {
				MainApp.imageAltoForno = new Image(MainApp.class.getResource("gui/resources/" + MainApp.strForno + ".png").toString());
		
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			BackgroundSize size = new BackgroundSize(100, 100, true, true, true ,false);
			
			BackgroundImage backAltoForno = new BackgroundImage(MainApp.imageAltoForno, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT ,size );
	
			TabPane tabPane = (TabPane) UtilitarioNewView.loadFXML(fxml, new PrimaryViewController());
			AnchorPane anchorAltoForno = (AnchorPane) tabPane.getTabs().get(0).getContent();
			
			Background backgroud = new Background(backAltoForno);		
			anchorAltoForno.setBackground(backgroud);
			
			vazao = (Text) anchorAltoForno.getChildren().get(0);
			Double layoutX = vazao.getLayoutX();
			Double layoutY = vazao.getLayoutY();	
			
			
			Double porcentXText = ( layoutX / MainApp.tamanhoWidth ) ;
			Double porcentYText = ( layoutY / MainApp.tamanhoHeigt ) ;
			Double wrappingWidth = vazao.getWrappingWidth();
			Double porcentXwrapping = ( wrappingWidth / 13.56 ) ;
	
			
			
			System.out.println(vazao.getWrappingWidth());
			
			RecizeListener<Number> recize = new RecizeListener<Number>(anchorAltoForno);
			
			anchorAltoForno.widthProperty().addListener(recize);
			
			anchorAltoForno.heightProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
		        @Override
		        public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
		        	Double porcentHeight = anchorAltoForno.getHeight() / 100;
		        	
		        	vazao.setLayoutY(porcentHeight * porcentYText);
		        	
		        	System.out.println(anchorAltoForno.getHeight() + "  " +  vazao.getLayoutX() );
		
		        }
			});
	
			MainApp.stage = UtilitarioNewView.getNewView(MainApp.nameSupervisorio, MainApp.mainScene = new Scene(tabPane));
			
		}

	public RecizeItens() {
		super();
	}

}