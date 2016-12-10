package game.logic;

import java.util.Collections;

import constants.ConfigConstant;
import game.model.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RenderableHolder {
	public static RenderableHolder instance = new RenderableHolder();

	private Image background;
	private double x;
	private double y;

	public RenderableHolder() {
		this.background = ConfigConstant.Resource.MAP_BACKGROUND;
	}

	public void render(GraphicsContext gc, MapCell mc) {
		this.x = Player.instance.getPlayerShip().x - ConfigConstant.gameScreenWidth / 2;
		this.y = Player.instance.getPlayerShip().y - ConfigConstant.gameScreenHeight / 2;
		if (this.x < 0) {
			this.x = 0;
		}
		if (this.x > ConfigConstant.mapCellWidth - ConfigConstant.gameScreenWidth) {
			this.x = ConfigConstant.mapCellWidth - ConfigConstant.gameScreenWidth;
		}
		if (this.y < 0) {
			this.y = 0;
		}
		if (this.y > ConfigConstant.mapCellHeight - ConfigConstant.gameScreenHeight) {
			this.y = ConfigConstant.mapCellHeight - ConfigConstant.gameScreenHeight;
		}
		gc.translate(-x, -y);
		drawBackground(gc);
		Collections.sort(mc.getEntities());
		for (Entity entity : mc.getEntities()) {
			if (entity instanceof Renderable) {
				((Renderable) entity).render(gc);
			}
		}
		drawInterface(gc);
		gc.translate(x, y);
	}

	public void drawBackground(GraphicsContext gc) {
		// TODO Auto-generated method stub
		ImagePattern backgroundRepeated = new ImagePattern(this.background, 0, 0,
				this.background.getWidth() / ConfigConstant.mapCellWidth,
				this.background.getHeight() / ConfigConstant.mapCellHeight, true);
		gc.setFill(backgroundRepeated);
		gc.fillRect(0, 0, ConfigConstant.mapCellWidth, ConfigConstant.mapCellHeight);
	}

	public void drawInterface(GraphicsContext gc) {

	}
}
