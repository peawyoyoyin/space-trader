package market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.Item;
import game.Item.ItemType;
import game.Player;

public class Trader {
	private List<Item> itemsOnSale;
	private Map<ItemType, Double> sellPriceMultiplier;
	private Map<ItemType, Double> buyPriceMultiplier;
	private String name;
	private boolean isAccessing;
	
	private static final int ITEMS_ON_SALE = 6;

	public Trader(String name) {
		this.name = name;
		this.itemsOnSale = new ArrayList<>();
		this.isAccessing = false;
		this.sellPriceMultiplier = new HashMap<Item.ItemType, Double>();
		this.buyPriceMultiplier = new HashMap<Item.ItemType, Double>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Item> getItemsOnSale() {
		return this.itemsOnSale;
	}
	
	public void resetItemsOnSale() {
		this.itemsOnSale.clear();
	}
	
	public Map<ItemType,Double> getSellPriceMultipliers() {
		return this.sellPriceMultiplier;
	}
	
	public Map<ItemType, Double> getBuyPriceMultipliers() {
		return this.buyPriceMultiplier;
	}

	public void generateItems() {
		for (ItemType type : Item.ItemType.values()) {
			this.sellPriceMultiplier.put(type, 1.0);
			this.buyPriceMultiplier.put(type, 1.0);
		}
		
		for(int i=0; i<ITEMS_ON_SALE; i++) {
			this.itemsOnSale.add(new Item(ItemType.getRandomItemType(), 200));
		}
	}

	public Item playerBuyItem(Item item) {
		if (this.itemsOnSale.contains(item)) {
			int realPrice = (int) (item.getBasePrice() * this.buyPriceMultiplier.get(item.getItemType()));
			if (Player.instance.getMoney() >= realPrice) {
				Player.instance.removeMoney(realPrice);
				this.itemsOnSale.remove(item);
				return item;
			} else {
				return null;
			}
		} else {
			System.out.println("Warning: Someone tries to buy item that does not exist in trader: "+this.name);
			return null;
		}
	}

	public int playerSellItem(Item item) {
		return (int) ((int) item.getBasePrice() * this.sellPriceMultiplier.get(item.getItemType()));
	}
	
	public boolean isAccessing() {
		return this.isAccessing;
	}
	
	public void setAccessing(boolean accessing) {
		this.isAccessing = accessing;
	}
}
