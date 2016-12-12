package game.gui;

import game.logic.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;

public class MinimapPane extends Canvas {
	public static MinimapPane instance = new MinimapPane();
	private IntegerProperty sectionX;
	private IntegerProperty sectionY;
	
	public static void initializeMinimap() {
		instance = new MinimapPane();
	}
	
	public MinimapPane() {
		this.sectionX = new SimpleIntegerProperty();
		this.sectionY = new SimpleIntegerProperty();
		this.sectionX.bind(Player.instance.getSectionXProperty());
		this.sectionY.bind(Player.instance.getSectionYProperty());
		
		this.sectionX.addListener(event -> {
			render();
		});
		
		this.sectionY.addListener(event -> {
			render();
		});
	}
	
	public void render() {
		
	}
}
