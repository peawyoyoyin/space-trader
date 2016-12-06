package game;

import javafx.scene.canvas.GraphicsContext;

public class BossShip extends Ship {

	public BossShip(int x, int y, int hp, int maxHp, int speed, int maxSpeed, int accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCollidingWith(Collidable other) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
