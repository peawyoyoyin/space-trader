package game.gui;

import constants.ConfigConstant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlayerInfoPane extends BorderPane {

	public static PlayerInfoPane instance = new PlayerInfoPane();

	private PlayerStatusPane playerStatusPane;

	public PlayerInfoPane() {
		super();
		this.setPadding(new Insets(20, 0, 20, 0));
		this.setPrefSize(ConfigConstant.gameScreen_left_width, ConfigConstant.gameScreenHeight);
		this.playerStatusPane = new PlayerStatusPane();
		this.setTop(MinimapPane.instance);
		BorderPane.setAlignment(MinimapPane.instance, Pos.CENTER);
		this.setCenter(this.playerStatusPane);
		VBox inventory = new VBox(5);
		inventory.setAlignment(Pos.CENTER);
		Label inventoryLabel = new Label("Inventory");
		inventoryLabel.setFont(ConfigConstant.Resource.HUD_FONT);
		inventory.getChildren().addAll(inventoryLabel, PlayerInventoryPane.instance);
		this.setBottom(inventory);
	}
}