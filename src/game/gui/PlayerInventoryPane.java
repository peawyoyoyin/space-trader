package game.gui;

import constants.ConfigConstant;
import game.logic.Player;
import game.model.Item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class PlayerInventoryPane extends HBox{
	public static PlayerInventoryPane instance = new PlayerInventoryPane();
	public PlayerInventoryPane() {
		this.setSpacing(5);
		this.setAlignment(Pos.CENTER);
		for (ItemType type : ItemType.values()) {
			this.getChildren().add(new PlayerInventoryCell(type));
		}
	}
}

class PlayerInventoryCell extends VBox {
	public PlayerInventoryCell(ItemType type) {
		super();
		Label countLabel = new Label();
		countLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		countLabel.textProperty().bind(Player.instance.getInventoryProperties().get(type).asString());
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
		Canvas itemIcon = new Canvas(40,40);
		itemIcon.getGraphicsContext2D().drawImage(ItemType.getItemIcon(type), 0, 0, 40, 40);
		this.getChildren().addAll(itemIcon, countLabel);
	}
}