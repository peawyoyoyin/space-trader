package startScreen;

import constants.ConfigConstant;
import highscore.HighScore;
import highscore.HighScoreParsingException;
import highscore.HighScoreRecord;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HighScorePane extends GridPane {
	private GridPane container;

	public HighScorePane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text("High Score");
		textName.setFont(ConfigConstant.START_SCREEN_FONT);
		textName.setFill(Color.WHITE);
		textName.setStroke(Color.BLACK);
		textName.setStrokeWidth(2);
		this.add(textName, 0, 0);
		GridPane.setMargin(textName, new Insets(20, 0, 30, 80));

		Text textBack = new Text("Back");
		textBack.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textBack.setFill(Color.WHITE);
		textBack.setStroke(Color.BLACK);
		textBack.setStrokeWidth(2);
		textBack.setEffect(new DropShadow());
		this.add(textBack, 0, 2);
		GridPane.setMargin(textBack, new Insets(20, 0, 80, 80));

		textBack.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getStartPane());
		});
		textBack.setOnMouseEntered(e -> {
			textBack.setEffect(new Glow(0.5));
		});
		textBack.setOnMouseExited(e -> {
			textBack.setEffect(new DropShadow());
		});

		container = new GridPane();
		container.setPrefWidth(ConfigConstant.startScreenWidth);
		container.setAlignment(Pos.TOP_CENTER);

		this.add(container, 0, 1);
		GridPane.setVgrow(container, Priority.ALWAYS);
	}

	public void loadHighScore() {
		container.getChildren().clear();
		try {
			HighScore.loadHighScore();
			Text name = new Text("Name");
			Text score = new Text("Score");
			name.setFont(ConfigConstant.Resource.HUD_HEADER_FONT);
			score.setFont(ConfigConstant.Resource.HUD_HEADER_FONT);
			name.setFill(Color.WHITE);
			score.setFill(Color.WHITE);
			name.setStroke(Color.BLACK);
			name.setStrokeWidth(2);
			score.setStroke(Color.BLACK);
			score.setStrokeWidth(2);
			container.add(name, 0, 0);
			container.add(score, 1, 0);
			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(50);
			container.getColumnConstraints().add(column1);
			int counter = 1;
			for (HighScoreRecord highScoreRecord : HighScoreRecord.getAllHighScoreRecords()) {
				Text Pname = new Text(highScoreRecord.getName());
				Text Pscore = new Text(Integer.toString(highScoreRecord.getScore()));
				Pname.setFont(ConfigConstant.Resource.HUD_MID_FONT);
				Pname.setFill(Color.WHITE);
				Pname.setStroke(Color.BLACK);
				Pname.setStrokeWidth(1);
				Pscore.setFont(ConfigConstant.Resource.HUD_MID_FONT);
				Pscore.setFill(Color.WHITE);
				Pscore.setStroke(Color.BLACK);
				Pscore.setStrokeWidth(1);

				container.add(Pname, 0, counter);
				container.add(Pscore, 1, counter);
				counter++;
			}
		} catch (HighScoreParsingException e) {
			// TODO Auto-generated catch block
			Text loadError = new Text("Load High Score Error");
			loadError.setFont(Font.font(48));
			container.add(loadError, 0, 0);
		}

	}
}
