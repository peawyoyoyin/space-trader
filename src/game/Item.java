package game;

import java.util.Random;

public class Item {
	public enum ItemType {
		MATERIAL, WEAPON, GEM, PARTS, JUNK
	}
	
	private ItemType itemType;
	private int basePrice;
	
	public Item(ItemType itemType, int basePrice) {
		this.itemType = itemType;
		this.basePrice = basePrice;
	}
	
	public static Item generate() {
		Random random = new Random();
		ItemType itemType = ItemType.values()[random.nextInt(ItemType.values().length)];
		return new Item(itemType, random.nextInt(1000)+150);
	}
	
	public static Item generate(ItemType itemType){
		Random random = new Random();
		return new Item(itemType, random.nextInt(1000)+150);
	}
}
