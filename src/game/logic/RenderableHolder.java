package game.logic;

import java.util.Collections;

import com.sun.javafx.tk.Toolkit;

import constants.ConfigConstant;
import game.model.Enemy;
import game.model.Entity;
import game.model.SpaceStationEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
		drawInterface(gc, mc);
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

	public void drawInterface(GraphicsContext gc, MapCell mc) {
		double nearestEnemyDistance = Integer.MAX_VALUE;
		Entity nearestEnemy = null;
		for (Entity entity : mc.getEntities()) {
			if (entity instanceof SpaceStationEntity) {
				track(entity.x, entity.y, Color.AQUA, gc);
			}
			if (entity instanceof Enemy) {
				if (Math.hypot(entity.x - Player.instance.getPlayerShip().x,
						entity.y - Player.instance.getPlayerShip().y) < nearestEnemyDistance) {
					nearestEnemy = entity;
				}
			}
		}
		if (nearestEnemy != null) {
			track(nearestEnemy.x, nearestEnemy.y, Color.RED, gc);
		}
		if (Player.instance.getPlayerShip().x < ConfigConstant.mapCellWidth / 2
				&& Player.instance.getPlayerShip().x < Player.instance.getPlayerShip().y) {
			track(0, Player.instance.getPlayerShip().y, Color.LAWNGREEN, gc);
		} else if (Player.instance.getPlayerShip().y < ConfigConstant.mapCellHeight / 2
				&& Player.instance.getPlayerShip().y < Player.instance.getPlayerShip().x) {
			track(Player.instance.getPlayerShip().x, 0, Color.LAWNGREEN, gc);
		} else if (Player.instance.getPlayerShip().x > ConfigConstant.mapCellWidth / 2
				&& Player.instance.getPlayerShip().x > Player.instance.getPlayerShip().y) {
			track(ConfigConstant.mapCellWidth, Player.instance.getPlayerShip().y, Color.LAWNGREEN, gc);
		} else if (Player.instance.getPlayerShip().y > ConfigConstant.mapCellHeight / 2
				&& Player.instance.getPlayerShip().y > Player.instance.getPlayerShip().x) {
			track(Player.instance.getPlayerShip().x, ConfigConstant.mapCellHeight, Color.LAWNGREEN, gc);
		}
	}

	public void track(double x, double y, Color color, GraphicsContext gc) {
		double degree = Math.toDegrees(
				Math.atan2((y - Player.instance.getPlayerShip().y), (x - Player.instance.getPlayerShip().x)));
		double xArrow = Player.instance.getPlayerShip().x + Math.cos(Math.toRadians(degree)) * 70;
		double yArrow = Player.instance.getPlayerShip().y + Math.sin(Math.toRadians(degree)) * 70;
		double distance = Math.hypot(x - Player.instance.getPlayerShip().x, y - Player.instance.getPlayerShip().y);
		gc.setFill(color);
		gc.translate(xArrow, yArrow);
		gc.fillText(Integer.toString((int) distance),
				-Toolkit.getToolkit().getFontLoader().computeStringWidth(Integer.toString((int) distance), gc.getFont())
						/ 2,
				-15);
		gc.rotate(degree - 90);
		gc.fillPolygon(new double[] { -10, 0, 10, 0 }, new double[] { -10 * Math.sqrt(3) / 2, 10 * Math.sqrt(3) / 2,
				-10 * Math.sqrt(3) / 2, (-10 * Math.sqrt(3) / 2) + 3 }, 4);
		gc.rotate(-degree + 90);
		gc.translate(-xArrow, -yArrow);
	}
}
