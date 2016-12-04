package main;


import constants.ConfigConstant;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import startScreen.StartScreen;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StartScreen startScreen = StartScreen.getInstace();
			Scene scene = new Scene(startScreen, ConfigConstant.startScreenWidth, ConfigConstant.startScreenHeight);
			primaryStage.initStyle(StageStyle.UNDECORATED);
//			primaryStage.setTitle("Project-Progmeth"); // Set the stage title
			primaryStage.setScene(scene); // Place the scene
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void toggleGamescreen(){
		
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
