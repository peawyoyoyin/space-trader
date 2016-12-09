package game;

import constants.ConfigConstant;
import input.Input;
import input.KeyCodeConstants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerShip extends Ship implements Friendly{

	public PlayerShip(int x, int y, int hp, int maxHp, double speed, int maxSpeed, double accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);
		// TODO Auto-generated constructor stub
		this.radius = 25;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.rotate(this.direction + 90);
		gc.setFill(Color.DARKGRAY);
		gc.fillRect(-25, -25, 50, 50);
		gc.rotate(-this.direction - 90);
		gc.translate(-x, -y);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		MapCell mc = MapCellHolder.instance.get(Player.instance.getSectionX(), Player.instance.getSectionY());
		if (this.x <= radius && Player.instance.getSectionX() > 0) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX() - 1,
					Player.instance.getSectionY());
			Player.instance.setSectionX(Player.instance.getSectionX() - 1);
			this.x = ConfigConstant.mapCellWidth - radius - 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());

		} else if (this.y <= radius && Player.instance.getSectionY() > 0) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX(),
					Player.instance.getSectionY() - 1);
			Player.instance.setSectionY(Player.instance.getSectionY() - 1);
			this.y = ConfigConstant.mapCellHeight - radius - 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());

		}
		if (this.x >= ConfigConstant.mapCellWidth - radius && Player.instance.getSectionX() < 4) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX() + 1,
					Player.instance.getSectionY());
			Player.instance.setSectionX(Player.instance.getSectionX() + 1);
			this.x = radius + 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());

		} else if (this.y >= ConfigConstant.mapCellHeight - radius && Player.instance.getSectionY() < 4) {
			MapCell mcNext = MapCellHolder.instance.get(Player.instance.getSectionX(),
					Player.instance.getSectionY() + 1);
			Player.instance.setSectionY(Player.instance.getSectionY() + 1);
			this.y = radius + 1;
			mc.getEntities().remove(Player.instance.getPlayerShip());
			mc.clear();
			mcNext.getEntities().add(Player.instance.getPlayerShip());
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
			shoot();
		}
	}

}
