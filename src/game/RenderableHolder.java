package game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class RenderableHolder {
	public static RenderableHolder instance = new RenderableHolder();

	private List<Entity> entities;
	public static Image background;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<Entity>();
	}

	public static void loadResource() {
		RenderableHolder.background = new Image(ClassLoader.getSystemResource("Map.jpg").toString());
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}
}
