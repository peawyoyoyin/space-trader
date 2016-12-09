package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyShip extends Ship implements Enemy{

	public EnemyShip(double x, double y, int hp, int maxHp, double speed, int maxSpeed, double accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);
		// TODO Auto-generated constructor stub
		this.radius = 25;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		if ((double) this.hp / this.maxHp * 100 > 25) {
			gc.setFill(Color.GREENYELLOW);
		} else {
			gc.setFill(Color.ORANGERED);
			;
		}
		gc.fillRect(-25, -35, (double) this.hp / this.maxHp * 50, 5);
		gc.rotate(this.direction + 90);
		gc.setFill(Color.RED);
		gc.fillRect(-25, -25, 50, 50);
		gc.rotate(-this.direction - 90);
		gc.translate(-x, -y);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

}
