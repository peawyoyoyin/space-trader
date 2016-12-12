package game.gui;

import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MinimapPane extends Canvas {
	public static MinimapPane instance = new MinimapPane();
	private IntegerProperty sectionX;
	private IntegerProperty sectionY;

	public static void initializeMinimap() {
		instance = new MinimapPane();
	}

	public MinimapPane() {
		this.setWidth(200);
		this.setHeight(200);
	}

	public void render(GraphicsContext gc) {
		gc.setStroke(Color.WHITE);
		for (int i = 0; i < 5; i++) {
			gc.strokeLine(0, 40 * i, 200, 40 * i);
		}
		for (int i = 0; i < 5; i++) {
			gc.strokeLine(40 * i, 0, 40 * i, 200);
		}
		gc.setFill(Color.AQUAMARINE);
		gc.translate(sectionX.get() * 40, sectionY.get() * 40);
		gc.fillPolygon(new double[]{ 5, 20, 35},new double[]{ 35, 5, 35},3);
		gc.translate(-sectionX.get() * 40, -sectionY.get() * 40);

	}
}
