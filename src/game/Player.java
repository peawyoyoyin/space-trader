package game;

import java.util.List;

public class Player {
	public static Player instance;
	private String playerName;
	private int money;
	private int sectionX;
	private int sectionY;
	private double bulletSpeed;
	private int bulletDamage;
	private List<Item> inventory;
	
	private static final String PLACEHOLDER_NAME = "name is blank";
	
	public Player() {
		instance = this;
		this.playerName = PLACEHOLDER_NAME;
		this.money = 0;
		this.sectionX = 0;
		this.sectionY = 0;
		this.bulletSpeed = 20;
		this.bulletDamage = 5;
	}
	

	public int getBulletDamage() {
		return bulletDamage;
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
	}
	
	public void removeMoney(int amount) {
		this.money -= amount;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public List<Item> getInventory() {
		return this.inventory;
	}
}
