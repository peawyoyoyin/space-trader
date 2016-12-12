package stocks;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
		private StockCell currentCell;

		public StocksList() {
			super();
		}

		public void addStockCell(StockCell stockCell) {
			this.getChildren().add(stockCell);
			stockCell.setOnMouseClicked(event -> {
				StocksScreen.instance.getStockGraph().setStock(stockCell.getStock());
				StocksScreen.instance.getStockTradePanel().setStock(stockCell.getStock());
				StocksScreen.instance.getStockGraph().updateGraph();
				currentCell.setActive(false);
				currentCell = stockCell;
				currentCell.setActive(true);
			});
			if (this.currentCell == null) {
				this.currentCell = stockCell;
				this.currentCell.setActive(true);
				StocksScreen.instance.getStockGraph().setStock(stockCell.getStock());
				StocksScreen.instance.getStockTradePanel().setStock(stockCell.getStock());
				StocksScreen.instance.getStockGraph().updateGraph();
			}
		}
	}

}
