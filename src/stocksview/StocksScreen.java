package stocksview;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import stocks.Stock;

public class StocksScreen extends BorderPane {
	
	private StocksList stocksList;
	private StockGraph stocksGraph;
	private StockTradePanel stockTradePanel;
	
	public StocksScreen() {
		super();
		this.setStyle("-fx-background-color: black;");
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
}

class StocksList extends VBox {
	public StocksList() {
		super();
	}
	
	public void addStockCell(StockCell stockCell) {
		this.getChildren().add(stockCell);
	}
}

class StockCell extends GridPane {
	
	private Label stockNameLabel;
	private Label stockPriceLabel;
	private StockChangeLabel stockChangeLabel;
	private Stock stock;
	
	public StockCell() {
		super();
		this.setStyle("-fx-background-color: gray;");
		this.setMinHeight(40);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER_LEFT);
		this.stock = null;
		this.stockNameLabel = new Label("Stock Name");
		this.stockPriceLabel = new Label("15.00");
		this.stockChangeLabel = new StockChangeLabel();
		this.add(stockNameLabel, 0, 0, 2, 1);
		this.add(stockPriceLabel, 2, 0);
		this.add(stockChangeLabel, 3, 0);
	}
	
	public StockCell(Stock stock) {
		super();
		this.setStyle("-fx-background-color: gray;");
		this.setMinHeight(40);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER_LEFT);
		this.stock = stock;
		this.stockNameLabel = new Label(this.stock.getName());
		this.stockPriceLabel = new Label(Integer.toString(this.stock.getPrice()));
		this.stockChangeLabel = new StockChangeLabel();
		this.add(stockNameLabel, 0, 0, 2, 1);
		this.add(stockPriceLabel, 2, 0);
		this.add(stockChangeLabel, 3, 0);
	}
}

class StockChangeLabel extends Label {
	private double change;
	
	public StockChangeLabel(double change) {
		super();
		this.setChange(change);
	}
	
	public StockChangeLabel() {
		super();
		this.setChange(0);
	}
	
	public void setChange(double change) {
		this.change = change;
		this.updateTextColor();
		this.setText(Double.toString(change));
	}
	
	public void updateTextColor() {
		if(this.change < 0) {
			this.setTextFill(Color.RED);
		} else if(this.change > 0) {
			this.setTextFill(Color.GREEN);
		} else {
			this.setTextFill(Color.ORANGE);
		}
	}
}