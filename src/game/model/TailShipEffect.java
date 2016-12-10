package game.model;

import java.util.Random;

import game.logic.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TailShipEffect extends Entity implements Renderable {

	private double radius;
	private int counter;
	private int maxCounter;
	private int direction;
	private double speed;

	public TailShipEffect(double x, double y, double radius ,int direction) {
		Random rand = new Random();
		this.x = x + rand.nextInt(10) - 5;
		this.y = y + rand.nextInt(10) - 5;
		this.direction = direction;
		this.radius = radius;
		this.counter = 0;
		this.maxCounter = 50;
		this.speed = 1;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.setFill(Color.rgb((int) (200 - (double) this.counter / this.maxCounter * 150), 0, 0));
		gc.fillOval(-radius / 2, -radius / 2, radius, radius);
		gc.translate(-x, -y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.x -= Math.cos(Math.toRadians(this.direction)) * speed;
		this.y -= Math.sin(Math.toRadians(this.direction)) * speed;
		this.radius = this.radius - ((double) 50/this.maxCounter);
		this.counter++;
		if (this.counter > this.maxCounter) {
			this.destroyed = true;
		}
		this.z = -100 + this.counter;
	}

}
