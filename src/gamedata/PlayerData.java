package gamedata;

public class PlayerData {
	String playerName;
	int money;
	PlayerStocksPortFolio stocksPortfolio;
	int sectionX;
	int sectionY;
	double bulletSpeed;
	int bulletDamage;
	
	private static final String PLACEHOLDER_NAME = "name is blank";
	
	public PlayerData() {
		this.playerName = PLACEHOLDER_NAME;
		this.money = 0;
		this.stocksPortfolio = new PlayerStocksPortFolio();
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
}
