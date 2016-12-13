package game.gui;

import game.logic.Player;
import highscore.HighScore;
import constants.ConfigConstant;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;

public class GameOverPane extends StackPane {

	public GameOverPane() {
		super();
		// TODO Auto-generated constructor stub
		double width = 400;
		double height = 250;

		VBox container = new VBox(25);
		container.setAlignment(Pos.CENTER);
		Text gameOver = new Text("Game Over");
		gameOver.setFont(ConfigConstant.Resource.HUD_HEADER_FONT);
		gameOver.setFill(Color.WHITE);
		// gameOver.setStroke(Color.BLACK);
		gameOver.setEffect(new DropShadow());
		container.getChildren().add(gameOver);
		if (HighScore.isInHighScore(Player.instance.getMoney())) {
			Text highScore = new Text("Your score is High Score!");
			highScore.setFill(Color.WHITE);
			highScore.setFont(ConfigConstant.Resource.HUD_FONT);
			container.getChildren().add(highScore);
			height += 50;
		}
		Text score = new Text("Your score is : " + Integer.toString(Player.instance.getMoney()));
		score.setFill(Color.WHITE);
		score.setFont(ConfigConstant.Resource.HUD_FONT);
		container.getChildren().add(score);
		Canvas bg = new Canvas(width, height);
		this.getChildren().add(bg);
		StackPane buttonContainer = new StackPane();
		Canvas bgButton = new Canvas(150, 50);
		bgButton.getGraphicsContext2D().drawImage(ConfigConstant.Resource.BUTTON_BACKGROUND, 0, 0);
		buttonContainer.getChildren().add(bgButton);
		Button mainMenuButton = new Button("Main Menu");
		mainMenuButton.setPrefSize(150, 50);
		mainMenuButton.setTextFill(Color.WHITE);
		mainMenuButton.setFont(ConfigConstant.Resource.HUD_FONT);
		mainMenuButton.setStyle(
				"-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
		mainMenuButton.setOnMouseEntered(e -> {
			bgButton.setEffect(new Glow(0.5));
		});
		mainMenuButton.setOnMouseExited(e -> {
			bgButton.setEffect(null);
		});
		buttonContainer.getChildren().add(mainMenuButton);
		container.getChildren().add(buttonContainer);
		this.getChildren().add(container);
		mainMenuButton.setOnAction(event -> {
			if (HighScore.isInHighScore(Player.instance.getMoney())) {
				System.out.println("save highscore with score " + Player.instance.getMoney());
				HighScore.addHighScoreEntry(Player.instance.getPlayerName(), Player.instance.getMoney());
			}
			Main.toStartScreen();
		});
		buttonContainer.setEffect(new DropShadow());

		if (height == 250) {
			bg.getGraphicsContext2D().drawImage(ConfigConstant.Resource.PANEL_BACKGROUND_M, 0, 0);
		} else {
			bg.getGraphicsContext2D().drawImage(ConfigConstant.Resource.PANEL_BACKGROUND_L, 0, 0);
		}
		this.setEffect(new DropShadow());
	}

}
