package game.gui;

import input.Input;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import market.Market;
import news.NewsPane;
import stocks.StocksScreen;
import constants.ConfigConstant;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import game.model.BombEffect;
import game.model.EnemyShip;
import game.model.PlayerShip;
import game.model.SpaceStationEntity;

public class GameTest extends Application {
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		Market.finalizeMarket();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();

		GamePane gamePane = new GamePane(constants.ConfigConstant.gameScreenWidth,
				constants.ConfigConstant.gameScreenHeight);
		PlayerShip ship = new PlayerShip(ConfigConstant.mapCellWidth / 2, ConfigConstant.mapCellHeight / 2, 100, 100, 0,
				10, 3, 5, 0);
		EnemyShip bShip = new EnemyShip(100, 100, 500, 500, 0, 5, 2, 1, 0);

		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		Player.instance.setPlayerShip(ship);
		mc.getEntities().add(ship);
		mc.getEntities().add(bShip);
		BombEffect be = new BombEffect(500, 500, 100, 100);
		mc.getEntities().add(be);
		SpaceStationEntity st = new SpaceStationEntity(ConfigConstant.mapCellWidth / 2,
				ConfigConstant.mapCellHeight / 2);
		mc.getEntities().add(st);

		GraphicsContext gc = gamePane.getGraphicsContext2D();
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
				gc.clearRect(0, 0, ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
				mc.update(gc);
				Input.inputUpdate();
			}
		};
		animation.start();
		root.getChildren().add(gamePane);
		Scene scene = new Scene(root, ConfigConstant.startScreenWidth, ConfigConstant.startScreenHeight);
		Input.Initialize(scene);

		scene.setRoot(PlayerInfoPane.instance);
		scene.setRoot(GameScreen.instance);
		GameScreen.instance.setLeft(NewsPane.instance);
		GameScreen.instance.setCenter(gamePane);
		GameScreen.instance.setRight(StocksScreen.instance);

		Player.instance.getPlayerShip().setMaxHp(200);
		Player.instance.setMoney(500);

		Market.InitializeMarket();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
					GameScreen.instance.changeLeft(PlayerInfoPane.instance);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		gamePane.requestFocus();
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}
}