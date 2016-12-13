package startScreen;

import constants.ConfigConstant;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
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
		textStart.setEffect(new DropShadow());
		this.add(textStart, 0, 1);
		GridPane.setMargin(textStart, new Insets(30,0,10,200));
		
		Text textHighScore = new Text("High Score");
		textHighScore.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textHighScore.setFill(Color.WHITE);
		textHighScore.setStroke(Color.BLACK);
		textHighScore.setStrokeWidth(2);
		textHighScore.setEffect(new DropShadow());
		this.add(textHighScore, 0, 2);
		GridPane.setMargin(textHighScore, new Insets(10,0,10,200));
		
		Text textSetting = new Text("Setting");
		textSetting.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textSetting.setFill(Color.WHITE);
		textSetting.setStroke(Color.BLACK);
		textSetting.setStrokeWidth(2);
		textSetting.setEffect(new DropShadow());
		this.add(textSetting, 0, 3);
		GridPane.setMargin(textSetting, new Insets(10,0,10,200));
		
		Text textUpdating = new Text("Update");
		textUpdating.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textUpdating.setFill(Color.WHITE);
		textUpdating.setStroke(Color.BLACK);
		textUpdating.setStrokeWidth(2);
		textUpdating.setEffect(new DropShadow());
		this.add(textUpdating, 0, 4);
		GridPane.setMargin(textUpdating, new Insets(10,0,10,200));
		
		Text textExit = new Text("Exit");
		textExit.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		textExit.setFill(Color.WHITE);
		textExit.setStroke(Color.BLACK);
		textExit.setStrokeWidth(2);
		textExit.setEffect(new DropShadow());
		this.add(textExit, 0, 5);
		GridPane.setMargin(textExit, new Insets(10,0,10,200));
		
		textStart.setOnMouseClicked(e -> {
			Main.newGame();
		});
		textStart.setOnMouseEntered(e -> {
			textStart.setEffect(new Glow(0.5));
		});
		textStart.setOnMouseExited(e -> {
			textStart.setEffect(new DropShadow());
		});
		
		textHighScore.setOnMouseClicked(e -> {
			StartScreen.getInstance().changePane(StartScreen.getInstance().getHighScorePane());
			StartScreen.getInstance().getHighScorePane().loadHighScore();
		});
		textHighScore.setOnMouseEntered(e -> {
			textHighScore.setEffect(new Glow(0.5));
		});
		textHighScore.setOnMouseExited(e -> {
			textHighScore.setEffect(new DropShadow());
		});
		
		textSetting.setOnMouseClicked(e -> {
			StartScreen.getInstance().changePane(StartScreen.getInstance().getSettingPane());
		});
		textSetting.setOnMouseEntered(e -> {
			textSetting.setEffect(new Glow(0.5));
		});
		textSetting.setOnMouseExited(e -> {
			textSetting.setEffect(new DropShadow());
		});
		
		textUpdating.setOnMouseClicked(e -> {
			StartScreen.getInstance().changePane(StartScreen.getInstance().getUpdatingPane());
			StartScreen.getInstance().getUpdatingPane().startUpdate();
		});
		textUpdating.setOnMouseEntered(e -> {
			textUpdating.setEffect(new Glow(0.5));
		});
		textUpdating.setOnMouseExited(e -> {
			textUpdating.setEffect(new DropShadow());
		});
		
		textExit.setOnMouseClicked(e -> {
			Platform.exit();
		});
		textExit.setOnMouseEntered(e -> {
			textExit.setEffect(new Glow(0.5));
		});
		textExit.setOnMouseExited(e -> {
			textExit.setEffect(new DropShadow());
		});
	}

}
