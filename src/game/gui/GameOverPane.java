package game.gui;

import game.logic.Player;
import highscore.HighScore;
import constants.ConfigConstant;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		VBox container = new VBox(40);
		container.setAlignment(Pos.CENTER);
		Label gameOver = new Label("Game Over");
		gameOver.setFont(ConfigConstant.Resource.HUD_FONT);
		gameOver.setTextFill(Color.WHITE);
		container.getChildren().add(gameOver);
		if(HighScore.isInHighScore(Player.instance.getMoney())) {
			Label highScore = new Label("Your score is High Score!");
			highScore.setTextFill(Color.WHITE);
			container.getChildren().add(highScore);
		}
		Label score = new Label("Your score is : "+Integer.toString(Player.instance.getMoney()));
		score.setTextFill(Color.WHITE);
		container.getChildren().add(score);
		Button mainMenuButton = new Button("Main Menu");
		container.getChildren().add(mainMenuButton);
		this.getChildren().add(container);
		mainMenuButton.setOnAction(event -> {
			if(HighScore.isInHighScore(Player.instance.getMoney())) {
				System.out.println("save highscore with score "+Player.instance.getMoney());
				HighScore.addHighScoreEntry(Player.instance.getPlayerName(), Player.instance.getMoney());
			}
			Main.toStartScreen();
		});
		System.out.println(this.getWidth());
//		Canvas bg = new Canvas(this.getWidth(), this.getHeight());
//		bg.getGraphicsContext2D().drawImage(ConfigConstant.Resource.PANEL_BACKGROUND, 0, 0, this.getWidth(), this.getHeight());
//		this.getChildren().add(bg);
		this.setBackground(new Background(new BackgroundImage(ConfigConstant.Resource.PANEL_BACKGROUND,
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
          new BackgroundSize(350, 350, false, false, false, false))));
	}

}
