package game;

import java.util.Random;

import constants.ConfigConstant;
import javafx.scene.image.Image;

public class Item {
	public enum ItemType {
		MATERIAL, WEAPON, GEM, PARTS, JUNK;
		
		public static Image getItemIcon(ItemType type) {
			Image image = null;
			
			switch(type) {
			case MATERIAL: {
				image = ConfigConstant.Resource.ITEM_MATERIAL;
				break;
			}
			case WEAPON : {
				image = ConfigConstant.Resource.ITEM_WEAPON;
				break;
			}
			case GEM : {
				image = ConfigConstant.Resource.ITEM_GEM;
				break;
			}
			case PARTS : {
				image = ConfigConstant.Resource.ITEM_PARTS;
				break;
			}
			case JUNK : {
				image = ConfigConstant.Resource.ITEM_JUNK;
				break;
			}
			default : {
				System.out.println("Warning: bad item type in getItemIcon");
				image = ConfigConstant.Resource.ITEM_JUNK;
				break;
			}
			}
			
			return image;
		}
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
	
	public int getBasePrice() {
		return this.basePrice;
	}
	
	public ItemType getItemType() {
		return this.itemType;
	}
}
