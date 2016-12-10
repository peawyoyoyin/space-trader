package game.gui;

import game.logic.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerStatusPane extends BorderPane{
	public static final Canvas SHIPVIEW_HEALTHY = new Canvas(100,100);
	static {
//		SHIPVIEW_HEALTHY.getGraphicsContext2D().drawImage(null, 0, 0, 100, 100);
		SHIPVIEW_HEALTHY.getGraphicsContext2D().setFill(Color.RED);
		SHIPVIEW_HEALTHY.getGraphicsContext2D().fillRect(0, 0, 100, 100);
	}
	private Canvas shipView;
	private PlayerStatus playerStatus;
	
	public class PlayerStatus extends VBox {
		public PlayerStatus() {
			super();
			HBox hpLabels = new HBox();
			hpLabels.getChildren().add(new Label("HP/MAXHP"));
			hpLabels.setSpacing(15);
			Label hpLabel = new Label();
			hpLabel.textProperty().bind(Player.instance.getPlayerShip().getShipProperty("hp").asString());
			Label maxhpLabel = new Label();
			maxhpLabel.textProperty().bind(Player.instance.getPlayerShip().getShipProperty("maxHp").asString());
			hpLabels.getChildren().addAll(hpLabel,maxhpLabel);
			this.getChildren().addAll(hpLabels);
		}
	}
	
	public PlayerStatusPane() {
		super();
		this.shipView = new Canvas();
		this.setLeft(shipView);
		this.playerStatus = new PlayerStatus();
		this.setCenter(playerStatus);
	}
	
	public void setShipView(Canvas shipView) {
		this.shipView = shipView;
		this.setLeft(shipView);
	}
	
	public void update() {
		
	}
}