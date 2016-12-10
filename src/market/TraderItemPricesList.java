package market;

import game.logic.Item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TraderItemPricesList extends VBox {
	
	class TraderItemPriceCell extends HBox{
		public TraderItemPriceCell(ItemType type, Trader trader) {
			super();
			this.setAlignment(Pos.CENTER);
			this.setSpacing(5);
			this.setPrefWidth(200);
			
			Canvas itemIcon = new Canvas(40,40);
			itemIcon.getGraphicsContext2D().drawImage(ItemType.getItemIcon(type), 0, 0, 40, 40);
			
			Label price = new Label(Integer.toString((int) (Market.getItemPrice(type)*trader.getBuyPriceMultipliers().get(type))));
			this.getChildren().addAll(itemIcon,price);
		}
	}
	
	public TraderItemPricesList(Trader trader) {
		this.setMaxHeight(400);
		this.setStyle("-fx-background-color: red;");
		Label top = new Label("Current Sell Prices");
		this.getChildren().add(top);
		for(ItemType type : ItemType.values()) {
			this.getChildren().add(new TraderItemPriceCell(type,trader));
		}
	}
}
