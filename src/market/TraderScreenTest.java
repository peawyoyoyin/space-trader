package market;

import constants.ConfigConstant;
import game.logic.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TraderScreenTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		
		Market.InitializeMarket();
		
		Player.instance.setMoney(20000000);
		
		Trader trader = new Trader("Trader!!!!!");
		trader.generateItems();
		
		TraderScreen traderScreen = new TraderScreen(trader);
		root.getChildren().add(traderScreen);
		Scene scene = new Scene(root, ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		for (StockUpdater stockUpdater : Market.getStockUpdaters()) {
			stockUpdater.interrupt();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
