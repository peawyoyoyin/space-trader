package launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LauncherScreen extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		BottomPane bottomPane = new BottomPane();
		root.setBottom(bottomPane);
		
		TabbedSplashScreen tabbedSplashScreen = new TabbedSplashScreen();
		root.setCenter(tabbedSplashScreen);
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("GameName Launcher");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}