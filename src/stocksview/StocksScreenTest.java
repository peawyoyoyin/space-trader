package stocksview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import market.Market;
import stocks.Stock;

public class StocksScreenTest extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		StocksScreen stocksScreen = StocksScreen.instance;
		stocksScreen.getStocksList().addStockCell(new StockCell(Stock.STOCK_PLACEHOLDER_2));
		stocksScreen.getStocksList().addStockCell(new StockCell(Stock.STOCK_PLACEHOLDER));
		root.getChildren().add(stocksScreen);
		
		Market.InitializeMarket();
		
		Scene scene = new Scene(root,250,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
