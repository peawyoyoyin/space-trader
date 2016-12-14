package stocks;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Stock {
	
	private static final List<Stock> GAME_STOCKS = new ArrayList<>();
	public static final int MAX_HISTORY_SIZE = 40;
	
	public static void initializeGameStocks() {
		GAME_STOCKS.clear();
		GAME_STOCKS.add(new Stock("JTC",15,10,50));
		GAME_STOCKS.add(new Stock("PGMTH",12,9,23));
		GAME_STOCKS.add(new Stock("CPS",40,32,70));
		System.out.println("GameStocks initialized");
	}
	
	private String name;
	private int price;
	
	//priceProperty is used for the ease of binding to texts
	private IntegerProperty priceProperty;
	
	private List<Integer> priceHistory;
	
	private int minPrice;
	private int maxPrice;
	
	public Stock(String name, int basePrice, int minPrice, int maxPrice) {
		this.name = name;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.priceHistory = new ArrayList<Integer>();
		this.priceProperty = new SimpleIntegerProperty();
		this.setNewPrice(basePrice);
		StockHolder.instance.getStocks().add(this);
	}
	
	public List<Integer> getPriceHistory() {
		return priceHistory;
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
	
	/**
	 * setNewPrice sets the price of the stock to a new Price,
	 * and adds the price the its priceHistory, this way prices
	 * are recorded for the sake of plotting it on StockGraph.
	 * 
	 * public methods increasePrice, decreasePrice and setPrice calls setNewPrice
	 * 
	 * @param price the price to set, usually automatically passed by the public set methods
	 */
	private void setNewPrice(int price) {
		
		this.price = price;
		
		//add price to priceHistory
		this.priceHistory.add(this.price);
		
		//resize priceHistory so it isn't to large
		if(priceHistory.size() > MAX_HISTORY_SIZE) {
			priceHistory.remove(0);
		}
		
		this.priceProperty.set(price);
	}

	public int getMaxPrice() {
		return this.maxPrice;
	}

	public int getMinPrice() {
		return this.minPrice;
	}
	
	public IntegerProperty getPriceProperty() {
		return this.priceProperty;
	}
}
