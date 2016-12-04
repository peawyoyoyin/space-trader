package news;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Duration;

public class NewsCell extends VBox {
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