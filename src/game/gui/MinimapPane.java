package game.gui;

import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import game.model.Entity;
import game.model.SpaceStationEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MinimapPane extends Canvas {
	public static MinimapPane instance = new MinimapPane();
	private IntegerProperty sectionX;
	private IntegerProperty sectionY;
	private GraphicsContext gc = this.getGraphicsContext2D();

	public static void initializeMinimap() {
		instance = new MinimapPane();
	}

	public MinimapPane() {
		super(200,200);
		this.sectionX = new SimpleIntegerProperty();
		this.sectionY = new SimpleIntegerProperty();
		this.sectionX.bind(Player.instance.getSectionXProperty());
		this.sectionY.bind(Player.instance.getSectionYProperty());
		render();
		this.sectionX.addListener(event -> {
			render();
		});
		
		this.sectionY.addListener(event -> {
			render();
		});
	}

	public void render() {
		gc.clearRect(0, 0, 200, 200);
		for (int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				MapCell mc = MapCellHolder.instance.get(i, j);
				gc.setFill(Color.BLUEVIOLET);
				gc.fillRect(40*i, 40*j, 40, 40);
				for (Entity entity : mc.getEntities()) {
					if(entity instanceof SpaceStationEntity) {
						gc.setFill(Color.CADETBLUE);
						gc.fillOval(40*i+5, 40*j+5, 30, 30);
						break;
					}
				}
			}
		}
		
		gc.setStroke(Color.BEIGE);
		for (int i = 0; i < 6; i++) {
			gc.strokeLine(0, 40 * i, 200, 40 * i);
		}
		for (int i = 0; i < 6; i++) {
			gc.strokeLine(40 * i, 0, 40 * i, 200);
		}

		gc.setFill(Color.AQUAMARINE);
		gc.translate(sectionX.get() * 40, sectionY.get() * 40);
		gc.fillPolygon(new double[]{ 5, 20, 35},new double[]{ 35, 5, 35},3);
		gc.translate(-sectionX.get() * 40, -sectionY.get() * 40);

	}
}
