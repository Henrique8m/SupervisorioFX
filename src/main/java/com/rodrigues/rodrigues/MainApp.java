package com.rodrigues.rodrigues;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainApp extends Application{
	private static Stage stage;
	private static Scene mainScene;

	public static Scene getMainScene() {
		return mainScene;
	}
	
   @Override
   public void start( Stage s) throws IOException {
       stage=s;
       setRoot("primary","");
   }

   static void setRoot(String fxml) throws IOException {
       setRoot(fxml,stage.getTitle());
   }

   static void setRoot(String fxml, String title) throws IOException {
	   ImageView image = new ImageView(new Image(MainApp.class.getResourceAsStream("FundoSupervisorioGlendon.png")));
            
	   ScrollPane scrollPane = loadFXML(fxml);
       mainScene = new Scene(scrollPane);
       stage.setTitle(" Supervisorio 1.0");
      // stage.setFullScreen(true);
       stage.setScene(mainScene);
       stage.show();
   }

   private static ScrollPane loadFXML(String fxml) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
       
       return fxmlLoader.load();
   }

    public static void main(String[] args) {
        launch(args);
    }
}
