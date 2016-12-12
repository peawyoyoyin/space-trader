package game.model;

import constants.ConfigConstant;
import game.logic.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class BombEffect extends Entity implements Renderable {

	private int counter;
	private int maxCounter;
	private int currentFrame;
	private Image image;
	private Image frame;

	public BombEffect(double x, double y) {
			this.x = x;
			this.y = y;
			this.z = 3;
			this.maxCounter = 36;
			this.currentFrame = 0;
			this.counter = 0;
			this.image = ConfigConstant.Resource.BOOM_IMAGE;
			this.frame = new WritableImage(image.getPixelReader(),0,0,94,94);
		}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.counter++;
		if (this.counter > this.maxCounter) {
			this.destroyed = true;
		}
		if (this.counter % 6 == 0) {
			this.currentFrame++;
		}
		this.frame = new WritableImage(image.getPixelReader(),currentFrame*95,0,94,94);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.translate(x, y);
		gc.drawImage(frame, -this.frame.getWidth() / 2, -this.frame.getHeight() / 2);
		gc.translate(-x, -y);
	}

}