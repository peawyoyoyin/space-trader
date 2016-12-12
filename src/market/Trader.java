package market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import game.logic.Player;
import game.model.Item;
import game.model.Item.ItemType;

public class Trader {

	private static final Map<ItemType, Double> MAX_SELL_MULT = new HashMap<ItemType,Double>();
	private static final Map<ItemType, Double> MIN_SELL_MULT = new HashMap<ItemType,Double>();
	private static final Map<ItemType, Double> MAX_BUY_MULT = new HashMap<ItemType,Double>();
	private static final Map<ItemType, Double> MIN_BUY_MULT = new HashMap<ItemType,Double>();
	private static List<Trader> allTraders;
	
	static {
		for (ItemType type : ItemType.values()) {
			MAX_SELL_MULT.put(type, 1.5);
			MIN_SELL_MULT.put(type, 0.25);
			MAX_BUY_MULT.put(type, 2.0);
			MIN_BUY_MULT.put(type, 0.75);
		}
	}
	
	private List<Item> itemsOnSale;
	private Map<ItemType, Double> sellPriceMultiplier;
	private Map<ItemType, Double> buyPriceMultiplier;
	private String name;
	private boolean isAccessing;

	private static final int ITEMS_ON_SALE = 6;
	
	public static double getMaxBuyPriceMultiplier(ItemType type) {
		return MAX_BUY_MULT.get(type);
	}
	
	public static double getMinBuyPriceMultiplier(ItemType type) {
		return MIN_BUY_MULT.get(type);
	}
	
	public static double getMaxSellPriceMultiplier(ItemType type) {
		return MAX_SELL_MULT.get(type);
	}
	
	public static double getMinSellPriceMultiplier(ItemType type) {
		return MIN_SELL_MULT.get(type);
	}

	public static Trader getRandomTrader() {
		Random random = new Random();
		return allTraders.get(random.nextInt(allTraders.size()));
	}

	public Trader(String name) {
		this.name = name;
		this.itemsOnSale = new ArrayList<>();
		this.isAccessing = false;
		this.sellPriceMultiplier = new HashMap<Item.ItemType, Double>();
		this.buyPriceMultiplier = new HashMap<Item.ItemType, Double>();
		for (ItemType type : Item.ItemType.values()) {
			this.sellPriceMultiplier.put(type, 0.5);
			this.buyPriceMultiplier.put(type, 1.0);
		}

		if (allTraders == null) {
			allTraders = new ArrayList<>();
		}
		allTraders.add(this);
		this.generateItems();
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

	public Map<ItemType, Double> getSellPriceMultipliers() {
		return this.sellPriceMultiplier;
	}

	public Map<ItemType, Double> getBuyPriceMultipliers() {
		return this.buyPriceMultiplier;
	}

	public void generateItems() {
		for (int i = 0; i < ITEMS_ON_SALE; i++) {
			this.itemsOnSale.add(new Item(ItemType.getRandomItemType()));
		}
	}

	public Item playerBuyItem(Item item) {
		if (this.itemsOnSale.contains(item)) {
			int realPrice = (int) (Market.getItemPrice(item.getItemType())
					* this.buyPriceMultiplier.get(item.getItemType()));
			if (Player.instance.getMoney() >= realPrice) {
				Player.instance.removeMoney(realPrice);
				this.itemsOnSale.remove(item);
				return item;
			} else {
				return null;
			}
		} else {
			System.out.println("Warning: Someone tries to buy item that does not exist in trader: " + this.name);
			return null;
		}
	}

	public int playerSellItem(Item item) {
		return (int) ((int) Market.getItemPrice(item.getItemType()) * this.sellPriceMultiplier.get(item.getItemType()));
	}

	public boolean isAccessing() {
		return this.isAccessing;
	}

	public void setAccessing(boolean accessing) {
		this.isAccessing = accessing;
	}
}
