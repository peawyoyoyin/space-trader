package game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import constants.ConfigConstant;
import game.gui.GameOverPane;
import game.gui.GameScreen;
import game.model.BombEffect;
import game.model.EnemyShip;
import game.model.Entity;
import game.model.Item;
import game.model.PlayerShip;
import game.model.Ship;
import game.model.SpaceStationEntity;
import highscore.HighScore;
import input.Input;
import input.KeyCodeConstants;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import market.TraderScreen;

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

	public void addNewEffect(Entity effect) {
		this.newEffect.add(effect);
	}

	public void clear() {
		for (int i = 0; i < entities.size(); i++) {
			if (!(entities.get(i) instanceof SpaceStationEntity)) {
				entities.remove(i);
				i--;
			}
		}
	}

	public void update(GraphicsContext gc) {

		RenderableHolder.instance.render(gc, this);

		if (!Player.instance.isPause()) {
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).update();
			}
		}

		if (Input.isKeyPressed(KeyCodeConstants.KEY_ENTER)) {
			for (Entity entity : entities) {
				if (entity instanceof SpaceStationEntity) {
					if (Player.instance.getPlayerShip().isCollideWith(entity)) {
						if (!Player.instance.isPause()) {
							Player.instance.pause();
							((SpaceStationEntity) entity).getTrader().setAccessing(true);
							GameScreen.instance.changeCenter(new TraderScreen(((SpaceStationEntity) entity).getTrader()));
						} else {
							Node target = null;
							for (Node node : ((Pane) GameScreen.instance.getCenter()).getChildren()) {
								if(node instanceof TraderScreen){
									target = node;
								}
							}
							((Pane) GameScreen.instance.getCenter()).getChildren().remove(target);
							Player.instance.resume();
							((SpaceStationEntity) entity).getTrader().setAccessing(false);
						}
					}
				}
			}
		}
		
		this.entities.addAll(newEffect);
		this.newEffect.clear();

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isDestroyed()) {
				if (entities.get(i) instanceof Ship) {
					this.entities.add(new BombEffect(entities.get(i).getX(), entities.get(i).getY()));
					ConfigConstant.Resource.BOOM_SOUND.play(ConfigConstant.volumeSFX);
				}
				if (entities.get(i) instanceof EnemyShip){
					Player.instance.addMoney(new Random().nextInt(50)+25);
					this.entities.add(Item.generateEnitity(entities.get(i).getX(), entities.get(i).getY()));
				}
				if (entities.get(i) instanceof PlayerShip){
					GameScreen.instance.changeCenter(new GameOverPane());
					
				}
				entities.remove(i);
				i--;
			}
		}
		
		gc.setFill(Color.BROWN);
		gc.fillText(Player.instance.getSectionX() + " - " + Player.instance.getSectionY(), 5, 20);

	}

}
