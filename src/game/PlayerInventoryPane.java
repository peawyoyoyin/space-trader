package game;

import java.util.HashMap;
import java.util.Map;

import game.Item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerInventoryPane extends VBox{
	private Map<ItemType, Label> itemCountLabels;
	public PlayerInventoryPane() {
		super();
		this.itemCountLabels = new HashMap<>();
		for (ItemType type : ItemType.values()) {
			this.itemCountLabels.put(type, new Label());
			this.getChildren().add(new PlayerInventoryCell(type, this.itemCountLabels.get(type)));
		}
		update();
	}
	public void update() {
		for (ItemType type : ItemType.values()) {
			Label typeLabel = this.itemCountLabels.get(type);
			typeLabel.setText(Integer.toString(Player.instance.getInventory().get(type)));
		}
	}
}

class PlayerInventoryCell extends HBox {
	public PlayerInventoryCell(ItemType type, Label countLabel) {
		super();
		this.setAlignment(Pos.CENTER_LEFT);
		this.setSpacing(15);
		Canvas itemIcon = new Canvas(40,40);
		itemIcon.getGraphicsContext2D().drawImage(ItemType.getItemIcon(type), 0, 0, 40, 40);
		
		this.getChildren().addAll(itemIcon, countLabel);
	}
}