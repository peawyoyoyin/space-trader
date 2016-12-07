package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BossShip extends Ship {

	public BossShip(double x, double y, int hp, int maxHp, double speed, int maxSpeed, double accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);
		// TODO Auto-generated constructor stub
		this.radius = 25;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.rotate(this.direction+90);
		gc.setFill(Color.RED);
		gc.fillRect( -25, -25, 50, 50);
		gc.rotate(-this.direction-90);
		gc.translate(-x, -y);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
