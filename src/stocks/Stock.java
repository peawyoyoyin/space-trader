package stocks;

public class Stock {
	private String name;
	private int price;
	
	private int minPrice;
	private int maxPrice;
	
	private boolean sellable;
	private boolean buyable;
	
	public Stock(String name, int basePrice, int minPrice, int maxPrice) {
		this.name = name;
		this.price = basePrice;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
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
	
	public void increasePrice(int amount) {
		this.price += amount;
		if(this.price > this.maxPrice) {
			this.price = this.maxPrice;
		}
	}
	
	public void decreasePrice(int amount) {
		this.price -= amount;
		if(this.price < this.minPrice) {
			this.price = this.minPrice;
		}
	}
	
	public void setPrice(int price) {
		if(price > this.maxPrice) {
			price = this.maxPrice;
		} else if(price < this.minPrice) {
			price = this.minPrice;
		}
		this.price = price;
	}
}
