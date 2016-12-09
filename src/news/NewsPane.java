package news;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

class NewsPaneTop extends StackPane {
	public NewsPaneTop() {
		super();
//		Label name = new Label("News Feed");
//		this.getChildren().add(name);
//		this.setPrefHeight(40);
		Image logo = new Image(ClassLoader.getSystemResource("logo.png").toString());
		Canvas logoCanvas = new Canvas(50, 50);
		logoCanvas.getGraphicsContext2D().drawImage(logo, 0, 0, 50, 50);
		this.getChildren().add(logoCanvas);
	}
}

public class NewsPane extends BorderPane {
	NewsFeed newsFeed;
	public NewsPane() {
		super();
		this.setTop(new NewsPaneTop());
		NewsFeedPane newsFeedPane = new NewsFeedPane();
		this.newsFeed = newsFeedPane.getNewsFeed();
		this.setCenter(newsFeedPane);
	}
	
	public NewsFeed getNewsFeed() {
		return this.newsFeed;
	}
}
