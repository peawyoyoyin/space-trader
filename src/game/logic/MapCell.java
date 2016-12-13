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
import input.Input;
import input.KeyCodeConstants;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import market.Market;
import market.TraderScreen;
import stocks.StocksScreen;

public class MapCell {
	private List<Entity> entities;
	private List<Entity> newEffect;
	private int enemyHp;
	private int enemyDmg;

	public MapCell() {
		this.entities = new ArrayList<Entity>();
		this.newEffect = new ArrayList<>();
		enemyHp = 50;
		enemyDmg = 3;
	}

	public void upgradeEnemyHp(int value) {
		enemyHp += value;
	}

	public void upgradeEnemyDmg(int value) {
		enemyDmg += value;
	}

	public int getEnemyHp() {
		return enemyHp;
	}

	public int getEnemyDmg() {
		return enemyDmg;
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

	public SpaceStationEntity getSpaceStation() {
		for (Entity entity : this.getEntities()) {
			if (entity instanceof SpaceStationEntity) {
				return (SpaceStationEntity) entity;
			}
		}
		return null;
	}

	public boolean hasSpaceStation() {
		for (Entity entity : this.getEntities()) {
			if (entity instanceof SpaceStationEntity) {
				return true;
			}
		}
		return false;
	}

	public boolean isPlayerColideWithSpacestation() {
		for (Entity entity : entities) {
			if (entity instanceof SpaceStationEntity && Player.instance.getPlayerShip().isCollideWith(entity)) {
				return true;
			}
		}
		return false;
	}

	public void update(GraphicsContext gc) {

		RenderableHolder.instance.render(gc, this);

		if (!Player.instance.isPause()) {
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).update();
			}
		}

		if (Input.isKeyPressed(KeyCodeConstants.KEY_ENTER)) {
			this.enterSpaceStation();
		}

		this.entities.addAll(newEffect);
		this.newEffect.clear();

		this.removeDestroyedEntities();

	}

	public void removeDestroyedEntities() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isDestroyed()) {
				if (entities.get(i) instanceof Ship) {
					this.entities.add(new BombEffect(entities.get(i).getX(), entities.get(i).getY()));
					ConfigConstant.Resource.BOOM_SOUND.play(ConfigConstant.volumeSFX);
				}
				if (entities.get(i) instanceof EnemyShip) {
					Player.instance.addMoney(new Random().nextInt(50) + 25);
					this.entities.add(Item.generateEnitity(entities.get(i).getX(), entities.get(i).getY()));
				}
				if (entities.get(i) instanceof PlayerShip) {
					GameScreen.instance.changeCenter(new GameOverPane());
					StocksScreen.instance.getStockTradePanel().setDisableTrade(true);
					Market.finalizeMarket();
				}
				entities.remove(i);
				i--;
			}
		}
	}

	public void spawnEnemy() {
		Random rd = new Random();
		for (int i = 0; i < rd.nextInt(3) + 2; i++) {
			Ship enemy = new EnemyShip(rd.nextDouble() * ConfigConstant.mapCellWidth,
					rd.nextDouble() * ConfigConstant.mapCellHeight, this.getEnemyHp(), this.getEnemyHp(), 0, 3, 0.1, 1,
					rd.nextInt(360));
			enemy.setBulletDamage(this.getEnemyDmg());
			this.getEntities().add(enemy);
		}
	}

	public void enterSpaceStation() {
		if (this.isPlayerColideWithSpacestation()) {
			if (!Player.instance.isPause()) {
				Player.instance.pause();
				this.getSpaceStation().getTrader().setAccessing(true);
				GameScreen.instance.changeCenter(new TraderScreen(this.getSpaceStation().getTrader()));
			} else {
				Node target = null;
				for (Node node : ((Pane) GameScreen.instance.getCenter()).getChildren()) {
					if (node instanceof TraderScreen) {
						target = node;
					}
				}
				((Pane) GameScreen.instance.getCenter()).getChildren().remove(target);
				Player.instance.resume();
				this.getSpaceStation().getTrader().setAccessing(false);
			}
		}
	}
}
