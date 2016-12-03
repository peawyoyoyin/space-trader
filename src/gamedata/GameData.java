package gamedata;

public class GameData {
	private static GameData instance = new GameData();
	
	private PlayerData playerData;
	
	public GameData() {
		this.playerData = new PlayerData();
	}
	
	public PlayerData getPlayerData() {
		return this.playerData;
	}
	
	public static GameData getInstance() {
		return instance;
	}
}
