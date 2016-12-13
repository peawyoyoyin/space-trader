package game.model;

import constants.ConfigConstant;
import game.logic.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class HitEffect extends Entity implements Renderable {

	private int counter;
	private int maxCounter;
	private Image image;

	public HitEffect(double x, double y, int maxCounter, boolean isGreen) {
		this.x = x;
		this.y = y;
		this.z = 3;
		this.maxCounter = maxCounter;
		this.counter = 0;
		if (isGreen) {
			this.image = ConfigConstant.Resource.HIT_IMAGE;
		} else {
			this.image = ConfigConstant.Resource.HIT_ENEMY_IMAGE;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.counter++;
		if (this.counter > this.maxCounter) {
			this.destroyed = true;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.setStroke(Color.DARKGRAY);
		gc.drawImage(image, -image.getWidth()/4, -image.getHeight()/4,image.getWidth()/2,image.getHeight()/2);
		gc.setGlobalAlpha(1);
		gc.translate(-x, -y);
	}

}
