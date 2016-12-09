package game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapCell {
	private List<Entity> entities;
	private List<Entity> newEffect;

	public MapCell() {
		this.entities = new ArrayList<Entity>();
		this.newEffect = new ArrayList<>();
	}

	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public void addNewEffect(Entity effect){
		this.newEffect.add(effect);
	}
	
	public void clear(){
		for (int i = 0; i < entities.size(); i++) {
			if (!(entities.get(i) instanceof Ship)) {
				entities.remove(i);
				i--;
			}
		}
	}

	public void update(GraphicsContext gc) {
		
		RenderableHolder.instance.render(gc, this);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		
		this.entities.addAll(newEffect);
		this.newEffect.clear();
		
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
				i--;
			}
		}
		
		gc.setFill(Color.BROWN);
		gc.fillText(Player.instance.getSectionX() + " - "
				+ Player.instance.getSectionY(), 5, 20);

	}

}
