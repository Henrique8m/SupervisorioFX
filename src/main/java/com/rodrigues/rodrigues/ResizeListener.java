package com.rodrigues.rodrigues;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ResizeListener<T> implements ChangeListener<Number> {
	
	AnchorPane anchorAltoForno;
	int size;
	
	Text[] text;
	Double[] layoutX;
	Double[] layoutY;		
	Double[] porcentXText;
	Double[] porcentYText ;	
	Double[] wrappingWidth;	
	Double[] porcentXwrapping ;	
	
	 public ResizeListener(AnchorPane anchorAltoForno, int size) 
	 {		 
		 this.size = size;
		 text = new Text[size];
		 layoutX = new Double[size] ;
		 layoutY = new Double[size];	
		 wrappingWidth = new Double[size];	
		 porcentXText = new Double[size];;
		 porcentYText = new Double[size];
		 porcentXwrapping = new Double[size];	 
		 this.anchorAltoForno = anchorAltoForno;
		 for(int i = 0; i < size; i++) 
		 {
			 text[i] = (Text) anchorAltoForno.getChildren().get(i);
			 layoutX[i] = text[i].getLayoutX();
			 layoutY[i] = text[i].getLayoutY();
			 wrappingWidth[i] = text[i].getWrappingWidth();	
			 porcentXText[i] = ( layoutX[i] / MainApp.tamanhoWidth ) ;
			 porcentYText[i] = ( layoutY[i] / MainApp.tamanhoHeigt ) ;
			 porcentXwrapping[i] = ( wrappingWidth[i] / MainApp.tamanhoWidth ) ;
			 
		 }
		 
	}
	
	
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		Double porcentWidth = anchorAltoForno.getWidth() / 100;	
		Double porcentHeight = anchorAltoForno.getHeight() / 100;
		 for(int i = 0; i < ( size ) ; i++) 
		 {
			 text[i].setLayoutX( porcentXText[i] * porcentWidth);
			 text[i].setLayoutY(porcentHeight * porcentYText[i]);
			 text[i].setWrappingWidth( porcentXwrapping[i] * porcentWidth );
		 }
			
	}

}
