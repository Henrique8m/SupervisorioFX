package com.rodrigues.rodrigues;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RecizeListener<T> implements ChangeListener<Number> {
	
	AnchorPane anchorAltoForno;
	
	Text T0, T1, T2 , T3, T4, T5, T6, T7, T8, T9;
	Double[] layoutX ;
	Double layoutY ;	
	Double wrappingWidth;	
	
	Double porcentXText;
	Double porcentYText;

	Double porcentXwrapping;
	
	
	 public RecizeListener(AnchorPane anchorAltoForno ) {

		 for(int i = 0; i < 9; i++) {
			 anchorAltoForno.getChildren().get(i);
			 
		 }
		 
		 

		 this.anchorAltoForno = anchorAltoForno;
		 layoutX = vazao.getLayoutX();
		 layoutY = vazao.getLayoutY();
		 wrappingWidth = vazao.getWrappingWidth();
		 porcentXText = ( layoutX / MainApp.tamanhoWidth ) ;
		 porcentYText = ( layoutY / MainApp.tamanhoHeigt ) ;
		 porcentXwrapping = ( wrappingWidth / 13.56 ) ;
	}
	
	
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		
		Double porcentWidth = anchorAltoForno.getWidth() / 100;	        	
    	    	vazao.setLayoutX( porcentXText * porcentWidth);
    	vazao.setWrappingWidth( porcentXwrapping * porcentWidth );
    	
    	System.out.println(anchorAltoForno.getWidth() + "  " +  vazao.getLayoutX() );
		
	}

}
