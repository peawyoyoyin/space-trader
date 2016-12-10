package game.logic;

import java.util.Random;

import constants.ConfigConstant;
import javafx.scene.image.Image;

public class Item {
	public enum ItemType {
		MATERIAL, WEAPON, GEM, PARTS, JUNK;

		public static ItemType getRandomItemType() {
			Random random = new Random();
			return ItemType.values()[random.nextInt(ItemType.values().length)];
		}

		public static String toString(ItemType itemType) {
			switch(itemType) {
			case MATERIAL : {
				return "materials";
			}
			case WEAPON : {
				return "weapons";
			}
			case GEM : {
				return "gems";
			}
			case PARTS : {
				return "parts";
			}
			case JUNK : {
				return "junk";
			}
			default: {
				return "NoneType";
			}
			}
		}

		public static Image getItemIcon(ItemType type) {
			Image image = null;

			switch (type) {
			case MATERIAL: {
				image = ConfigConstant.Resource.ITEM_MATERIAL;
				break;
			}
			case WEAPON: {
				image = ConfigConstant.Resource.ITEM_WEAPON;
				break;
			}
			case GEM: {
				image = ConfigConstant.Resource.ITEM_GEM;
				break;
			}
			case PARTS: {
				image = ConfigConstant.Resource.ITEM_PARTS;
				break;
			}
			case JUNK: {
				image = ConfigConstant.Resource.ITEM_JUNK;
				break;
			}
			default: {
				System.out.println("Warning: bad item type in getItemIcon");
				image = ConfigConstant.Resource.ITEM_JUNK;
				break;
			}
			}

			return image;
		}
	}

	private ItemType itemType;

	@Deprecated
	public Item(ItemType itemType, int basePrice) {
		this(itemType);
	}

	public Item(ItemType itemType) {
		this.itemType = itemType;
	}

	public static Item generate() {
		Random random = new Random();
		ItemType itemType = ItemType.values()[random.nextInt(ItemType.values().length)];
		return new Item(itemType);
	}

	public static Item generate(ItemType itemType) {
		return new Item(itemType);
	}

	public ItemType getItemType() {
		return this.itemType;
	}

	public Image getItemIcon() {
		return ItemType.getItemIcon(this.itemType);
	}
}
