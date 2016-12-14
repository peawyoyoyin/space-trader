package stocks;

import constants.ConfigConstant;
import game.logic.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StockTradePanel extends GridPane {
	private ButtonStock buyButton;
	private ButtonStock sellButton;
	private Label stockNameLabel;

	private Stock stock;

	public StockTradePanel() {
		super();
		this.setPadding(new Insets(5));
		this.setHgap(20);
		this.setMinHeight(70);
		this.setAlignment(Pos.CENTER);

		this.buyButton = new ButtonStock("Buy");
		this.buyButton.setOnMouseClicked(event -> {
			this.buyStock();
		});
		this.sellButton = new ButtonStock("Sell");
		this.sellButton.setOnMouseClicked(event -> {
			this.sellStock();
		});
		this.stockNameLabel = new Label("Stock Name");
		this.stockNameLabel.setFont(ConfigConstant.Resource.STOCK_FONT);

		this.stock = null;

		this.add(stockNameLabel, 0, 0);
		this.add(buyButton, 1, 0);
		this.add(sellButton, 2, 0);
	}

	public void setDisableTrade(boolean disable) {
		this.buyButton.setDisable(disable);
		this.sellButton.setDisable(disable);
	}

	public void setStock(Stock stock) {
		this.stockNameLabel.setText(stock.getName());
		this.stock = stock;
	}

	public void buyStock() {
		System.out.println("Buy Stock");
		int price = this.stock.getPrice();
		if (Player.instance.getMoney() >= price) {
			Player.instance.removeMoney(price);
			PlayerStocksPortFolio.instance.getStockTradeData(this.stock).addInstock(1, price);
		} else {
			System.out.println("not enough money");
		}
	}

	public void sellStock() {
		int price = this.stock.getPrice();
		StockTradeData stockTradeData = PlayerStocksPortFolio.instance.getStockTradeData(this.stock);
		if (stockTradeData.getInstock() > 0) {
			stockTradeData.removeInstock(1);
			Player.instance.addMoney(price);
		} else {
			System.out.println("No stock to sell");
		}
	}
	
	class ButtonStock extends StackPane {
		public ButtonStock(String value) {
			// TODO Auto-generated constructor stub
			this.setPrefSize(50, 20);

			Canvas background = new Canvas(60, 30);
			background.getGraphicsContext2D().setFill(Color.rgb(201, 208, 217));
			background.getGraphicsContext2D().fillRoundRect(0, 0, 60, 30, 10, 5);
			this.getChildren().add(background);

			Text name = new Text(value);
			name.setFont(ConfigConstant.Resource.HUD_FONT);
			name.setFill(Color.rgb(70, 72, 78));
			this.getChildren().add(name);

			this.setEffect(new DropShadow());

			this.setOnMouseEntered(event -> {
				this.setEffect(new DropShadow(0.1, Color.BLACK));
			});
			this.setOnMouseExited(event -> {
				this.setEffect(new DropShadow());
			});
			this.setOnMousePressed(event -> {
				this.setEffect(new InnerShadow());
			});
			this.setOnMouseReleased(event -> {
				this.setEffect(new DropShadow(0.1, Color.BLACK));
			});
		}
	}
}
