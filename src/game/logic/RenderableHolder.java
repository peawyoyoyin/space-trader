package game.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import constants.ConfigConstant;
import game.model.Enemy;
import game.model.Entity;
import game.model.SpaceStationEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
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
		this.x = Player.instance.getPlayerShip().getX() - ConfigConstant.gameScreenWidth / 2;
		this.y = Player.instance.getPlayerShip().getY() - ConfigConstant.gameScreenHeight / 2;
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
		drawNameTrader(gc);
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
				track(entity.getX(), entity.getY(), Color.AQUA, gc);
			}
			if (entity instanceof Enemy) {
				if (Math.hypot(entity.getX() - Player.instance.getPlayerShip().getX(),
						entity.getY() - Player.instance.getPlayerShip().getY()) < nearestEnemyDistance) {
					nearestEnemyDistance = Math.hypot(entity.getX() - Player.instance.getPlayerShip().getX(),
							entity.getY() - Player.instance.getPlayerShip().getY());
					nearestEnemy = entity;
				}
			}
		}
		if (nearestEnemy != null) {
			track(nearestEnemy.getX(), nearestEnemy.getY(), Color.RED, gc);
		}
		List<Double> distanceList = Arrays.asList(Player.instance.getPlayerShip().getX(),
				Player.instance.getPlayerShip().getY(),
				ConfigConstant.mapCellWidth - Player.instance.getPlayerShip().getX(),
				ConfigConstant.mapCellHeight - Player.instance.getPlayerShip().getY());
		if (Player.instance.getPlayerShip().getX() == Collections.min(distanceList)) {
			track(0, Player.instance.getPlayerShip().getY(), Color.LAWNGREEN, gc);
		} else if (Player.instance.getPlayerShip().getY() == Collections.min(distanceList)) {
			track(Player.instance.getPlayerShip().getX(), 0, Color.LAWNGREEN, gc);
		} else if (ConfigConstant.mapCellWidth - Player.instance.getPlayerShip().getX() == Collections
				.min(distanceList)) {
			track(ConfigConstant.mapCellWidth, Player.instance.getPlayerShip().getY(), Color.LAWNGREEN, gc);
		} else if (ConfigConstant.mapCellHeight - Player.instance.getPlayerShip().getY() == Collections
				.min(distanceList)) {
			track(Player.instance.getPlayerShip().getX(), ConfigConstant.mapCellHeight, Color.LAWNGREEN, gc);
		}
	}

	public void drawNameTrader(GraphicsContext gc) {
		if (MapCellHolder.instance.getPlayerCell().isPlayerColideWithSpacestation() && !Player.instance.isPause()) {
			gc.setFill(Color.WHITE);
			gc.setFont(ConfigConstant.Resource.HUD_FONT);
			gc.setEffect(new DropShadow());
			String text = "Trader : " + MapCellHolder.instance.getPlayerCell().getSpaceStation().getTrader().getName();
			FontLoader fl = Toolkit.getToolkit().getFontLoader();
			double textX = (ConfigConstant.gameScreenWidth
					- fl.computeStringWidth(text, ConfigConstant.Resource.HUD_FONT)) / 2;
			gc.fillText(text, textX, 100);
			gc.setEffect(null);
		}
	}

	public void track(double x, double y, Color color, GraphicsContext gc) {
		double degree = Math.toDegrees(
				Math.atan2((y - Player.instance.getPlayerShip().getY()), (x - Player.instance.getPlayerShip().getX())));
		double xArrow = Player.instance.getPlayerShip().getX() + Math.cos(Math.toRadians(degree)) * 70;
		double yArrow = Player.instance.getPlayerShip().getY() + Math.sin(Math.toRadians(degree)) * 70;
		double distance = Math.hypot(x - Player.instance.getPlayerShip().getX(),
				y - Player.instance.getPlayerShip().getY());
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
