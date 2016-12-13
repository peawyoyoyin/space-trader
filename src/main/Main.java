package main;

import java.util.Random;

import constants.ConfigConstant;
import game.gui.GamePane;
import game.gui.GameScreen;
import game.gui.MinimapPane;
import game.gui.PlayerInfoPane;
import game.gui.PlayerInventoryPane;
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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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

	public static void newGame() {
		Input.Initialize(scene);
		Market.InitializeMarket();
		MapCellHolder.instance = new MapCellHolder();

		Trader.InitiailizeTraders();
		GameScreen.instance = new GameScreen();
		Player.instance = new Player();
		PlayerShip playerShip = new PlayerShip(3000, 3000, 5, 100, 0, 10, 1, 3, 0);
		Player.instance.setPlayerShip(playerShip);
		Player.instance.addMoney(500);
		Player.instance.setSectionX(1);
		Player.instance.setSectionY(1);
		MinimapPane.initializeMinimap();
		PlayerInfoPane.instance = new PlayerInfoPane();
		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		mc.getEntities().add(playerShip);

		GamePane gamePane = new GamePane(ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
		GameScreen.instance.changeCenter(gamePane);
		GraphicsContext gc = gamePane.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, 200, 200);

		gameRunner = new AnimationTimer() {
			int counter = 0;

			public void handle(long now) {
				MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
				gc.clearRect(0, 0, ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
				mc.update(gc);
				Input.inputUpdate();
				counter++;
				if (counter % 1000 == 0) {
					Random rd = new Random();
					boolean isNormalMC = false;
					int x = 0, y = 0;
					MapCell mcUpgrade = null;
					while (!isNormalMC) {
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
					NewsPane.instance.addNews(new News("Universe", "Monster in sector " + Integer.toString(x) + "-"
							+ Integer.toString(y) + " are now stronger!"));
					mcUpgrade.upgradeEnemyDmg(rd.nextInt(5));
					mcUpgrade.upgradeEnemyHp(rd.nextInt(5) * 5);
				}
			}
		};

		gameRunner.start();
		scene.setRoot(GameScreen.instance);

	}

	public static void toStartScreen() {
		gameRunner.stop();
		StartScreen.getInstace().changePane(StartScreen.getInstace().getStartPane());
		BackgroundStartScreen.reBackground();
		scene.setRoot(StartScreen.getInstace());
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			StartScreen startScreen = StartScreen.getInstace();
			scene = new Scene(startScreen, ConfigConstant.startScreenWidth, ConfigConstant.startScreenHeight);
			// primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setTitle(ConfigConstant.GAME_NAME); // Set the stage
																// title
			primaryStage.setScene(scene); // Place the scene
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
