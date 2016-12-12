package main;

import constants.ConfigConstant;
import game.gui.GamePane;
import game.gui.GameScreen;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import game.model.PlayerShip;
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

		Player.instance = new Player();
		PlayerShip playerShip = new PlayerShip(3000, 3000, 5, 100, 0, 10, 1, 3, 0);
		Player.instance.setPlayerShip(playerShip);

		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		mc.getEntities().add(playerShip);

		GamePane gamePane = new GamePane(ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
		GameScreen.instance.changeCenter(gamePane);
		GraphicsContext gc = gamePane.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, 200, 200);
		gameRunner = new AnimationTimer() {
			public void handle(long now) {
				MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
				gc.clearRect(0, 0, ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
				mc.update(gc);
				// playerShip.hit(100);
				if (Input.isKeyPressed(KeyCode.N)) {
					System.out.println("new game");
					this.stop();
					newGame();
				}
				Input.inputUpdate();
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
			primaryStage.setTitle("Project-Progmeth"); // Set the stage title
			primaryStage.setScene(scene); // Place the scene
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public void toggleGamescreen() {
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
