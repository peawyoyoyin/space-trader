package game.model;

import java.util.Random;

import constants.ConfigConstant;
import game.logic.MapCell;
import game.logic.MapCellHolder;
import game.logic.Player;
import input.Input;
import input.KeyCodeConstants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PlayerShip extends Ship implements Friendly {

	private IntegerProperty hpProperty;
	private IntegerProperty maxHpProperty;
	private DoubleProperty speedProperty;

	private Image image;
	private int delayShoot;
	private int counterdelay;

	public PlayerShip(int x, int y, int hp, int maxHp, double speed, int maxSpeed, double accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);

		this.radius = 35;
		this.image = ConfigConstant.Resource.SHIP_IMAGE;
		this.delayShoot = 10;
		this.counterdelay = 0;
		this.hpProperty = new SimpleIntegerProperty();
		this.hpProperty.set(hp);
		this.maxHpProperty = new SimpleIntegerProperty();
		this.maxHpProperty.set(maxHp);
		this.speedProperty = new SimpleDoubleProperty(this.maxSpeed);
		this.z = 5;
	}

	@Override
	public void setMaxSpeed(double maxSpeed) {

		super.setMaxSpeed(maxSpeed);
		this.speedProperty.set(maxSpeed);
	}

	public IntegerProperty getShipProperty(String indicator) {
		switch (indicator) {
		case "hp": {
			return this.hpProperty;
		}
		case "maxHp": {
			return this.maxHpProperty;
		}
		default: {
			return this.hpProperty;
		}
		}
	}

	public DoubleProperty getSpeedProperty() {
		return this.speedProperty;
	}

	@Override
	public void render(GraphicsContext gc) {

		gc.translate(x, y);
		gc.rotate(this.direction + 90);
		gc.drawImage(image, -image.getWidth() / 2, -image.getHeight() / 2);
		gc.rotate(-this.direction - 90);
		gc.translate(-x, -y);

	}

	@Override
	public void setMaxHp(int maxHp) {

		super.setMaxHp(maxHp);
		this.maxHpProperty.set(maxHp);
	}

	@Override
	public void hit(int damage) {

		super.hit(damage);
		this.hpProperty.set(this.getHp());
	}

	@Override
	public void update() {

		boolean isSpawnEnemy = true;
		boolean isChangeSec = false;

		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		if (this.x <= radius && Player.instance.getSectionX() > 0) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX() - 1,
					Player.instance.getSectionY());
			Player.instance.setSectionX(Player.instance.getSectionX() - 1);
			this.x = ConfigConstant.mapCellWidth - radius - 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());

			isChangeSec = true;
			for (Entity entity : mcNext.getEntities()) {
				if (entity instanceof SpaceStationEntity) {
					isSpawnEnemy = false;
				}
			}

		} else if (this.y <= radius && Player.instance.getSectionY() > 0) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX(),
					Player.instance.getSectionY() - 1);
			Player.instance.setSectionY(Player.instance.getSectionY() - 1);
			this.y = ConfigConstant.mapCellHeight - radius - 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());

			isChangeSec = true;
			for (Entity entity : mcNext.getEntities()) {
				if (entity instanceof SpaceStationEntity) {
					isSpawnEnemy = false;
				}
			}

		}
		if (this.x >= ConfigConstant.mapCellWidth - radius && Player.instance.getSectionX() < 4) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX() + 1,
					Player.instance.getSectionY());
			Player.instance.setSectionX(Player.instance.getSectionX() + 1);
			this.x = radius + 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());

			isChangeSec = true;
			for (Entity entity : mcNext.getEntities()) {
				if (entity instanceof SpaceStationEntity) {
					isSpawnEnemy = false;
				}
			}

		} else if (this.y >= ConfigConstant.mapCellHeight - radius && Player.instance.getSectionY() < 4) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX(),
					Player.instance.getSectionY() + 1);
			Player.instance.setSectionY(Player.instance.getSectionY() + 1);
			this.y = radius + 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());

			isChangeSec = true;
			for (Entity entity : mcNext.getEntities()) {
				if (entity instanceof SpaceStationEntity) {
					isSpawnEnemy = false;
				}
			}
		}

		if (isSpawnEnemy && isChangeSec) {
			Random rd = new Random();
			MapCell mc2 = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
			for (int i = 0; i < rd.nextInt(3) + 2; i++) {
				Ship enemy = new EnemyShip(rd.nextDouble() * ConfigConstant.mapCellWidth,
						rd.nextDouble() * ConfigConstant.mapCellHeight, mc.getEnemyHp(), mc.getEnemyHp(), 0, 3, 0.1, 1,
						rd.nextInt(360));
				enemy.setBulletDamage(mc.getEnemyDmg());
				mc2.getEntities().add(enemy);
			}

		}
		super.update();

		if (Input.isKeyDown(KeyCodeConstants.KEY_UP)) {
			forward();
		} else if (Input.isKeyDown(KeyCodeConstants.KEY_DOWN)) {
			back();
		} else {
			this.speed = this.speed * 0.98;
		}
		if (Input.isKeyDown(KeyCodeConstants.KEY_LEFT)) {
			turn(true);
		} else if (Input.isKeyDown(KeyCodeConstants.KEY_RIGHT)) {
			turn(false);
		}
		if (Input.isKeyDown(KeyCodeConstants.KEY_SHOOT)) {
			if (counterdelay == 0) {
				shoot();
			}
			counterdelay++;
			if (counterdelay == delayShoot) {
				this.counterdelay = 0;
			}
		} else {
			this.counterdelay = 0;
		}

	}

}