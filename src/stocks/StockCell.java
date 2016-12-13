package stocks;

import constants.ConfigConstant;
import gamedata.PlayerStocksPortFolio;
import gamedata.StockTradeData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class StockCell extends GridPane {
	
	private Label stockNameLabel;
	private Label stockPriceLabel;
	private StockChangeLabel stockChangeLabel;
	private Label instockLabel;
	private Stock stock;
	
	public void setActive(boolean active) {
		if(active) {
//			this.setStyle("-fx-background-color: lightgray;");
			this.setEffect(new DropShadow(5, Color.AQUAMARINE));
		} else {
//			this.setStyle("-fx-background-color: gray;");
			this.setEffect(null);
		}
	}
	
	public StockCell(Stock stock) {
		super();
//		this.setStyle("-fx-background-color: gray;");
		this.setPadding(new Insets(0,0,0,5));
		this.setMinHeight(40);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER_LEFT);
		this.stock = stock;
		this.stockNameLabel = new Label(this.stock.getName());
		this.stockPriceLabel = new Label(Integer.toString(this.stock.getPrice()));
		this.stockPriceLabel.textProperty().bind(this.stock.getPriceProperty().asString());
		this.stockChangeLabel = new StockChangeLabel();
		


		this.instockLabel = new Label(Integer.toString(PlayerStocksPortFolio.instance.getStockTradeData(stock).getInstock()));
		this.instockLabel.textProperty().bind(PlayerStocksPortFolio.instance.getStockTradeData(stock).getInstockProperty().asString());
		this.add(stockNameLabel, 0, 0, 2, 1);
		this.add(stockPriceLabel, 2, 0);
		this.add(stockChangeLabel, 3, 0);
		this.add(instockLabel, 4, 0);
		
		this.stockPriceLabel.textProperty().addListener(event -> {
			StockTradeData data = PlayerStocksPortFolio.instance.getStockTradeData(stock);
			double boughtPrice = data.getBoughtPrice();
			int price = stock.getPrice();
			double change = (price-boughtPrice)/boughtPrice*100;
			if(data.getInstock() == 0) {
				change = 0;
			}
			stockChangeLabel.setChange(change);
		});
		
		this.stockNameLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		this.stockPriceLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		this.instockLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		this.stockChangeLabel.setFont(ConfigConstant.Resource.HUD_FONT);
	}
	
	public Stock getStock() {
		return this.stock;
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
		this.setText(Integer.toString((int) change));
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