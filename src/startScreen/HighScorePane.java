package startScreen;

import constants.ConfigConstant;
import highscore.HighScore;
import highscore.HighScoreParsingException;
import highscore.HighScoreRecord;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HighScorePane extends GridPane {
	private GridPane container;

	public HighScorePane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text("High Score");
		textName.setFont(ConfigConstant.START_SCREEN_FONT);
		this.add(textName, 0, 0);
		GridPane.setMargin(textName, new Insets(20, 0, 0, 80));

		Text textBack = new Text("Back");
		textBack.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		this.add(textBack, 0, 2);
		GridPane.setMargin(textBack, new Insets(20, 0, 80, 80));
		
		textBack.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getStartPane());
		});

		container = new GridPane();
		container.setPrefWidth(ConfigConstant.startScreenWidth);
		container.setAlignment(Pos.TOP_CENTER);
		
		this.add(container, 0, 1);
		GridPane.setVgrow(container, Priority.ALWAYS);
	}
	
	public void loadHighScore(){
		container.getChildren().clear();
		try {
			HighScore.loadHighScore();
			Text name = new Text("Name");
			Text score = new Text("Score");
			container.add(name,0,0);
			container.add(score, 1, 0);
			int counter = 1;
			for (HighScoreRecord highScoreRecord : HighScoreRecord.getAllHighScoreRecords()) {
				Text Pname = new Text(highScoreRecord.getName());
				Text Pscore = new Text(Integer.toString(highScoreRecord.getScore()));
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
