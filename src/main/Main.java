package main;

import java.util.Random;

import constants.ConfigConstant;
import game.gui.GameScreen;
import game.gui.MinimapPane;
import game.gui.PlayerInfoPane;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import game.model.Entity;
import game.model.PlayerShip;
import game.model.SpaceStationEntity;
import input.Input;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import market.Market;
import market.Trader;
import news.News;
import news.NewsPane;
import startScreen.BackgroundStartScreen;
import startScreen.StartScreen;

public class Main extends Application {

	private static Scene scene;
	private static AnimationTimer gameRunner;

	/**
	 * newGame is called on every time the game starts
	 */
	public static void newGame() {
		ConfigConstant.Resource.MENU_MUSIC.stop();
		ConfigConstant.Resource.GAME_MUSIC.play(ConfigConstant.volumeMusic);
		
		Input.Initialize(scene);
		Market.InitializeMarket();
		//re-initialize mapCells
		MapCellHolder.instance = new MapCellHolder();

		Trader.InitiailizeTraders();
		
		GameScreen.instance = new GameScreen();
		
		//re-initialize Player
		Player.instance = new Player();
		PlayerShip playerShip = new PlayerShip(3000, 3000, 100, 100, 0, 8, 1, 3, 0);
		Player.instance.setPlayerShip(playerShip);
		Player.instance.addMoney(500);
		Player.instance.setSectionX(1);
		Player.instance.setSectionY(1);
		
		//initialize game HUD
		MinimapPane.initializeMinimap();
		PlayerInfoPane.instance = new PlayerInfoPane();
		
		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		mc.getEntities().add(playerShip);
		Canvas gamePane = new Canvas(ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
		GameScreen.instance.changeCenter(gamePane);
		GraphicsContext gc = gamePane.getGraphicsContext2D();

		//create a animationTimer (called gameRunner) that call update methods periodically.
		gameRunner = new AnimationTimer() {
			int counter = 0;

			public void handle(long now) {
				MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
				gc.clearRect(0, 0, ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
				mc.update(gc);
				//for testing
				if(Input.isKeyPressed(KeyCode.G)) {
					Player.instance.addMoney(500);
				}
				Input.inputUpdate();
				counter++;
				//every 1000 ticks one mapCell generate stronger enemies
				if (counter % 1000 == 0) {
					Random rd = new Random();
					boolean isNormalMC = false;
					int x = 0, y = 0;
					MapCell mcUpgrade = null;
					while (!isNormalMC) {
						//randomly choose a mapCell, until the mapCell contains no SpaceStationEntity
						x = rd.nextInt(5);
						y = rd.nextInt(5);
						mcUpgrade = MapCellHolder.instance.get(x, y);
						for (Entity entity : mcUpgrade.getEntities()) {
							if (entity instanceof SpaceStationEntity) {
								break;
							}
						}
						isNormalMC = true;
					}
					
					//add news
					NewsPane.instance.addNews(new News("Universe", "Monster in sector " + Integer.toString(x) + "-"
							+ Integer.toString(y) + " are now stronger!"));
					
					//upgrade the mapCell's enemy generation
					mcUpgrade.upgradeEnemyDmg(rd.nextInt(5));
					mcUpgrade.upgradeEnemyHp(rd.nextInt(5) * 5);
				}
			}
		};

		gameRunner.start();
		scene.setRoot(GameScreen.instance);

	}
	
	/**
	 * this method is called when the player dies and goes back the the start screen
	 * stop all running threads
	 */
	public static void toStartScreen() {
		gameRunner.stop();
		StartScreen.getInstance().changePane(StartScreen.getInstance().getStartPane());
		BackgroundStartScreen.reBackground();
		scene.setRoot(StartScreen.getInstance());
		ConfigConstant.Resource.GAME_MUSIC.stop();
		ConfigConstant.Resource.MENU_MUSIC.play(ConfigConstant.volumeMusic);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			StartScreen startScreen = StartScreen.getInstance();
			ConfigConstant.Resource.MENU_MUSIC.play(ConfigConstant.volumeMusic);
			scene = new Scene(startScreen, ConfigConstant.startScreenWidth, ConfigConstant.startScreenHeight);
			// primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setTitle(ConfigConstant.GAME_NAME);
			primaryStage.setScene(scene); 
			primaryStage.setResizable(false);
			primaryStage.show();
			scene.getStylesheets().add(this.getClass().getResource("/main/style.css").toExternalForm());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		Market.finalizeMarket();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
