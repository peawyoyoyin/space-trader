package game.gui;

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
		Button mainMenuButton = new Button("Main Menu");
		container.getChildren().add(mainMenuButton);
		this.getChildren().add(container);
		
		mainMenuButton.setOnAction(event -> {
			Main.toStartScreen();
		});
	}
	
}
