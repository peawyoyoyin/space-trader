package startScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import constants.ConfigConstant;
import game.logic.Renderable;
import game.model.DemoEnemy;
import game.model.Entity;
import game.model.SpaceStationEntity;
import game.model.TailShipEffect;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class BackgroundStartScreen extends Canvas {
	private Image background = ConfigConstant.Resource.MAP_BACKGROUND;
	private GraphicsContext gc = this.getGraphicsContext2D();
	public static List<Entity> entities = new ArrayList<>();
	public static List<Entity> newEffect = new ArrayList<>();

	public BackgroundStartScreen() {
		super();
		// TODO Auto-generated constructor stub
		this.setWidth(ConfigConstant.startScreenWidth);
		this.setHeight(ConfigConstant.startScreenHeight);
		DemoEnemy en1 = new DemoEnemy(300, 100, 100, 100, 5, 5, 0.1, 2, 45);
		DemoEnemy en2 = new DemoEnemy(700, 300, 100, 100, 3, 3, 0.1, 3, 0);
		DemoEnemy en3 = new DemoEnemy(1000, 500, 100, 100, 1, 7, 0.1, 1, 225);
		SpaceStationEntity sp = new SpaceStationEntity(ConfigConstant.startScreenWidth - 100,
				ConfigConstant.startScreenHeight - 100);
		entities.add(en1);
		entities.add(en2);
		entities.add(en3);
		entities.add(sp);
		AnimationTimer animator = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < entities.size(); i++) {
					if (entities.get(i).isDestroyed()) {
						entities.remove(i);
						i--;
					}
				}
				drawBackground();
				Collections.sort(entities);
				for (Entity entity : entities) {
					entity.update();
					if (entity instanceof Renderable) {
						((Renderable) entity).render(gc);
					}
				}
				entities.addAll(newEffect);
				newEffect.clear();
			}
		};
		animator.start();
	}

	public void drawBackground() {
		ImagePattern backgroundRepeated = new ImagePattern(background, 0, 0,
				background.getWidth() / ConfigConstant.startScreenWidth,
				background.getHeight() / ConfigConstant.startScreenHeight, true);
		gc.setFill(backgroundRepeated);
		gc.fillRect(0, 0, ConfigConstant.startScreenWidth, ConfigConstant.startScreenHeight);
	}

	public static void addEffect(TailShipEffect tailShipEffect) {
		// TODO Auto-generated method stub
		newEffect.add(tailShipEffect);
	}

	public void reBackground() {
		entities.clear();
		DemoEnemy en1 = new DemoEnemy(300, 100, 100, 100, 5, 5, 0.1, 2, 45);
		DemoEnemy en2 = new DemoEnemy(700, 300, 100, 100, 3, 3, 0.1, 3, 0);
		DemoEnemy en3 = new DemoEnemy(1000, 500, 100, 100, 1, 7, 0.1, 1, 225);
		SpaceStationEntity sp = new SpaceStationEntity(ConfigConstant.startScreenWidth - 100,
				ConfigConstant.startScreenHeight - 100);
		entities.add(en1);
		entities.add(en2);
		entities.add(en3);
		entities.add(sp);
	}

}
