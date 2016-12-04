package startScreen;

import constants.ConfigConstant;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartPane extends GridPane {

	private String gameName = "Gamename";

	public StartPane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text(gameName);
		textName.setFont(ConfigConstant.START_SCREEN_FONT);
		this.add(textName, 0, 0);
		GridPane.setMargin(textName, new Insets(20,0,0,80));
		
		Text textStart = new Text("Start");
		textStart.setFont(Font.font(56));
		this.add(textStart, 0, 1);
		GridPane.setMargin(textStart, new Insets(20,0,20,200));
		
		Text textSetting = new Text("Setting");
		textSetting.setFont(Font.font(56));
		this.add(textSetting, 0, 2);
		GridPane.setMargin(textSetting, new Insets(20,0,20,200));
		
		Text textUpdating = new Text("Check for update");
		textUpdating.setFont(Font.font(56));
		this.add(textUpdating, 0, 3);
		GridPane.setMargin(textUpdating, new Insets(20,0,20,200));
		
		Text textExit = new Text("Exit");
		textExit.setFont(Font.font(56));
		this.add(textExit, 0, 4);
		GridPane.setMargin(textExit, new Insets(20,0,20,200));
		
		textStart.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getNewGamePane());
		});
		
		textSetting.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getSettingPane());
		});
		
		textUpdating.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getUpdatingPane());
		});
		
		textExit.setOnMouseClicked(e -> {
			Platform.exit();
		});
	}

}
