package game.gui;

import game.logic.Player;
import highscore.HighScore;
import constants.ConfigConstant;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Main;

public class GameOverPane extends StackPane {

	public GameOverPane() {
		super();
		// TODO Auto-generated constructor stub
		double width = 400;
		double height = 250;

		VBox container = new VBox(25);
		container.setAlignment(Pos.CENTER);
		Label gameOver = new Label("Game Over");
		gameOver.setFont(ConfigConstant.Resource.HUD_HEADER_FONT);
		gameOver.setTextFill(Color.WHITE);
		container.getChildren().add(gameOver);
		if (HighScore.isInHighScore(Player.instance.getMoney())) {
			Label highScore = new Label("Your score is High Score!");
			highScore.setTextFill(Color.WHITE);
			highScore.setFont(ConfigConstant.Resource.HUD_FONT);
			container.getChildren().add(highScore);
			height += 50;
		}
		Label score = new Label("Your score is : " + Integer.toString(Player.instance.getMoney()));
		score.setTextFill(Color.WHITE);
		score.setFont(ConfigConstant.Resource.HUD_FONT);
		container.getChildren().add(score);
		Canvas bg = new Canvas(width, height);
		this.getChildren().add(bg);
		StackPane buttonContainer = new StackPane();
		Canvas bgButton = new Canvas(150, 50);
		bgButton.getGraphicsContext2D().drawImage(ConfigConstant.Resource.BUTTON_BACKGROUND, 0, 0, 150, 50);
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
		System.out.println(this.getWidth());

		bg.getGraphicsContext2D().drawImage(ConfigConstant.Resource.PANEL_BACKGROUND, 0, 0, bg.getWidth(),
				bg.getHeight());

	}

}
