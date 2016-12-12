package gamedata;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import stocks.Stock;

public class StockTradeData {
	private Stock stock;
	private double boughtPrice;
	private int inStock;
	
	private IntegerProperty inStockProperty;
	
	public StockTradeData(Stock stock) {
		this.stock = stock;
		this.inStock = 0;
		this.boughtPrice = 0;
		this.inStockProperty = new SimpleIntegerProperty();
		this.inStockProperty.set(0);
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public void addInstock(int amount, int price) {
		if(this.inStock == 0) {
			this.boughtPrice = price;
		} else {
			//new boughtPrice is average calculated from old boughtPrice
			this.boughtPrice = (this.boughtPrice*this.inStock + price*amount) / (this.inStock+amount);
		}
		this.inStock+=amount;
		this.inStockProperty.set(this.inStock);
	}
	
	public void removeInstock(int amount) {
		this.inStock -= amount;
		if(this.inStock < 0) {
			this.inStock = 0;
		}
		if(this.inStock == 0) {
			this.boughtPrice = 0;
		}
		this.inStockProperty.set(this.inStock);
	}
	
	public double getBoughtPrice() {
		return this.boughtPrice;
	}
		
	public int getInstock() {
		return this.inStock;
	}
	
	public IntegerProperty getInstockProperty() {
		return this.inStockProperty;
	}
}