package stocks;

import constants.ConfigConstant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class StockCell extends GridPane {

	private Label stockNameLabel;
	private Label stockPriceLabel;
	private StockChangeLabel stockChangeLabel;
	private Label instockLabel;
	private Stock stock;

	public StockCell(Stock stock) {
		super();
		this.setPadding(new Insets(0, 0, 0, 5));
		this.setMinHeight(40);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER_LEFT);
		this.stock = stock;
		this.stockNameLabel = new Label(this.stock.getName());

		/*
		 * stockPriceLabel's textProperty is bound to the priceProperty of the
		 * stock so we do not need to constantly update its text
		 */
		this.stockPriceLabel = new Label(Integer.toString(this.stock.getPrice()));
		this.stockPriceLabel.textProperty().bind(this.stock.getPriceProperty().asString());

		this.stockChangeLabel = new StockChangeLabel();

		/*
		 * instockLabel's textProperty is bound to the StockTradeData's
		 * InstockProperty so we do not need to constantly update its text
		 */
		this.instockLabel = new Label(
				Integer.toString(PlayerStocksPortFolio.instance.getStockTradeData(stock).getInstock()));
		this.instockLabel.textProperty()
				.bind(PlayerStocksPortFolio.instance.getStockTradeData(stock).getInstockProperty().asString());

		this.add(stockNameLabel, 0, 0);
		this.add(stockPriceLabel, 1, 0);
		this.add(stockChangeLabel, 2, 0);
		this.add(instockLabel, 3, 0);

		/* 
		 * add ChangeListener to stockPriceLabel's textProperty so it calculate
		 * changes every time the price changes
		 */
		this.stockPriceLabel.textProperty().addListener(event -> {
			StockTradeData data = PlayerStocksPortFolio.instance.getStockTradeData(stock);
			double boughtPrice = data.getBoughtPrice();
			int price = stock.getPrice();
			double change = (price - boughtPrice) / boughtPrice * 100;
			if (data.getInstock() == 0) {
				change = 0;
			}
			stockChangeLabel.setChange(change);
		});
		
		
		this.stockNameLabel.setFont(ConfigConstant.Resource.STOCK_FONT);
		this.stockPriceLabel.setFont(ConfigConstant.Resource.STOCK_FONT);
		this.instockLabel.setFont(ConfigConstant.Resource.STOCK_FONT);
		this.stockChangeLabel.setFont(ConfigConstant.Resource.STOCK_FONT);
		this.getColumnConstraints().add(new ColumnConstraints(65));
		this.getColumnConstraints().add(new ColumnConstraints(35));
		this.getColumnConstraints().add(new ColumnConstraints(35));
		this.getColumnConstraints().add(new ColumnConstraints(30));
	}

	public void setActive(boolean active) {
		if (active) {
			this.setEffect(new DropShadow(5, Color.AQUAMARINE));
		} else {
			this.setEffect(null);
		}
	}

	public Stock getStock() {
		return this.stock;
	}
}

/**
 * StockChangeLabel is basically a Label that changes its colour according to
 * its values
 */
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
		this.setText(Integer.toString((int) change));
	}

	public void updateTextColor() {
		if (this.change < 0) {
			this.setTextFill(Color.RED);
		} else if (this.change > 0) {
			this.setTextFill(Color.GREEN);
		} else {
			this.setTextFill(Color.ORANGE);
		}
	}
}