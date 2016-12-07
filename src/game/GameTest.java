package game;

import input.Input;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import constants.ConfigConstant;
import gamedata.GameData;

public class GameTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();

		GamePane gamePane = new GamePane(constants.ConfigConstant.gameScreenWidth,
				constants.ConfigConstant.gameScreenHeight);
		PlayerShip ship = new PlayerShip(500, 500, 100, 100, 0, 5, 0.05, 1, 1);
		PlayerShip ship2 = new PlayerShip(0, 0, 5, 5, 0, 0, 0, 0, 0);
		MapCell mc = MapCellHolder.instance.get(GameData.getInstance().getPlayerData().getSectionX(), GameData.getInstance().getPlayerData().getSectionY());
		mc.getEntities().add(ship);
		for (int i = 0; i < 10; i++) {
			mc.getEntities().add(new Bullet(i*10, i*10, 20, 45, 5,ship2));
		}
		GraphicsContext gc = gamePane.getGraphicsContext2D();
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gc.clearRect(0, 0, ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
				mc.update(gc);
				Input.inputUpdate();
				System.out.println(ship.getHp());
			}
		};
		animation.start();
		root.getChildren().add(gamePane);
		Scene scene = new Scene(root);
		Input.Initialize(scene);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
