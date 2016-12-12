package game.gui;

import input.Input;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import constants.ConfigConstant;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import game.model.HitEffect;
import game.model.BombEffect;
import game.model.EnemyShip;
import game.model.PlayerShip;
import game.model.SpaceStationEntity;

public class GameTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();

		GamePane gamePane = new GamePane(constants.ConfigConstant.gameScreenWidth,
				constants.ConfigConstant.gameScreenHeight);
		PlayerShip ship = new PlayerShip(ConfigConstant.mapCellWidth/2, ConfigConstant.mapCellHeight/2, 100, 100, 0, 10, 3, 5, 0);
		EnemyShip bShip = new EnemyShip(ConfigConstant.mapCellWidth/2-100, ConfigConstant.mapCellHeight/2-100, 500, 500, 0, 5, 1, 1, 0);

		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		Player.instance.setPlayerShip(ship);
		mc.getEntities().add(ship);
		mc.getEntities().add(bShip);
		HitEffect he = new HitEffect(500, 500, 100, 100);
		mc.getEntities().add(he);
		SpaceStationEntity st = new SpaceStationEntity(ConfigConstant.mapCellWidth/2, ConfigConstant.mapCellHeight/2);
		mc.getEntities().add(st);
		BombEffect be = new BombEffect(3000, 3000);
		mc.getEntities().add(be);
		
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
		Scene scene = new Scene(root);
		Input.Initialize(scene);
		
		//scene.setRoot(PlayerInfoPane.instance);
		
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}