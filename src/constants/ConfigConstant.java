package constants;

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.sun.javafx.tk.Toolkit;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ConfigConstant {
	public static final String GAME_NAME = "GAME NAME";
	
	public static double volumeMusic = 1;
	public static double volumeSFX = 1;

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
		public static AudioClip BOOM_SOUND = new AudioClip(ClassLoader.getSystemResource("DeathFlash.wav").toString());

		public static Image BULLET_IMAGE = new Image(
				ClassLoader.getSystemResource("Lasers/laserGreen12.png").toString());
		public static Image SHIP_IMAGE = new Image(ClassLoader.getSystemResource("playerShip1_red.png").toString());
		public static Image ENEMY_IMAGE = new Image(ClassLoader.getSystemResource("Enemies/enemyRed1.png").toString());
		public static Image MAP_BACKGROUND = new Image(ClassLoader.getSystemResource("purple.png").toString());
		public static Image SPACE_STATION = new Image(ClassLoader.getSystemResource("starbase-tex.png").toString());
		public static Image BOOM_IMAGE = new Image(ClassLoader.getSystemResource("explosion_sp.png").toString());
		public static Image PANEL_BACKGROUND = new Image(ClassLoader.getSystemResource("glassPanel_corners.png").toString());
		public static Image TRADER_FACE = new Image(ClassLoader.getSystemResource("trader/traderface.jpg").toString());
		public static Image ITEM_GEM = new Image(ClassLoader.getSystemResource("itemicon/itemgem.png").toString());
		public static Image ITEM_PARTS = new Image(ClassLoader.getSystemResource("itemicon/itemparts.png").toString());
		public static Image ITEM_JUNK = new Image(ClassLoader.getSystemResource("itemicon/itemjunk.png").toString());
		public static Image ITEM_WEAPON = new Image(
				ClassLoader.getSystemResource("itemicon/itemweapon.png").toString());
		public static Image ITEM_MATERIAL = new Image(
				ClassLoader.getSystemResource("itemicon/itemmaterial.png").toString());

		public static Font HUD_FONT = loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 15);

	}

	public static Font loadFont(String url, double size) {
		try {
			return Toolkit.getToolkit().getFontLoader().loadFont(new URL(url).openConnection().getInputStream(), size);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
