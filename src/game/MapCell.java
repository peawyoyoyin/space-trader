package game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class MapCell {
	private List<Entity> entities;
	
	public MapCell() {
		this.entities = new ArrayList<Entity>();
	}
	
	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public void update(GraphicsContext gc) {
		for (Entity entity : entities) {
			entity.update();
			if(entity instanceof Renderable) {
				((Renderable) entity).render(gc);
			}
		}
	}
}
