package startScreen;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class BackgroundStartScreen extends Canvas {

	public BackgroundStartScreen() {
		super();
		// TODO Auto-generated constructor stub
		this.setWidth(1120);
		this.setHeight(630);
		getGraphicsContext2D().setFill(Color.LIGHTGREY);
		getGraphicsContext2D().fillRect(0, 0, 1120, 630);

		AnimationTimer animater = new AnimationTimer() {
			int x = 0;

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				getGraphicsContext2D().setFill(Color.RED);
				getGraphicsContext2D().fillRect(0, x, 1120, 1);
				x++;
				if (x > 630) {
					this.stop();
				}
			}
		};
		animater.start();
	}

	public void drawBackground() {
		getGraphicsContext2D().setFill(Color.LIGHTGREY);
		getGraphicsContext2D().fillRect(0, 0, 1120, 630);
	}

}
