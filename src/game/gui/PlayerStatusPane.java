package game.gui;

import game.logic.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerStatusPane extends BorderPane{
	private PlayerStatus playerStatus;
	
	public class PlayerStatus extends VBox {
		public PlayerStatus() {
			super();
			PlayerHealthBar healthBar = new PlayerHealthBar();
			HBox hpLabels = new HBox();
			hpLabels.getChildren().add(new Label("HP"));
			hpLabels.setSpacing(15);
			Label hpLabel = new Label();
			hpLabel.textProperty().bind(Player.instance.getPlayerShip().getShipProperty("hp").asString());
			Label maxhpLabel = new Label();
			maxhpLabel.textProperty().bind(Player.instance.getPlayerShip().getShipProperty("maxHp").asString());
			hpLabels.getChildren().addAll(hpLabel,new Label("/"),maxhpLabel);
			this.getChildren().addAll(hpLabels,healthBar);
			
			HBox shipDamageLabels = new HBox();
			shipDamageLabels.setPadding(new Insets(15,0,0,5));
			Label shipDamageLabel = new Label();
			shipDamageLabels.getChildren().addAll(new Label("Ship Weapon Damage : "), shipDamageLabel);
			
			HBox shipSpeedLabels = new HBox();
			shipSpeedLabels.setPadding(new Insets(15,0,0,5));
			Label shipSpeedLabel = new Label();
			shipSpeedLabels.getChildren().addAll(new Label("Ship Speed : "),shipSpeedLabel);
			
			HBox moneyLabels = new HBox();
			Label moneyLabel = new Label("1245645");
			moneyLabel.textProperty().bind(Player.instance.getMoneyProperty().asString());
			moneyLabel.setTextFill(Color.SANDYBROWN);
			
			moneyLabels.setPadding(new Insets(15,0,0,5));
			moneyLabels.getChildren().addAll(new Label("Money :  "),moneyLabel);
			this.getChildren().addAll(shipDamageLabels,shipSpeedLabels,moneyLabels);
		}
	}
	
	public PlayerStatusPane() {
		super();
		this.setStyle("-fx-background-color: gray;");
		this.playerStatus = new PlayerStatus();
		this.setCenter(playerStatus);
	}
	
	
	public void update() {
		
	}
}

class PlayerHealthBar extends Canvas {
	private IntegerProperty hp;
	private IntegerProperty maxHp;
	
	private static final int WIDTH = 250;
	private static final int HEIGHT = 10;
	
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
		//background
		gc.setFill(Color.GREEN);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		double percentage = (double) this.hp.get()/this.maxHp.get();
		gc.setFill(Color.LIMEGREEN);
		gc.fillRect(0, 0, WIDTH*percentage, HEIGHT);
	}
}