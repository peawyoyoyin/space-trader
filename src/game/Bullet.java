package game;

import constants.ConfigConstant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Entity implements Renderable {

	private double speed;
	private int direction;
	private int damage;
	private Ship shooter;

	public Ship getShooter() {
		return shooter;
	}

	public int getDamage() {
		return damage;
	}

	public Bullet(double x, double y, double speed, int direction, int damage, Ship shooter) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;
		this.damage = damage;
		this.shooter = shooter;
		this.radius = 5;
		this.z = 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.x += Math.cos(Math.toRadians(this.direction)) * speed;
		this.y += Math.sin(Math.toRadians(this.direction)) * speed;
		if (x > ConfigConstant.mapCellWidth + radius || x < -radius || y > ConfigConstant.mapCellHeight + radius
				|| y < -radius) {
			this.destroyed = true;
		}
		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		for (Entity entity : mc.getEntities()) {
			if (entity instanceof Ship && entity != this.getShooter()
					&& this.isCollideWith(entity)) {
				this.destroyed = true;
				((Ship) entity).hit(this.getDamage());
				mc.addNewEffect(new BombEffect(this.x, this.y, 40, 10));
			}

		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.rotate(this.direction + 90);
		gc.setFill(Color.BLUE);
		gc.fillRect(-5, -5, 10, 10);
		gc.rotate(-this.direction - 90);
		gc.translate(-x, -y);
	}

}
