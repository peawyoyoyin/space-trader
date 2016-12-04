package stocksview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StockTradePanel extends GridPane {
	
	private Button buyButton;
	private Button sellButton;
	private Label stockNameLabel;
	
	public StockTradePanel() {
		super();
		this.setPadding(new Insets(5));
		this.setHgap(20);
		this.setMinHeight(70);
		this.setAlignment(Pos.CENTER_LEFT);
		this.setStyle("-fx-background-color: gray;");
		this.buyButton = new Button("Buy");
		this.sellButton = new Button("Sell");
		this.stockNameLabel = new Label("Stock Name");
		
		this.add(stockNameLabel, 0, 0, 2, 1);
		this.add(buyButton, 2, 0);
		this.add(sellButton, 3, 0);
	}
}
