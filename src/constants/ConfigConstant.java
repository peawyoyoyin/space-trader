package constants;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ConfigConstant {
	public static final String GAME_NAME = "GAME NAME";

	public static int startScreenHeight = 630;
	public static int startScreenWidth = 1120;
	public static final Font START_SCREEN_FONT = Font.font("Verdana", FontPosture.REGULAR, 72);
	public static final Font START_SCREEN_ITEM_FONT = Font.font("Verdana", FontPosture.REGULAR, 56);
	
	public static final Font GAME_FONT = Font.font("Verdana", FontPosture.REGULAR, 24);

	public static final String UPTODATE_TEXT = "Game Client is Up to Date";
	public static final String SERVER_NOT_FOUND_TEXT = "Server Not Found";

	public static int gameScreenHeight = 630;
	public static int gameScreenWidth = 620;

	public static int mapCellWidth = 6000;
	public static int mapCellHeight = 6000;

	public static int gameScreen_right_width = 250;
	public static int gameScreen_left_width = 250;

	public static String serverURL = "http://127.0.0.1:7777/";
	public static String version = "# progmeth-project";

	public static class Resource {
		public static AudioClip LASER_SOUND = new AudioClip(ClassLoader.getSystemResource("sfx_laser1.wav").toString());
		
		public static Image BULLET_IMAGE = new Image(ClassLoader.getSystemResource("Lasers/laserGreen12.png").toString());
		public static Image SHIP_IMAGE = new Image(ClassLoader.getSystemResource("playerShip1_red.png").toString());
		public static Image ENEMY_IMAGE = new Image(ClassLoader.getSystemResource("Enemies/enemyRed1.png").toString());
		public static Image MAP_BACKGROUND = new Image(ClassLoader.getSystemResource("purple.png").toString());
		public static Image SPACE_STATION = new Image(ClassLoader.getSystemResource("starbase-tex.png").toString());
		public static Image BOOM_IMAGE = new Image(ClassLoader.getSystemResource("explosion_sp.png").toString());


		public static Image TRADER_FACE = new Image(ClassLoader.getSystemResource("trader/traderface.jpg").toString());
		public static Image ITEM_GEM = new Image(ClassLoader.getSystemResource("itemicon/itemgem.png").toString());
		public static Image ITEM_PARTS = new Image(ClassLoader.getSystemResource("itemicon/itemparts.png").toString());
		public static Image ITEM_JUNK = new Image(ClassLoader.getSystemResource("itemicon/itemjunk.png").toString());
		public static Image ITEM_WEAPON = new Image(
				ClassLoader.getSystemResource("itemicon/itemweapon.png").toString());
		public static Image ITEM_MATERIAL = new Image(
				ClassLoader.getSystemResource("itemicon/itemmaterial.png").toString());
	}
}
