package game;

import com.sun.javafx.geom.Shape;

import constants.ConfigConstant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet extends Entity implements Renderable {

	private double speed;
	private int direction;
	private int damage;
	private Image body;
	private Ship shooter;

	public Ship getShooter() {
		return shooter;
	}
	public int getDamage() {
		return damage;
	}


	public Bullet(double x, double y, double speed, int direction, int damage,Ship shooter) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;
		this.damage = damage;
		//this.body = new Image("res/startScreen/bg.png");
		this.shooter = shooter;
		this.radius = 5;
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		this.x += Math.cos(Math.toRadians(this.direction)) * speed;
		this.y += Math.sin(Math.toRadians(this.direction)) * speed;
		if(x>ConfigConstant.gameScreenWidth || x<0||y>ConfigConstant.gameScreenHeight || y<0){
			this.destroyed = true;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.rotate(this.direction+90);
		gc.setFill(Color.BLUE);
		gc.fillRect( -5, -5, 10, 10);
		gc.rotate(-this.direction-90);
		gc.translate(-x, -y);
	}

}
