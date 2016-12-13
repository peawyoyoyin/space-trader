package game.model;

import constants.ConfigConstant;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class EnemyShip extends Ship implements Enemy {
	private int counterdelay;
	private int delayShoot;
	private Image image;

	public EnemyShip(double x, double y, int hp, int maxHp, double speed, int maxSpeed, double accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);
		// TODO Auto-generated constructor stub
		this.radius = 35;
		this.counterdelay = 0;
		this.delayShoot = 10;
		this.image = ConfigConstant.Resource.ENEMY_IMAGE;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.rotate(this.direction - 90);
		gc.drawImage(image, - image.getWidth()/2, -image.getHeight()/2);
		gc.rotate(-this.direction + 90);
		if ((double) this.hp / this.maxHp * 100 > 25) {
			gc.setFill(Color.GREENYELLOW);
		} else {
			gc.setFill(Color.ORANGERED);
			;
		}
		gc.fillRect(-25, -35, (double) this.hp / this.maxHp * 50, 5);
		gc.translate(-x, -y);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		Entity nearestEnemy = getEnemyNearest();
		if (nearestEnemy != null) {
			attack(nearestEnemy);
		} else {
			idle();
		}
	}

	private void idle() {
		// TODO Auto-generated method stub
		forward();
		turn(false);
		this.counterdelay = 0;
	}

	private void attack(Entity nearestEnemy) {
		// TODO Auto-generated method stub
		double degree = Math.toDegrees(Math.atan2((nearestEnemy.y - y), (nearestEnemy.x - x)));
		double directionNormalize = this.direction < 0 ? this.direction + 360 : this.direction;
		if (degree < 0) {
			degree += 360;
		}
		double delta = degree - directionNormalize < 0 ? degree - directionNormalize + 360
				: degree - directionNormalize;
		if (Math.abs(degree - directionNormalize) < 5) {
			if (counterdelay == 0) {
				shoot();
			}
			counterdelay++;
			if (counterdelay == delayShoot) {
				this.counterdelay = 0;
			}
		}
		if (delta > 1) {
			if (delta < 180) {
				turn(false);
			} else {
				turn(true);
			}
		}

		if (Math.hypot(this.x - nearestEnemy.x, this.y - nearestEnemy.y) < 100) {
			stop();
		} else {
			forward();
		}
	}

	public Entity getEnemyNearest() {
		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		double nearestEnemyDistance = 1000;
		Entity nearestEnemy = null;
		for (Entity entity : mc.getEntities()) {
			if (entity instanceof Friendly) {
				if (Math.hypot(entity.x - this.x, entity.y - this.y) < nearestEnemyDistance) {
					nearestEnemy = entity;
				}
			}
		}
		return nearestEnemy;
	}

	public void stop() {
		this.speed = -this.accelerate;
		if (this.speed < 0) {
			this.speed = 0;
		}
	}

}
