package stocksview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StocksScreenTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		StocksScreen stocksScreen = new StocksScreen();
		root.getChildren().add(stocksScreen);
		Scene scene = new Scene(root,250,500);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
