package constants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.javafx.tk.Toolkit;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class ConfigConstant {
	public static final String GAME_NAME = "Space Trader";

	public static String PLAYER_NAME = "< blank name >";
	public static double volumeMusic = 1.00;
	public static double volumeSFX = 1.00;

	public static final String HIGHSCORE_FILE = "highscores/highscore";

	public static int startScreenHeight = 630;
	public static int startScreenWidth = 1120;

	public static final String UPTODATE_TEXT = "Game Client is Up to Date";
	public static final String SERVER_NOT_FOUND_TEXT = "Server Not Found";

	public static int gameScreenHeight = 630;
	public static int gameScreenWidth = 620;

	public static int mapCellWidth = 6000;
	public static int mapCellHeight = 6000;

	public static int gameScreen_right_width = 250;
	public static int gameScreen_left_width = 250;

	public static String serverURL = "http://127.0.0.1:7777/";
	public static String version = "old version";

	public static class Resource {
		public static AudioClip LASER_SOUND = new AudioClip(ClassLoader.getSystemResource("sfx_laser1.wav").toString());
		public static AudioClip BOOM_SOUND = new AudioClip(ClassLoader.getSystemResource("DeathFlash.wav").toString());
		public static AudioClip GAME_MUSIC = new AudioClip(
				ClassLoader.getSystemResource("MyVeryOwnDeadShip.wav").toString());
		public static AudioClip MENU_MUSIC = new AudioClip(ClassLoader.getSystemResource("space.wav").toString());

		public static Image HPBAR_BACK = new Image(ClassLoader.getSystemResource("hpbar-bg.png").toString());
		public static Image HPBAR_FRONT = new Image(ClassLoader.getSystemResource("hpbar-front.png").toString());
		public static Image BULLET_IMAGE = new Image(ClassLoader.getSystemResource("laserGreen12.png").toString());
		public static Image BULLET_ENEMY_IMAGE = new Image(ClassLoader.getSystemResource("laserRed06.png").toString());
		public static Image HIT_IMAGE = new Image(ClassLoader.getSystemResource("laserGreenShot.png").toString());
		public static Image HIT_ENEMY_IMAGE = new Image(ClassLoader.getSystemResource("laserRedShot.png").toString());
		public static Image SHIP_IMAGE = new Image(ClassLoader.getSystemResource("playerShip1_red.png").toString());
		public static Image ENEMY_IMAGE = new Image(ClassLoader.getSystemResource("enemyRed1.png").toString());
		public static Image MAP_BACKGROUND = new Image(ClassLoader.getSystemResource("purple.png").toString());
		public static Image SPACE_STATION = new Image(ClassLoader.getSystemResource("starbase-tex.png").toString());
		public static Image BOOM_IMAGE = new Image(ClassLoader.getSystemResource("explosion_sp.png").toString());
		public static Image STOCKS_BG = new Image(ClassLoader.getSystemResource("stocks-bg.png").toString());
		public static Image NEWS_BG = new Image(ClassLoader.getSystemResource("news-bg.png").toString());
		public static Image STATUS_BG = new Image(ClassLoader.getSystemResource("status-bg.png").toString());
		public static Image PANEL_BACKGROUND_L = new Image(
				ClassLoader.getSystemResource("glassPanel_L.png").toString());
		public static Image PANEL_BACKGROUND_M = new Image(
				ClassLoader.getSystemResource("glassPanel_M.png").toString());
		public static Image BUTTON_BACKGROUND = new Image(
				ClassLoader.getSystemResource("glassPanel_Button.png").toString());

		public static Image ITEM_GEM = new Image(ClassLoader.getSystemResource("itemgem.png").toString());
		public static Image ITEM_PARTS = new Image(ClassLoader.getSystemResource("itemparts.png").toString());
		public static Image ITEM_JUNK = new Image(ClassLoader.getSystemResource("itemjunk.png").toString());
		public static Image ITEM_WEAPON = new Image(ClassLoader.getSystemResource("itemweapon.png").toString());
		public static Image ITEM_MATERIAL = new Image(ClassLoader.getSystemResource("itemmaterial.png").toString());

		public static Font START_SCREEN_FONT = loadFont(
				ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 72);
		public static Font START_SCREEN_ITEM_FONT = loadFont(
				ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 56);
		public static Font START_SCREEN_SMALL_FONT = loadFont(
				ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 36);
		public static Font STOCK_FONT = loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 13);
		public static Font HUD_FONT = loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(), 15);
		public static Font HUD_MID_FONT = loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(),
				24);
		public static Font HUD_HEADER_FONT = loadFont(ClassLoader.getSystemResource("kenvector_future.ttf").toString(),
				36);

		static {
			GAME_MUSIC.setCycleCount(AudioClip.INDEFINITE);
			MENU_MUSIC.setCycleCount(AudioClip.INDEFINITE);
		}

	}

	/**
	 * this method uses Toolkit.getToolkit() to load font. this is created to
	 * solve font loading problems on some computer.
	 */
	public static Font loadFont(String url, double size) {
		try {
			return Toolkit.getToolkit().getFontLoader().loadFont(new URL(url).openConnection().getInputStream(), size);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
