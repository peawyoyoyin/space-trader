package game.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.logic.Item.ItemType;
import game.model.PlayerShip;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;

public class Player {
	public static Player instance = new Player();
	private String playerName;
	private int money;
	private int sectionX;
	private int sectionY;
	private boolean isPause;
	private double bulletSpeed;
	private int bulletDamage;
	private PlayerShip playerShip;
	
	private Map<ItemType, Integer> inventory;
	
	private IntegerProperty moneyProperty;
	
	private static final String PLACEHOLDER_NAME = "name is blank";
	
	public Player() {
		this.playerName = PLACEHOLDER_NAME;
		this.money = 0;
		this.sectionX = 0;
		this.sectionY = 0;
		this.bulletSpeed = 20;
		this.bulletDamage = 5;
		this.inventory = new HashMap<>();
		for (ItemType type : ItemType.values()) {
			inventory.put(type, 0);
		}
		this.moneyProperty = new SimpleIntegerProperty();
		this.isPause = false;
	}
	
	public IntegerProperty getMoneyProperty() {
		return this.moneyProperty;
	}
	
	public void addItemtoInventory(Item item) {
		int old = this.inventory.get(item.getItemType());
		this.inventory.put(item.getItemType(), old+1);
	}
	
	public void removeItemFromInventory(ItemType type) {
		int old = this.inventory.get(type);
		if(old > 0) {
			this.inventory.put(type, old-1);
		}
	}
	
	public Map<ItemType, Integer> getInventory() {
		return this.inventory;
	}
	

	public int getBulletDamage() {
		return bulletDamage;
	}

	public PlayerShip getPlayerShip() {
		return playerShip;
	}

	public void setPlayerShip(PlayerShip playerShip) {
		this.playerShip = playerShip;
	}

	public void setBulletDamage(int bulletDamage) {
		this.bulletDamage = bulletDamage;
	}


	public double getBulletSpeed() {
		return bulletSpeed;
	}


	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}


	public int getSectionX() {
		return sectionX;
	}

	public int getSectionY() {
		return sectionY;
	}

	public void setSectionX(int sectionX) {
		this.sectionX = sectionX;
	}

	public void setSectionY(int sectionY) {
		this.sectionY = sectionY;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void addMoney(int amount) {
		this.money += amount;
		this.moneyProperty.set(this.money);
	}
	
	public void removeMoney(int amount) {
		this.money -= amount;
		this.moneyProperty.set(this.money);
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
		this.moneyProperty.set(money);
	}

	public boolean isPause() {
		return isPause;
	}

	public void pause() {
		this.isPause = true;
	}
	public void resume(){
		this.isPause = false;
	}
}
