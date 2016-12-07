package game;

import com.sun.javafx.geom.Shape;

import input.Input;
import input.KeyCodeConstants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerShip extends Ship {

	private Image body;

	public PlayerShip(int x, int y, int hp, int maxHp, double speed, int maxSpeed, double accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);
		// TODO Auto-generated constructor stub
		this.body = new Image("res/startScreen/bg.png");
		this.radius = 25;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.rotate(this.direction+90);
		gc.drawImage(body, -25, -25, 50, 50);
		gc.rotate(-this.direction-90);
		gc.translate(-x, -y);
		
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		super.update();
		if (Input.isKeyDown(KeyCodeConstants.KEY_UP)) {
			forward();
		} else if (Input.isKeyDown(KeyCodeConstants.KEY_DOWN)) {
			back();
		}
		if (Input.isKeyDown(KeyCodeConstants.KEY_LEFT)) {
			turn(true);
		} else if (Input.isKeyDown(KeyCodeConstants.KEY_RIGHT)) {
			turn(false);
		}
		if (Input.isKeyDown(KeyCodeConstants.KEY_SHOOT)){
			shoot();
		}
	}

}
