package startScreen;

import constants.ConfigConstant;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SettingPane extends GridPane{

	public SettingPane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text("Setting");
		textName.setFont(ConfigConstant.START_SCREEN_FONT);
		this.add(textName, 0, 0);
		GridPane.setMargin(textName, new Insets(20,0,0,80));
		
		Text textBack = new Text("Back");
		textBack.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		this.add(textBack, 0, 2);
		GridPane.setMargin(textBack, new Insets(20,0,80,80));
		
		textBack.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getStartPane());
		});
		
		TilePane container = new TilePane();
		this.add(container, 0, 1);
		GridPane.setVgrow(container, Priority.ALWAYS);
	}

}
