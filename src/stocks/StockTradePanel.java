package stocks;

import game.Player;
import gamedata.PlayerStocksPortFolio;
import gamedata.StockTradeData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StockTradePanel extends GridPane {
	
	private Button buyButton;
	private Button sellButton;
	private Label stockNameLabel;
	
	private Stock stock;
	
	public StockTradePanel() {
		super();
		this.setPadding(new Insets(5));
		this.setHgap(20);
		this.setMinHeight(70);
		this.setAlignment(Pos.CENTER_LEFT);
		this.setStyle("-fx-background-color: gray;");
		this.buyButton = new Button("Buy");
		this.buyButton.setOnAction(event -> {
			this.buyStock();
		});
		this.sellButton = new Button("Sell");
		this.sellButton.setOnAction(event -> {
			this.sellStock();
		});
		this.stockNameLabel = new Label("Stock Name");
		
		this.stock = null;
		
		this.add(stockNameLabel, 0, 0, 2, 1);
		this.add(buyButton, 2, 0);
		this.add(sellButton, 3, 0);
	}
	
	public void setStock(Stock stock) {
		this.stockNameLabel.setText(stock.getName());
		this.stock = stock;
	}
	
	public void buyStock() {
		System.out.println("Buy Stock");
		int price = this.stock.getPrice();
		if(Player.instance.getMoney() >= price) {
			Player.instance.removeMoney(price);
			PlayerStocksPortFolio.instance.getStockTradeData(this.stock).addInstock(1, price);
		} else {
			System.out.println("not enough money");
		}
	}
	
	public void sellStock() {
		int price = this.stock.getPrice();
		StockTradeData stockTradeData = PlayerStocksPortFolio.instance.getStockTradeData(this.stock);
		if(stockTradeData.getInstock() > 0) {
			stockTradeData.removeInstock(1);
			Player.instance.addMoney(price);
		} else {
			System.out.println("No stock to sell");
		}
	}
}
