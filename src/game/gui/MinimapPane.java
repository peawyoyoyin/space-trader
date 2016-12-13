package game.gui;

import constants.ConfigConstant;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import game.model.Entity;
import game.model.SpaceStationEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class MinimapPane extends Canvas {
	public static MinimapPane instance = new MinimapPane();
	private IntegerProperty sectionX;
	private IntegerProperty sectionY;
	private GraphicsContext gc = this.getGraphicsContext2D();

	public static void initializeMinimap() {
		instance = new MinimapPane();
	}

	public MinimapPane() {
		super(200, 200);
		this.sectionX = new SimpleIntegerProperty();
		this.sectionY = new SimpleIntegerProperty();
		this.sectionX.bind(Player.instance.getSectionXProperty());
		this.sectionY.bind(Player.instance.getSectionYProperty());
		System.out.println(sectionX.get());
		render();
		this.sectionX.addListener(event -> {
			render();
		});

		this.sectionY.addListener(event -> {
			render();
		});
	}

	public void render() {
		Image spaceSt = ConfigConstant.Resource.SPACE_STATION;
		Image player = ConfigConstant.Resource.SHIP_IMAGE;
		Image background = ConfigConstant.Resource.MAP_BACKGROUND;
		ImagePattern backgroundRepeated = new ImagePattern(background, 0, 0, background.getWidth() / 1000,
				background.getHeight() / 1000, true);
		gc.setFill(backgroundRepeated);
		gc.fillRect(0, 0, 200, 200);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				MapCell mc = MapCellHolder.instance.get(i, j);
				for (Entity entity : mc.getEntities()) {
					if (entity instanceof SpaceStationEntity) {
						gc.drawImage(spaceSt, 40 * i + 5, 40 * j + 5, 30, 30);
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

		gc.translate(sectionX.get() * 40, sectionY.get() * 40);
		gc.drawImage(player, 10, 10, 20, 20);
		gc.translate(-sectionX.get() * 40, -sectionY.get() * 40);

	}
}
