package com.rodrigues.rodrigues;

import java.io.IOException;

import com.rodrigues.rodrigues.model.dto.UserResponse;
import com.rodrigues.rodrigues.model.entities.User;
import com.rodrigues.rodrigues.model.service.UserService;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.rodrigues.rodrigues.controller.UserController;
import com.rodrigues.rodrigues.model.dto.UserRequest;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainApp extends Application{
	private static Stage stage;
	private static Scene mainScene;
	private final SpringContext spring = new SpringContext();
	private ConfigurableApplicationContext applicationContext;
	
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
	   ScrollPane scrollPane = loadFXML(fxml);
       mainScene = new Scene(scrollPane);
       stage.setTitle(" Supervisorio 1.0");
      // stage.setFullScreen(true);
       stage.setScene(mainScene);
       stage.show();
   }

   private static ScrollPane loadFXML(String fxml) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
       return fxmlLoader.load();
   }
   
    //Spring
	@Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
        
        	//ac.registerBean(Application.class, () -> MainApp.this);
        	ac.registerBean(UserService.class, () -> new UserService());
            ac.registerBean(UserResponse.class, () -> new UserResponse());
            ac.registerBean(UserRequest.class, () -> new UserRequest());
            ac.registerBean(User.class, () -> new User());
        	//ac.registerBean("UserControler",UserController.class, () -> new UserController(new UserRequest()));
            ac.registerBean(Parameters.class, this::getParameters);
            ac.registerBean(HostServices.class, this::getHostServices);

        };
        this.applicationContext = new SpringApplicationBuilder().sources(Springinit.class)
                .initializers(initializer).run(getParameters().getRaw().toArray(new String[0]));
     spring.setApplicationContext(applicationContext); 
    }
	
    public static void main(String[] args) {
       launch(args);
    }
}
