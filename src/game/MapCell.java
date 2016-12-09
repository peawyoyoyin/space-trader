package game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapCell {
	private List<Entity> entities;

	public MapCell() {
		this.entities = new ArrayList<Entity>();
	}

	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public void clearBullet(){
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Bullet) {
				entities.remove(i);
				i--;
			}
		}
	}

	public void update(GraphicsContext gc) {

		for (int i = 0; i < entities.size(); i++) {
			
			if (entities.get(i) instanceof Bullet) {
				for (Entity entity2 : entities) {
					if (entity2 instanceof Ship && entity2 != ((Bullet) entities.get(i)).getShooter()
							&& entities.get(i).isCollideWith(entity2)) {
						entities.get(i).destroyed = true;
						((Ship) entity2).hit(((Bullet) entities.get(i)).getDamage());
					}

				}
			}
			entities.get(i).update();
		}
		
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Renderable) {
				((Renderable) entities.get(i)).render(gc);
			}
		}
		
		gc.setFill(Color.BROWN);
		gc.fillText(Player.instance.getSectionX() + " - "
				+ Player.instance.getSectionY(), 5, 20);

	}

}
