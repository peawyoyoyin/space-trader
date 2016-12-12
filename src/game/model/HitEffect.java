package game.model;

import game.logic.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HitEffect extends Entity implements Renderable {

	private int counter;
	private int maxCounter;
	private double maxRadius;
	private double currentRadius;

	public HitEffect(double x, double y, double maxRadius, int maxCounter) {
		this.x = x;
		this.y = y;
		this.z = 3;
		this.maxRadius = maxRadius;
		this.maxCounter = maxCounter;
		this.currentRadius = 0;
		this.counter = 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.counter++;
		if (this.counter > this.maxCounter) {
			this.destroyed = true;
		}
		this.currentRadius = this.currentRadius + this.maxRadius / this.maxCounter;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.setStroke(Color.DARKGRAY);
		double i = 0;
		for (int j = 0; j < currentRadius; j++) {
			gc.setGlobalAlpha(i);
			gc.strokeOval(-j/2, -j/2, j, j);
			i = i + 0.01;
		}
		gc.setGlobalAlpha(1);
		gc.translate(-x, -y);
	}

}
