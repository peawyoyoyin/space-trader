package startScreen;

import constants.ConfigConstant;
import game.logic.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
		
		GridPane container = new GridPane();
		container.setPrefWidth(ConfigConstant.startScreenWidth);
		container.setAlignment(Pos.CENTER);
		Text textPlayerName = new Text("Player Name");
		container.add(textPlayerName, 0, 0);
		TextField playerNameField = new TextField(ConfigConstant.PLAYER_NAME);
		container.add(playerNameField,1 , 0);
		Text textVolumeMusic = new Text("Volume Music");
		container.add(textVolumeMusic, 0, 1);
		Slider sliderMusic = new Slider(0,1,ConfigConstant.volumeMusic);
		container.add(sliderMusic, 1, 1);
		Text textVolumeSFX = new Text("Volume SFX");
		container.add(textVolumeSFX, 0, 2);
		this.add(container, 0, 1);
		Slider sliderSFX = new Slider(0,1,ConfigConstant.volumeSFX);
		container.add(sliderSFX, 1, 2);
		GridPane.setVgrow(container, Priority.ALWAYS);
		sliderMusic.setOnMouseReleased(e ->{
			ConfigConstant.volumeMusic = sliderMusic.getValue();
		});
		sliderSFX.setOnMouseReleased(e ->{
			ConfigConstant.volumeSFX = sliderSFX.getValue();
		});
		playerNameField.textProperty().addListener(e -> {
			System.out.println("hhhh");
			ConfigConstant.PLAYER_NAME = playerNameField.getText();
		});
	}

}
