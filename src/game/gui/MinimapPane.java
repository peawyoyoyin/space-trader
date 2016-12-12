package game.gui;

import game.logic.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
