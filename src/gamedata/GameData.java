package gamedata;

@Deprecated
public class GameData {
	public static GameData instance = new GameData();
	
	private PlayerData playerData;
	
	public GameData() {
		this.playerData = new PlayerData();
	}
	
	public PlayerData getPlayerData() {
		return this.playerData;
	}
	
	@Deprecated
	public static GameData getInstance() {
		return instance;
	}
}
