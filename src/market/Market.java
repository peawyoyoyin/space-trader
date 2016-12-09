package market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import game.Item.ItemType;
import javafx.application.Platform;
import news.News;
import news.NewsPane;
import stocks.Stock;
import stocks.StockHolder;
import stocks.StockPriceController;

public class Market {
	
	private static final double RANDOM_NEWS_CHANCE = 2.00;
	private static final double STOCK_EVENT_CHANCE = 5.00;
	private static final double TRADER_CHANGE_CHANCE = 5.00;
	
	
	private static List<StockUpdater> stockUpdaters = new ArrayList<StockUpdater>();
	private static Map<ItemType, Integer> itemPrices = new HashMap<ItemType, Integer>();
	
	public static void InitializeMarket() {
		int d = 1000;
		
		for (Stock stock : StockHolder.getInstance().getStocks()) {
			stockUpdaters.add(new StockUpdater(new StockPriceController(stock, 2),d));
			d -= 200;
		}
		
		for (StockUpdater stockUpdater : stockUpdaters) {
			stockUpdater.start();
		}
		
		for (ItemType itemType : ItemType.values()) {
			itemPrices.put(itemType, 50);
		}
	}
	
	public static int getItemPrice(ItemType itemType) {
		return itemPrices.get(itemType);
	}
	
	public static void MarketUpdate() {
		Random random = new Random();
		double chance = random.nextDouble()*100;
		
		double roll = RANDOM_NEWS_CHANCE;
		if(chance < roll) {
			generateRandomNews();
		} else {
			roll+= STOCK_EVENT_CHANCE;
			if(chance < roll) {
				generateStockEvent();
			} else {
				roll += TRADER_CHANGE_CHANCE;
				if(chance<roll) {
					generateTraderChange();
				}
			}
		}
	}
	
	public static List<StockUpdater> getStockUpdaters() {
		return stockUpdaters;
	}
	
	private static void generateRandomNews() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				NewsPane.instance.addNews(News.getRandomNews());
			}
		});

	}
	
	private static void generateStockEvent() {
		
	}
	
	private static void generateTraderChange() {
		
	}
	
	public static void finalizeMarket() {
		for (StockUpdater stockUpdater : stockUpdaters) {
			stockUpdater.interrupt();
		}
	}
}
