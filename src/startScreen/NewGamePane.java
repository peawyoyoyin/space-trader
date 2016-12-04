package startScreen;

import constants.ConfigConstant;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewGamePane extends GridPane {

	public NewGamePane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text("Start Game");
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
		
		VBox container = new VBox();
		this.add(container, 0, 1);
		
		Text textNew = new Text("New Game");
		textNew.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		container.getChildren().add(textNew);
		VBox.setMargin(textNew, new Insets(20,0,20,200));
		
		Text textLoad = new Text("Load Game");
		textLoad.setFont(ConfigConstant.START_SCREEN_ITEM_FONT);
		container.getChildren().add(textLoad);
		VBox.setMargin(textLoad, new Insets(20,0,20,200));
		
		GridPane.setVgrow(container, Priority.ALWAYS);
		
		textNew.setOnMouseClicked(e -> {
			
		});
		
		textLoad.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getLoadGamePane());
		});
	}

}
