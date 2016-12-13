package startScreen;

import constants.ConfigConstant;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

public class StartPane extends GridPane {
	public StartPane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text(ConfigConstant.GAME_NAME);
		textName.setFont(ConfigConstant.START_SCREEN_FONT);
		textName.setFill(Color.WHITE);
		textName.setStroke(Color.BLACK);
		textName.setStrokeWidth(2);
		this.add(textName, 0, 0);
		GridPane.setMargin(textName, new Insets(40,0,0,80));
		
		Text textStart = new Text("Start");
		textStart.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textStart.setFill(Color.WHITE);
		textStart.setStroke(Color.BLACK);
		textStart.setStrokeWidth(2);
		this.add(textStart, 0, 1);
		GridPane.setMargin(textStart, new Insets(30,0,10,200));
		
		Text textHighScore = new Text("High Score");
		textHighScore.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textHighScore.setFill(Color.WHITE);
		textHighScore.setStroke(Color.BLACK);
		textHighScore.setStrokeWidth(2);
		this.add(textHighScore, 0, 2);
		GridPane.setMargin(textHighScore, new Insets(10,0,10,200));
		
		Text textSetting = new Text("Setting");
		textSetting.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textSetting.setFill(Color.WHITE);
		textSetting.setStroke(Color.BLACK);
		textSetting.setStrokeWidth(2);
		this.add(textSetting, 0, 3);
		GridPane.setMargin(textSetting, new Insets(10,0,10,200));
		
		Text textUpdating = new Text("Update");
		textUpdating.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textUpdating.setFill(Color.WHITE);
		textUpdating.setStroke(Color.BLACK);
		textUpdating.setStrokeWidth(2);
		this.add(textUpdating, 0, 4);
		GridPane.setMargin(textUpdating, new Insets(10,0,10,200));
		
		Text textExit = new Text("Exit");
		textExit.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textExit.setFill(Color.WHITE);
		textExit.setStroke(Color.BLACK);
		textExit.setStrokeWidth(2);
		this.add(textExit, 0, 5);
		GridPane.setMargin(textExit, new Insets(10,0,10,200));
		
		textStart.setOnMouseClicked(e -> {
			Main.newGame();
		});
		
		textHighScore.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getHighScorePane());
			StartScreen.getInstace().getHighScorePane().loadHighScore();
		});
		
		textSetting.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getSettingPane());
		});
		
		textUpdating.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getUpdatingPane());
			StartScreen.getInstace().getUpdatingPane().startUpdate();
		});
		
		textExit.setOnMouseClicked(e -> {
			Platform.exit();
		});
	}

}
