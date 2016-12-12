package game.gui;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GameScreen extends BorderPane{
	
	public static final GameScreen instance = new GameScreen();
	
	private Node left;
	private Node center;
	private Node right;
	
	public GameScreen() {
		super();
		this.setLeft(new StackPane());
		this.setRight(new StackPane());
		this.setCenter(new StackPane());
	}
	
	public GameScreen(Node left, Node center, Node right) {
		super();
		this.left = left;
		this.center = center;
		this.right = right;
		this.setLeft(this.left);
		this.setCenter(this.center);
		this.setRight(this.right);
	}
	
	public void changeLeft(Node left) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				setLeft(left);
				FadeTransition fts = new FadeTransition(Duration.seconds(0.3), left);
				fts.setFromValue(0);
				fts.setToValue(1);
				fts.play();
			}
		});

	}
	
	public void changeRight(Node right) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				setRight(right);
				FadeTransition fts = new FadeTransition(Duration.seconds(0.3), right);
				fts.setFromValue(0);
				fts.setToValue(1);
				fts.play();
			}
		});
	}
	
	public void changeCenter(Node center) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				setCenter(center);
				FadeTransition fts = new FadeTransition(Duration.seconds(0.3), center);
				fts.setFromValue(0);
				fts.setToValue(1);
				fts.play();
			}
		});
	}
}
