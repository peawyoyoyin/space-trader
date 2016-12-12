package game.model;

import java.util.Random;

import constants.ConfigConstant;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import game.logic.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Item extends Entity implements Renderable {
	public enum ItemType {
		MATERIAL, WEAPON, GEM, PARTS, JUNK;

		public static ItemType getRandomItemType() {
			Random random = new Random();
			return ItemType.values()[random.nextInt(ItemType.values().length)];
		}

		public static String toString(ItemType itemType) {
			switch (itemType) {
			case MATERIAL: {
				return "materials";
			}
			case WEAPON: {
				return "weapons";
			}
			case GEM: {
				return "gems";
			}
			case PARTS: {
				return "parts";
			}
			case JUNK: {
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
	private double direction;

	public Item(ItemType itemType) {
		this.itemType = itemType;
	}

	public static Item generate() {
		Random random = new Random();
		ItemType itemType = ItemType.values()[random.nextInt(ItemType.values().length)];
		return new Item(itemType);
	}

	public static Item generateEnitity(double x, double y) {
		Random random = new Random();
		ItemType itemType = ItemType.values()[random.nextInt(ItemType.values().length)];
		Item item = new Item(itemType);
		item.setX(x);
		item.setY(y);
		item.setZ(-100);
		item.radius = 20;
		item.direction = 225;
		return item;
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		for (Entity entity : mc.getEntities()) {
			if (entity instanceof PlayerShip && this.isCollideWith(entity)) {
				this.destroyed = true;
				Player.instance.addItemtoInventory(this);
			}
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.rotate(this.direction);
		gc.drawImage(getItemIcon(), -getItemIcon().getWidth()/2, -getItemIcon().getHeight()/2);
		gc.rotate(-this.direction);
		gc.translate(-x, -y);
	}
}
