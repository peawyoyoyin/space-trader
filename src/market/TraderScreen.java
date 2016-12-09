package market;

import java.util.ArrayList;
import java.util.List;

import constants.ConfigConstant;
import game.Item;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TraderScreen extends BorderPane{
	private Trader trader;
	private List<TraderScreenItemCell> itemCells;
	private VBox itemsOnSale;
	
	public class TraderScreenTop extends BorderPane {
		public TraderScreenTop(Trader trader) {
			super();
			Label name = new Label(trader.getName());
			BorderPane.setAlignment(name, Pos.CENTER);
			this.setCenter(name);
		}
	}
	
	public class TraderScreenItemCell extends HBox {
		Item item;
		class ItemCellIcon extends Canvas {
			public ItemCellIcon(Item item) {
				super(40,40);
				GraphicsContext gc = this.getGraphicsContext2D();
				Image icon = Item.ItemType.getItemIcon(item.getItemType());
				gc.drawImage(icon, 0, 0, 40, 40);
			}
		}
		
		private ItemCellIcon itemCellIcon;
		private Label priceLabel;
		public TraderScreenItemCell(Item item, Trader trader) {
			super();
			this.setPrefHeight(50);
			this.setStyle("-fx-background-color: gray");
			this.item = item;
			this.setAlignment(Pos.CENTER);
			this.setSpacing(5);
			this.itemCellIcon = new ItemCellIcon(item);
			this.priceLabel = new Label("Price: "+Integer.toString((int) (trader.getBuyPriceMultipliers().get(item.getItemType())*Market.getItemPrice(item.getItemType()))));
			
			this.getChildren().addAll(itemCellIcon, priceLabel);
		}
	}
	
	public TraderScreen(Trader trader) {
		super();
		this.itemCells = new ArrayList<>();
		this.trader = trader;
		
		this.itemsOnSale = new VBox();
		
		updateItemList();
		
		this.setCenter(itemsOnSale);
		
		TraderScreenTop top = new TraderScreenTop(trader);
		this.setTop(top);
		
		Canvas canvas = new Canvas(ConfigConstant.gameScreenWidth,100);
		canvas.getGraphicsContext2D().drawImage(ConfigConstant.Resource.TRADER_FACE, 0, 0, ConfigConstant.gameScreenWidth, 100);
		
		top.setTop(canvas);
	}
	
	public void updateItemList() {
		for (Item item : trader.getItemsOnSale()) {
			TraderScreenItemCell itemCell = new TraderScreenItemCell(item, trader);
			itemCell.setOnMouseClicked(event -> {
				System.out.println(itemCell.item);
			});
			this.itemCells.add(itemCell);
			itemsOnSale.getChildren().add(itemCell);
		}
	}

}