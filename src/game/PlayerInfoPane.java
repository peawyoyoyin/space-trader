package game;

import javafx.scene.layout.BorderPane;

public class PlayerInfoPane extends BorderPane {
	
	public static PlayerInfoPane instance = new PlayerInfoPane();
	
	private PlayerStatusPane playerStatusPane;
	private PlayerInventoryPane playerInventoryPane;
	
	private PlayerInfoPane() {
		super();
		this.playerStatusPane = new PlayerStatusPane();
		this.playerInventoryPane = new PlayerInventoryPane();
		this.setCenter(this.playerStatusPane);
		this.setBottom(this.playerInventoryPane);
		this.playerStatusPane.setShipView(PlayerStatusPane.SHIPVIEW_HEALTHY);
	}
	
	public void update() {
		this.playerStatusPane.update();
		this.playerInventoryPane.update();
	}
}