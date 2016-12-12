package game.gui;

import constants.ConfigConstant;
import javafx.scene.canvas.Canvas;

public class GamePane extends Canvas{

	public GamePane() {
		this(ConfigConstant.gameScreenWidth, ConfigConstant.gameScreenHeight);
	}
	
	public GamePane(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
	}

}
