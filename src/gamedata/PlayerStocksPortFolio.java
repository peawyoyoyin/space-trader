package gamedata;

import java.util.ArrayList;
import java.util.List;

import stocks.Stock;
import stocks.StockHolder;

public class PlayerStocksPortFolio {
	
	List<StockTradeData> allTradeData;
	
	public PlayerStocksPortFolio() {
		this.allTradeData = new ArrayList<StockTradeData>();
		for (Stock stock : StockHolder.getInstance().getStocks()) {
			this.allTradeData.add(new StockTradeData(stock));
		}
	}
	
	public StockTradeData getStockTradeData(Stock stock) {
		for (StockTradeData stockTradeData : allTradeData) {
			if(stockTradeData.getStock() == stock) {
				return stockTradeData;
			}
		}
		return null;
	}
	
}



class StockTradeData {
	private Stock stock;
	private double boughtPrice;
	private int inStock;
	
	public StockTradeData(Stock stock) {
		this.stock = stock;
		this.inStock = 0;
		this.boughtPrice = 0;
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public void addInstock(int amount) {
		if(this.inStock == 0) {
			this.boughtPrice = this.stock.getPrice();
		} else {
			//new boughtPrice is average calculated from old boughtprice
			this.boughtPrice = (this.boughtPrice*this.inStock + this.stock.getPrice()*amount) / this.inStock+amount;
		}
		this.inStock+=amount;
	}
	
	public void removeInstock(int amount) {
		this.inStock -= amount;
		if(this.inStock < 0) {
			this.inStock = 0;
		}
		if(this.inStock == 0) {
			this.boughtPrice = 0;
		}
	}
	
	public double getBoughtPrice() {
		return this.boughtPrice;
	}
		
	public int getInstock() {
		return this.inStock;
	}
}