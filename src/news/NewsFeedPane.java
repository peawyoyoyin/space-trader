package news;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class NewsFeedPane extends ScrollPane {
	private NewsFeed newsFeed;

	public NewsFeedPane() {
		super();
		this.newsFeed = new NewsFeed();
		this.setMinWidth(250);
		this.setMaxHeight(500);
		this.setPadding(new Insets(5));
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setContent(newsFeed);
		this.setStyle("-fx-background-color: transparent;");
	}

	public NewsFeed getNewsFeed() {
		return this.newsFeed;
	}
}

class NewsFeed extends VBox {
	private static final int MAX_NEWS = 10;

	public NewsFeed() {
		super();
		this.setSpacing(5);
		this.setMaxWidth(250);
	}

	public void addNews(News news) {
		this.getChildren().add(0, new NewsCell(news));
		if (this.getChildren().size() > MAX_NEWS) {
			this.getChildren().remove(this.getChildren().size() - 1);
		}
	}
}