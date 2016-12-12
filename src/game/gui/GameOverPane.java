package game.gui;

import constants.ConfigConstant;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Main;

public class GameOverPane extends StackPane {

	public GameOverPane() {
		super();
		// TODO Auto-generated constructor stub
		Canvas bg = new Canvas(300, 200);
		bg.getGraphicsContext2D().drawImage(ConfigConstant.Resource.PANEL_BACKGROUND, 0, 0, 300, 200);
		this.getChildren().add(bg);
		VBox container = new VBox(40);
		container.setAlignment(Pos.CENTER);
		Label gameOver = new Label("Game Over");
		gameOver.setFont(ConfigConstant.Resource.HUD_FONT);
		gameOver.setTextFill(Color.WHITE);
		container.getChildren().add(gameOver);
		Button mainMenuButton = new Button("Main Menu");
		container.getChildren().add(mainMenuButton);
		this.getChildren().add(container);
		mainMenuButton.setOnAction(event -> {
			Main.toStartScreen();
		});
	}

}
