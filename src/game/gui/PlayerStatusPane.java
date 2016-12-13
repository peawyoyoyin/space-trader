package game.gui;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import constants.ConfigConstant;
import game.logic.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerStatusPane extends BorderPane {
	private PlayerStatus playerStatus;

	public class PlayerStatus extends VBox {
		public PlayerStatus() {
			super();
			this.setPadding(new Insets(10));
			PlayerHealthBar healthBar = new PlayerHealthBar();
			this.getChildren().add(healthBar);

			HBox shipDamageLabels = new HBox();
			shipDamageLabels.setPadding(new Insets(15, 0, 0, 5));
			Label shipDamageLabel = new Label();
			shipDamageLabel.setFont(ConfigConstant.Resource.HUD_FONT);
			shipDamageLabel.textProperty().bind(Player.instance.getBulletDamageProperty().asString());
			Label text1 = new Label("Ship Damage : ");
			text1.setFont(ConfigConstant.Resource.HUD_FONT);
			shipDamageLabels.getChildren().addAll(text1, shipDamageLabel);

			HBox shipSpeedLabels = new HBox();
			shipSpeedLabels.setPadding(new Insets(15, 0, 0, 5));
			Label shipSpeedLabel = new Label();
			shipSpeedLabel.setFont(ConfigConstant.Resource.HUD_FONT);
			shipSpeedLabel.textProperty().bind(Player.instance.getPlayerShip().getSpeedProperty().asString());
			Label text2 = new Label("Ship Speed : ");
			text2.setFont(ConfigConstant.Resource.HUD_FONT);
			shipSpeedLabels.getChildren().addAll(text2, shipSpeedLabel);

			HBox moneyLabels = new HBox();
			Label moneyLabel = new Label("1245645");
			moneyLabel.setFont(ConfigConstant.Resource.HUD_FONT);
			moneyLabel.textProperty().bind(Player.instance.getMoneyProperty().asString());
			moneyLabel.setTextFill(Color.SANDYBROWN);

			moneyLabels.setPadding(new Insets(15, 0, 0, 5));
			Label text3 = new Label("Money :  ");
			text3.setFont(ConfigConstant.Resource.HUD_FONT);
			moneyLabels.getChildren().addAll(text3, moneyLabel);
			this.getChildren().addAll(shipDamageLabels, shipSpeedLabels, moneyLabels);
		}
	}

	public PlayerStatusPane() {
		super();
		this.playerStatus = new PlayerStatus();
		this.setCenter(playerStatus);
	}
}

class PlayerHealthBar extends Canvas {
	private IntegerProperty hp;
	private IntegerProperty maxHp;

	private static final int WIDTH = 225;
	private static final int HEIGHT = 30;

	PlayerHealthBar() {
		super(WIDTH, HEIGHT);

		this.hp = new SimpleIntegerProperty();
		this.hp.bind(Player.instance.getPlayerShip().getShipProperty("éÂéÂhp"));
		this.maxHp = new SimpleIntegerProperty();
		this.maxHp.bind(Player.instance.getPlayerShip().getShipProperty("maxHp"));
		this.hp.addListener(event -> {
			updateHealthBar();
		});
		this.maxHp.addListener(event -> {
			updateHealthBar();
		});
		updateHealthBar();
	}

	private void updateHealthBar() {
		GraphicsContext gc = this.getGraphicsContext2D();
		// background
		gc.drawImage(ConfigConstant.Resource.HPBAR_BACK, 0, 0, WIDTH, HEIGHT);
		double percentage = (double) this.hp.get() / this.maxHp.get();
		WritableImage hpbar = new WritableImage(ConfigConstant.Resource.HPBAR_FRONT.getPixelReader(), 0, 0,
				(int) (percentage > 0 ? ConfigConstant.Resource.HPBAR_FRONT.getWidth() * percentage : 1),
				(int) ConfigConstant.Resource.HPBAR_FRONT.getHeight());
		gc.drawImage(hpbar, 0, 0, WIDTH * percentage, HEIGHT);
		gc.setFont(ConfigConstant.Resource.HUD_FONT);
		String hp = this.hp.get() + "/" + this.maxHp.get();
		FontLoader fl = Toolkit.getToolkit().getFontLoader();
		double hpTextWidth = fl.computeStringWidth(hp, ConfigConstant.Resource.HUD_FONT);
		double hpTextHeight = fl.getFontMetrics(ConfigConstant.Resource.HUD_FONT).getLineHeight();
		gc.fillText(hp, (WIDTH - hpTextWidth) / 2, HEIGHT - (hpTextHeight / 2));
	}
}