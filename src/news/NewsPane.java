package news;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Duration;

class NewsPaneTop extends StackPane {
	public NewsPaneTop() {
		super();
		Label name = new Label("News Feed");
		this.getChildren().add(name);
		this.setPrefHeight(40);
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
