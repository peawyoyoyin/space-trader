package game.gui;

import constants.ConfigConstant;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import news.NewsPane;
import stocks.StocksScreen;

public class GameScreen extends BorderPane {

	public static final GameScreen instance = new GameScreen();

	private Node left;
	private Node center;
	private Node right;

	private BorderPane leftPane;
	private StackPane centerPane;
	private BorderPane rightPane;

	public GameScreen() {
		this(NewsPane.instance,
				new StackPane(),
				StocksScreen.instance);
	}

	public GameScreen(Node left, Node center, Node right) {
		super();
		this.leftPane = new BorderPane();
		this.rightPane = new BorderPane();
		this.centerPane = new StackPane();

		StackPane changeToNewsFeed = new StackPane();
		Label newsFeedLabel = new Label("News");
		newsFeedLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		changeToNewsFeed.getChildren().add(newsFeedLabel);
		StackPane changeToShipStatus = new StackPane();
		Label statusLabel = new Label("Status");
		statusLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		changeToShipStatus.getChildren().add(statusLabel);

		changeToNewsFeed.setOnMouseClicked(event -> {
			changeLeft(NewsPane.instance);
		});

		changeToShipStatus.setOnMouseClicked(event -> {
			changeLeft(PlayerInfoPane.instance);
		});

		HBox leftTabControl = new HBox();
		leftTabControl.setAlignment(Pos.CENTER);
		leftTabControl.setSpacing(10);
		leftTabControl.getChildren().addAll(changeToNewsFeed, changeToShipStatus);

		this.leftPane.setTop(leftTabControl);

		this.left = left;
		this.center = center;
		this.right = right;
		this.setLeft(this.leftPane);
		this.setCenter(this.centerPane);
		this.setRight(this.rightPane);

		this.leftPane.setCenter(left);
		this.rightPane.setCenter(right);
		this.centerPane.getChildren().add(center);
	}

	public void changeLeft(Node left) {
		if (left != this.left) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					leftPane.setCenter(left);
					FadeTransition fts = new FadeTransition(Duration.seconds(0.3), left);
					fts.setFromValue(0);
					fts.setToValue(1);
					fts.play();
				}
			});
		}
		this.left = left;
	}

	public void changeRight(Node right) {

		if (right != this.right) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					rightPane.setCenter(right);
					FadeTransition fts = new FadeTransition(Duration.seconds(0.3), right);
					fts.setFromValue(0);
					fts.setToValue(1);
					fts.play();
				}
			});
		}
		this.right = right;
	}

	public void changeCenter(Node center) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				centerPane.getChildren().add(center);
				FadeTransition fts = new FadeTransition(Duration.seconds(0.3), center);
				fts.setFromValue(0);
				fts.setToValue(1);
				fts.play();
			}
		});
		this.center = center;
	}
}
