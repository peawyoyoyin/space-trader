package gamedata;

public class GameData {
	private static GameData instance;
	
	private PlayerData playerData;
	
	public GameData() {
		GameData.instance = new GameData();
		this.playerData = new PlayerData();
	}
	
	public PlayerData getPlayerData() {
		return this.playerData;
	}
	
	public static GameData getInstance() {
		return instance;
	}
}
