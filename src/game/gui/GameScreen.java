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
import javafx.scene.paint.Color;
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
	
	private Canvas leftBack;

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
		
		StackPane leftContainer = new StackPane();
		this.leftBack = new Canvas(ConfigConstant.gameScreen_left_width, ConfigConstant.gameScreenHeight);
		leftBack.getGraphicsContext2D().drawImage(ConfigConstant.Resource.NEWS_BG, 0, 0);
		leftContainer.getChildren().addAll(leftBack,leftPane);

		StackPane changeToNewsFeed = new StackPane();
		Label newsFeedLabel = new Label("News");
		newsFeedLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		newsFeedLabel.setTextFill(Color.BLACK);
		newsFeedLabel.setOpacity(0.5);
		changeToNewsFeed.getChildren().add(newsFeedLabel);
		StackPane changeToShipStatus = new StackPane();
		Label statusLabel = new Label("Status");
		statusLabel.setTextFill(Color.WHITE);
		statusLabel.setOpacity(0.5);
		statusLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		changeToShipStatus.getChildren().add(statusLabel);
		
		changeToNewsFeed.setOnMouseClicked(event -> {
			changeLeft(NewsPane.instance);
			statusLabel.setTextFill(Color.WHITE);
			newsFeedLabel.setTextFill(Color.BLACK);
			leftBack.getGraphicsContext2D().drawImage(ConfigConstant.Resource.NEWS_BG, 0, 0);
		});

		changeToShipStatus.setOnMouseClicked(event -> {
			changeLeft(PlayerInfoPane.instance);
			statusLabel.setTextFill(Color.BLACK);
			newsFeedLabel.setTextFill(Color.WHITE);
			leftBack.getGraphicsContext2D().drawImage(ConfigConstant.Resource.STATUS_BG, 0, 0);
		});

		HBox leftTabControl = new HBox();
		leftTabControl.setAlignment(Pos.CENTER);
		leftTabControl.setSpacing(60);
		leftTabControl.setMinHeight(30);
		leftTabControl.getChildren().addAll(changeToNewsFeed, changeToShipStatus);

		this.leftPane.setTop(leftTabControl);

		this.left = left;
		this.center = center;
		this.right = right;
		this.setLeft(leftContainer);
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
