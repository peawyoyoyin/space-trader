package stocks;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	private String name;
	private int price;
	
	private List<Integer> priceHistory;
	
	private int minPrice;
	private int maxPrice;
	
	private boolean sellable;
	private boolean buyable;
	
	public Stock(String name, int basePrice, int minPrice, int maxPrice) {
		this.name = name;
		this.price = basePrice;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.priceHistory = new ArrayList<Integer>();
		this.buyable = true;
		this.sellable = true;
	}
	
	public boolean isBuyable() {
		return this.buyable;
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
		this.priceHistory.add(this.price);
		this.price = price;
		if(priceHistory.size() > 5) {
			priceHistory.remove(0);
		}
	}
}
