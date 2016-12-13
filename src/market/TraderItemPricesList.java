package market;

import constants.ConfigConstant;
import game.logic.Player;
import game.model.Item.ItemType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TraderItemPricesList extends VBox {
	
	class TraderItemPriceCell extends HBox{
		public TraderItemPriceCell(ItemType type, Trader trader) {
			super();
			this.setAlignment(Pos.CENTER);
			this.setSpacing(5);
			this.setPrefWidth(200);
			
			//draw item icon based on item type
			Canvas itemIcon = new Canvas(40,40);
			itemIcon.getGraphicsContext2D().drawImage(ItemType.getItemIcon(type), 0, 0, 40, 40);
			
			Label price = new Label(Integer.toString((int) (Market.getItemPrice(type)*trader.getSellPriceMultipliers().get(type))));
			price.setFont(ConfigConstant.Resource.HUD_FONT);
			this.getChildren().addAll(itemIcon,price);
		}
	}
	
	/**
	 * TraderItemPricesList is used to sell items (from player to trader)
	 * it contains multiple TraderItemPriceCells, one for each ItemType
	 * 
	 * @param trader the trader the player is selling to.
	 */
	public TraderItemPricesList(Trader trader) {
		this.setMaxHeight(400);
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(5);
		Label top = new Label("Sell");
		top.setFont(ConfigConstant.Resource.HUD_FONT);
		this.getChildren().add(top);
		
		for(ItemType type : ItemType.values()) {
			TraderItemPriceCell priceCell = new TraderItemPriceCell(type, trader);
			priceCell.setOnMouseClicked(event -> {
				System.out.println("sell item " + type.toString());
				if(Player.instance.getInventory().get(type) > 0) {
					Player.instance.removeItemFromInventory(type);
					Player.instance.addMoney(trader.playerSellItem(type));
				} else {
					System.out.println("no item to sell");
				}
			});
			this.getChildren().add(priceCell);
		}
		
		VBox space = new VBox();
		this.getChildren().add(space);
		VBox.setVgrow(space, Priority.ALWAYS);
		this.setPadding(new Insets(0,0,50,0));
		Button upgrade = new Button("Upgrade");
		this.getChildren().add(upgrade);
		
		upgrade.setOnAction(e -> {
			TraderScreen.togglePane();
		});
	}
	
}
