package startScreen;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BackgroundStartScreen extends Canvas {

	public BackgroundStartScreen() {
		super();
		// TODO Auto-generated constructor stub
		this.setWidth(1120);
		this.setHeight(630);
		Image bg = new Image("res/startScreen/bg.png");
		getGraphicsContext2D().setFill(Color.GREEN);
		getGraphicsContext2D().fillRect(0, 0, 1120, 630);
		this.getGraphicsContext2D().drawImage(bg, 0, 0);

		AnimationTimer animator = new AnimationTimer() {
			int x = 0;

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				getGraphicsContext2D().setFill(Color.RED);
				getGraphicsContext2D().fillRect(0, x, 1120, 1);
				x++;
				if (x > 60) {
					this.stop();
				}
			}
		};
		animator.start();
	}
	
	public void drawBackground() {
		getGraphicsContext2D().setFill(Color.LIGHTGREY);
		getGraphicsContext2D().fillRect(0, 0, 1120, 630);
	}

}
