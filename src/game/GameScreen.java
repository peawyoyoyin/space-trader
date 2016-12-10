package game;

import constants.ConfigConstant;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import market.TraderScreen;
import news.NewsPane;
import stocks.StocksScreen;

public class GameScreen extends BorderPane{
	
	public static final GameScreen instance = new GameScreen();
	
	private Node left;
	private Node center;
	private Node right;
	
	public GameScreen() {
		this(NewsPane.instance,new Canvas(ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight),StocksScreen.instance);
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
		final Node currentLeft = this.getLeft();
		TranslateTransition tts = new TranslateTransition(Duration.seconds(0.8),currentLeft);
		tts.setFromY(0);
		tts.setToY(currentLeft.getBoundsInParent().getHeight());
		tts.setOnFinished(event -> {
			this.setLeft(left);
		});
		tts.play();
	}
	
	public void changeRight(Node right) {
		this.setRight(right);
		FadeTransition fts = new FadeTransition(Duration.seconds(0.8), right);
		fts.setFromValue(0);
		fts.setToValue(1);
		fts.play();
	}
	
	public void changeCenter(Node center) {
		final Node currentCenter = this.getCenter();
		TranslateTransition tts = new TranslateTransition(Duration.seconds(0.8),currentCenter);
		tts.setFromY(0);
		tts.setToY(currentCenter.getBoundsInParent().getHeight());
		tts.setOnFinished(event -> {
			this.setCenter(center);
		});
		tts.play();
	}
}
