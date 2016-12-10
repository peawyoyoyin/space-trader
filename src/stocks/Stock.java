package stocks;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Stock {
	
	public static final List<Stock> GAME_STOCKS = new ArrayList<>();
	
	public static final int MAX_HISTORY_SIZE = 40;
	
	private String name;
	private int price;
	
	private IntegerProperty priceProperty;
	
	private List<Integer> priceHistory;
	
	private int minPrice;
	private int maxPrice;
	
	private boolean sellable;
	private boolean buyable;
	
	public static void initializeGameStocks() {
		GAME_STOCKS.add(new Stock("JDT",15,10,50));
		GAME_STOCKS.add(new Stock("PGMTH",12,9,23));
		GAME_STOCKS.add(new Stock("CPS",40,32,70));
		System.out.println("GameStocks initialized");
	}
	
	public Stock(String name, int basePrice, int minPrice, int maxPrice) {
		this.name = name;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.priceHistory = new ArrayList<Integer>();
		this.buyable = true;
		this.sellable = true;
		this.priceProperty = new SimpleIntegerProperty();
		this.setNewPrice(basePrice);
		StockHolder.instance.getStocks().add(this);
	}
	
	public boolean isBuyable() {
		return this.buyable;
	}
	
	public List<Integer> getPriceHistory() {
		return priceHistory;
	}

	public boolean isSellable() {
		return this.sellable;
	}
	
	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}
	
	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void increasePrice(int amount) {
		int tempPrice = this.price + amount;
		if(tempPrice > this.maxPrice) {
			tempPrice = this.maxPrice;
		}
		this.setNewPrice(tempPrice);
	}
	
	public void decreasePrice(int amount) {
		int tempPrice = this.price - amount;
		if(tempPrice < this.minPrice) {
			tempPrice = this.minPrice;
		}
		this.setNewPrice(tempPrice);
	}
	
	public void setPrice(int price) {
		if(price > this.maxPrice) {
			price = this.maxPrice;
		} else if(price < this.minPrice) {
			price = this.minPrice;
		}
		this.setNewPrice(price);
	}
	
	private void setNewPrice(int price) {
		this.price = price;
		this.priceHistory.add(this.price);
		if(priceHistory.size() > MAX_HISTORY_SIZE) {
			priceHistory.remove(0);
		}
		this.priceProperty.set(price);
	}

	public double getMaxPrice() {
		return this.maxPrice;
	}

	public double getMinPrice() {
		return this.minPrice;
	}
	
	public IntegerProperty getPriceProperty() {
		return this.priceProperty;
	}
}
