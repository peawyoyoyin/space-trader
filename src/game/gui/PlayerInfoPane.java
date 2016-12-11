package game.gui;

import constants.ConfigConstant;
import javafx.scene.layout.BorderPane;

public class PlayerInfoPane extends BorderPane {
	
	public static PlayerInfoPane instance = new PlayerInfoPane();
	
	private PlayerStatusPane playerStatusPane;
	
	private PlayerInfoPane() {
		super();
		this.setPrefSize(ConfigConstant.gameScreen_left_width, ConfigConstant.gameScreenHeight);
		this.playerStatusPane = new PlayerStatusPane();
		this.setCenter(this.playerStatusPane);
	}
	
	public void update() {
		this.playerStatusPane.update();
	}
}