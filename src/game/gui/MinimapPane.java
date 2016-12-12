package game.gui;

import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;

public class MinimapPane extends Canvas {
	public static MinimapPane instance = new MinimapPane();
	private IntegerProperty sectionX;
	private IntegerProperty sectionY;
	
	public static void initializeMinimap() {
		instance = new MinimapPane();
	}
	
	public MinimapPane() {
		
	}
	
	public void render() {
		
	}
}
