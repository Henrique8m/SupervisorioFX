package com.rodrigues.rodrigues.serial.utilitary;

import java.io.IOException;

import com.rodrigues.rodrigues.MainApp;
import com.rodrigues.rodrigues.gui.util.Alerts;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UtilitarioNewView {
	
	public static synchronized <T> Object loadFXML(String fxml, Object controller) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("gui/" + fxml + ".fxml"));
		fxmlLoader.setController(controller);
		return fxmlLoader.load();
	}

	public static void getNewViewModal(String title, Pane pane, Stage stageEvent){
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.setScene(new Scene(pane, 400, 400));
		stage.setResizable(false);
		stage.initOwner(stageEvent);
		stage.initModality(Modality.WINDOW_MODAL);			
		stage.initStyle(StageStyle.UTILITY);
		stage.setAlwaysOnTop(true);						
		stage.showAndWait();
	}
	
	public static Stage getNewView(String title, Scene mainScene){
		Stage stage = new Stage();
		stage.setMaximized(true);
		stage.setTitle(title);
		stage.setScene(mainScene);
		stage.show();
		return stage;
	}

}
