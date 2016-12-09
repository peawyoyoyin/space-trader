package constants;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ConfigConstant {
	public static final String GAME_NAME = "GAME NAME";
	
	public static int startScreenHeight = 630;
	public static int startScreenWidth = 1120;
	public static final Font START_SCREEN_FONT = Font.font("Verdana", FontPosture.REGULAR, 72);
	public static final Font START_SCREEN_ITEM_FONT = Font.font("Verdana", FontPosture.REGULAR, 56);
	
	public static final String UPTODATE_TEXT = "Game Client is Up to Date";
	public static final String SERVER_NOT_FOUND_TEXT = "Server Not Found";
	
	public static int gameScreenHeight = 630;
	public static int gameScreenWidth = 620;
	
	public static int mapCellWidth = 6000;
	public static int mapCellHeight = 6000;
	
	public static int stocksScreenWidth;
	public static int stocksScreenHeight;
	
	public static int newsFeedScreenWidth;
	public static int newsFeedScreenHeight;
	
	public static String serverURL = "http://127.0.0.1:7777/";
	public static String version = "# progmeth-project";	
}
