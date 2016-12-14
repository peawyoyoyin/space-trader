package news;

import constants.ConfigConstant;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

class NewsPaneTop extends StackPane {
	public NewsPaneTop() {
		super();
		this.setPrefHeight(40);
		Image logo = new Image(ClassLoader.getSystemResource("logo.png").toString());
		Canvas logoCanvas = new Canvas(60, 60);
		logoCanvas.getGraphicsContext2D().drawImage(logo, 0, 0, 60, 60);
		this.getChildren().add(logoCanvas);
	}
}

public class NewsPane extends BorderPane {
	public static NewsPane instance = new NewsPane();
	
	private NewsFeedPane newsFeedPane;
	NewsFeed newsFeed;

	public NewsPane() {
		super();
		this.setTop(new NewsPaneTop());
		newsFeedPane = new NewsFeedPane();
		this.newsFeed = newsFeedPane.getNewsFeed();
		this.setPadding(new Insets(10,0,0,0));
		this.setCenter(newsFeedPane);
		this.setMaxWidth(ConfigConstant.gameScreen_left_width);
	}

	public NewsFeedPane getNewsFeedPane() {
		return newsFeedPane;
	}

	public NewsFeed getNewsFeed() {
		return this.newsFeed;
	}

	public void addNews(News news) {
		this.newsFeed.addNews(news);
	}

	public void clearFeed() {
		this.newsFeed.getChildren().clear();
	}
}
