package stocks;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sun.java2d.cmm.ColorTransform;

public class StocksScreen extends BorderPane {
	
	public static final StocksScreen instance = new StocksScreen();
	
	private StocksList stocksList;
	private StockGraph stocksGraph;
	private StockTradePanel stockTradePanel;
	
	public StocksScreen() {
		super();
		this.setStyle("-fx-background-color: gray;");
		this.stocksList = new StocksList();
		this.stocksGraph = new StockGraph();
		this.stockTradePanel = new StockTradePanel();
		this.setTop(stocksList);
		this.setCenter(stocksGraph);
		this.setBottom(stockTradePanel);
	}
	
	public StocksList getStocksList() {
		return this.stocksList;
	}
	
	public StockGraph getStockGraph() {
		return this.stocksGraph;
	}
	
	public StockTradePanel getStockTradePanel() {
		return this.stockTradePanel;
	}
	
	public class StocksList extends VBox {
		public StocksList() {
			super();
		}
		
		public void addStockCell(StockCell stockCell) {
			this.getChildren().add(stockCell);
			stockCell.setOnMouseClicked(event -> {
				StocksScreen.instance.getStockGraph().setStock(stockCell.getStock());
				StocksScreen.instance.getStockTradePanel().setStock(stockCell.getStock());
				StocksScreen.instance.getStockGraph().updateGraph();
			});
		}
	}
}

