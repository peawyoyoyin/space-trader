package game.logic;

import java.util.HashMap;
import java.util.Map;

import constants.ConfigConstant;
import game.model.Item;
import game.model.Item.ItemType;
import game.model.PlayerShip;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
	public static Player instance = new Player();
	private String playerName;
	private boolean isPause;
	private double bulletSpeed;
	private PlayerShip playerShip;

	private IntegerProperty sectionXProperty;
	private IntegerProperty sectionYProperty;

	private Map<ItemType, Integer> inventory;
	private Map<ItemType, IntegerProperty> inventoryProperties;

	private IntegerProperty moneyProperty;
	private IntegerProperty bulletDamageProperty;

	public Player() {
		this.playerName = ConfigConstant.PLAYER_NAME;
		this.bulletSpeed = 20;
		this.inventory = new HashMap<>();
		this.inventoryProperties = new HashMap<>();
		for (ItemType type : ItemType.values()) {
			inventory.put(type, 0);
			inventoryProperties.put(type, new SimpleIntegerProperty(0));
		}
		this.moneyProperty = new SimpleIntegerProperty(0);
		this.isPause = false;
		this.sectionXProperty = new SimpleIntegerProperty(0);
		this.sectionYProperty = new SimpleIntegerProperty(0);
		this.bulletDamageProperty = new SimpleIntegerProperty(5);
	}

	public IntegerProperty getBulletDamageProperty() {
		return this.bulletDamageProperty;
	}

	public IntegerProperty getSectionXProperty() {
		return this.sectionXProperty;
	}

	public IntegerProperty getSectionYProperty() {
		return this.sectionYProperty;
	}

	public IntegerProperty getMoneyProperty() {
		return this.moneyProperty;
	}

	public void addItemtoInventory(Item item) {
		int old = this.inventory.get(item.getItemType());
		this.inventory.put(item.getItemType(), old + 1);
		this.inventoryProperties.get(item.getItemType()).set(old + 1);
	}

	public void removeItemFromInventory(ItemType type) {
		int old = this.inventory.get(type);
		if (old > 0) {
			this.inventory.put(type, old - 1);
			this.inventoryProperties.get(type).set(old - 1);
			;
		}
	}

	public Map<ItemType, IntegerProperty> getInventoryProperties() {
		return this.inventoryProperties;
	}

	public Map<ItemType, Integer> getInventory() {
		return this.inventory;
	}

	public int getBulletDamage() {
		return this.bulletDamageProperty.get();
	}

	public PlayerShip getPlayerShip() {
		return playerShip;
	}

	public void setPlayerShip(PlayerShip playerShip) {
		this.playerShip = playerShip;
	}

	public void setBulletDamage(int bulletDamage) {
		this.bulletDamageProperty.set(bulletDamage);
	}

	public double getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public int getSectionX() {
		return this.sectionXProperty.get();
	}

	public int getSectionY() {
		return this.sectionYProperty.get();
	}

	public void setSectionX(int sectionX) {
		this.sectionXProperty.set(sectionX);
	}

	public void setSectionY(int sectionY) {
		this.sectionYProperty.set(sectionY);
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public synchronized void addMoney(int amount) {
		int old = this.moneyProperty.get();
		this.moneyProperty.set(old + amount);
	}

	public synchronized void removeMoney(int amount) {
		int old = this.moneyProperty.get();
		this.moneyProperty.set(old - amount);
	}

	public int getMoney() {
		return this.moneyProperty.get();
	}

	public synchronized void setMoney(int money) {
		this.moneyProperty.set(money);
	}

	public boolean isPause() {
		return isPause;
	}

	public void pause() {
		this.isPause = true;
	}

	public void resume() {
		this.isPause = false;
	}
}