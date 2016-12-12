package game.gui;

import game.logic.Player;
import highscore.HighScore;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.Main;

public class GameOverPane extends StackPane{

	public GameOverPane() {
		super();
		// TODO Auto-generated constructor stub
		VBox container = new VBox(5);
		container.setAlignment(Pos.CENTER);
		Label gameOver = new Label("Game Over");
		container.getChildren().add(gameOver);
		if(HighScore.isInHighScore(Player.instance.getMoney())) {
			Label highScore = new Label("Your score is High Score!");
			container.getChildren().add(highScore);
		}
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
	}
	
}
