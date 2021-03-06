package game.model;

import constants.ConfigConstant;
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

	public void changeSector(String direction) {
		MapCellHolder.instance.getPlayerCell().getEntities().remove(Player.instance.getPlayerShip());
		MapCellHolder.instance.getPlayerCell().clear();
		switch (direction) {
		case "right":
			Player.instance.setSectionX(Player.instance.getSectionX() + 1);
			this.x = radius + 1;
			break;

		case "left":
			Player.instance.setSectionX(Player.instance.getSectionX() - 1);
			this.x = ConfigConstant.mapCellWidth - radius - 1;
			break;

		case "up":
			Player.instance.setSectionY(Player.instance.getSectionY() - 1);
			this.y = ConfigConstant.mapCellHeight - radius - 1;
			break;

		case "down":
			Player.instance.setSectionY(Player.instance.getSectionY() + 1);
			this.y = radius + 1;
			break;

		default:
			break;
		}
		MapCellHolder.instance.getPlayerCell().getEntities().add(Player.instance.getPlayerShip());
		if (!MapCellHolder.instance.getPlayerCell().hasSpaceStation()) {
			MapCellHolder.instance.getPlayerCell().spawnEnemy();
		} else {
			MapCellHolder.instance.getPlayerCell().getSpaceStation().getTrader().resetItemsOnSale();
			MapCellHolder.instance.getPlayerCell().getSpaceStation().getTrader().generateItems();
		}
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

		if (this.x <= radius && Player.instance.getSectionX() > 0) {
			changeSector("left");

		} else if (this.y <= radius && Player.instance.getSectionY() > 0) {
			changeSector("up");

		}
		if (this.x >= ConfigConstant.mapCellWidth - radius && Player.instance.getSectionX() < 4) {
			changeSector("right");

		} else if (this.y >= ConfigConstant.mapCellHeight - radius && Player.instance.getSectionY() < 4) {
			changeSector("down");
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