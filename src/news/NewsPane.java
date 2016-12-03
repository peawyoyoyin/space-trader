package news;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class NewsPane extends ScrollPane{

	
	private NewsFeed newsFeed;
	
	public NewsPane() {
		super();
		this.newsFeed = new NewsFeed();
		this.setMaxHeight(500);
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
		this.setMaxWidth(230);
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
		this.setMinHeight(50);
		this.setStyle("-fx-background-color: lightgray;");
		Label author = new Label(news.getAuthor());
		author.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));
		Label content = new Label(news.getContent());
		content.setWrapText(true);
		this.getChildren().addAll(author, content);
	}
}