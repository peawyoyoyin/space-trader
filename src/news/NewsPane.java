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
		NewsFeedWrapper newsFeedPane = new NewsFeedWrapper();
		this.newsFeed = newsFeedPane.getNewsFeed();
		this.setCenter(newsFeedPane);
	}
	
	public NewsFeed getNewsFeed() {
		return this.newsFeed;
	}
}

class NewsFeedWrapper extends ScrollPane{

	private NewsFeed newsFeed;
	
	public NewsFeedWrapper() {
		super();
		this.newsFeed = new NewsFeed();
		this.setMinWidth(250);
		this.setMaxHeight(500);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setContent(newsFeed);
	}
	
	public NewsFeed getNewsFeed() {
		return this.newsFeed;
	}

}

class NewsFeed extends VBox {
	private static final int MAX_NEWS = 5;
	
	public NewsFeed() {
		super();
		this.setSpacing(5);
		this.setMaxWidth(250);
	}
	
	public void addNews(News news) {
		this.getChildren().add(0,new NewsCell(news));
		if(this.getChildren().size() > MAX_NEWS) {
			this.getChildren().remove(this.getChildren().size()-1);
		}
	}
}

class NewsCell extends VBox {
	public NewsCell(News news) {
		super();
		this.setPadding(new Insets(5));
		this.setSpacing(5);
		this.setMinWidth(250);
		this.setMinHeight(50);
		this.setStyle("-fx-background-color: lightgray;");
		Label author = new Label(news.getAuthor());
		author.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));
		Label content = new Label(news.getContent());
		content.setWrapText(true);
		this.getChildren().addAll(author, content);
		
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.8), this);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.play();
	}
}