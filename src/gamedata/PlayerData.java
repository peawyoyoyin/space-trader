package gamedata;

public class PlayerData {
	String playerName;
	int money;
	PlayerStocksPortFolio stocksPortfolio;
	
	private static final String PLACEHOLDER_NAME = "name is blank";
	
	public PlayerData() {
		this.playerName = PLACEHOLDER_NAME;
		this.money = 0;
		this.stocksPortfolio = new PlayerStocksPortFolio();
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
