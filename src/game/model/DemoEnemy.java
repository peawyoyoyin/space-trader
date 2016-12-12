package game.model;

import constants.ConfigConstant;
import game.logic.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import startScreen.BackgroundStartScreen;;

public class DemoEnemy extends Ship implements Renderable{

	private Image image;
	private boolean isTurnLeft;

	public DemoEnemy(double x, double y, int hp, int maxHp, double speed, int maxSpeed, double accelerate, int turnRate,
			int direction) {
		super(x, y, hp, maxHp, speed, maxSpeed, accelerate, turnRate, direction);
		// TODO Auto-generated constructor stub
		this.image = ConfigConstant.Resource.ENEMY_IMAGE;
		this.isTurnLeft = false;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		BackgroundStartScreen.addEffect(new TailShipEffect(this.x + Math.cos(Math.toRadians(this.direction + 180)) * 10,
				this.y + Math.sin(Math.toRadians(this.direction + 180)) * 10, 40, this.direction));
		this.x += Math.cos(Math.toRadians(this.direction)) * speed;
		this.y += Math.sin(Math.toRadians(this.direction)) * speed;
		forward();
		turn(isTurnLeft);
	}

	public void setTurnLeft(boolean isTurnLeft) {
		this.isTurnLeft = isTurnLeft;
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

}
