package market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import game.model.Item.ItemType;
import gamedata.PlayerStocksPortFolio;
import javafx.application.Platform;
import news.News;
import news.NewsPane;
import stocks.Stock;
import stocks.StockCell;
import stocks.StockHolder;
import stocks.StockPriceController;
import stocks.StocksScreen;

public class Market {

	private static Thread marketRunner;

	private static final double RANDOM_NEWS_CHANCE = 2.00;
	private static final double TRADER_CHANGE_CHANCE = 1.00;

	private static List<StockUpdater> stockUpdaters = new ArrayList<StockUpdater>();
	private static Map<ItemType, Integer> itemPrices = new HashMap<ItemType, Integer>();

	public static void InitializeMarket() {
		if(marketRunner != null) {
			marketRunner.interrupt();
		}
		NewsPane.instance.clearFeed();
		StockHolder.instance.getStocks().clear();
		Stock.initializeGameStocks();
		PlayerStocksPortFolio.instance = new PlayerStocksPortFolio();
		
		int d = 1000;
		
		if(!stockUpdaters.isEmpty()) {
			for (StockUpdater stockUpdater : stockUpdaters) {
				stockUpdater.interrupt();
			}
			stockUpdaters.clear();
		}
		
		stockUpdaters = new ArrayList<StockUpdater>();

		StocksScreen.instance.getStocksList().getChildren().clear();
		for (Stock stock : StockHolder.instance.getStocks()) {
			StocksScreen.instance.getStocksList().addStockCell(new StockCell(stock));
			stockUpdaters.add(new StockUpdater(new StockPriceController(stock, 2), d));
			d -= 200;
		}

		StocksScreen.instance.getStockGraph().setStock(StockHolder.instance.getStocks().get(0));
		StocksScreen.instance.getStockTradePanel().setStock(StockHolder.instance.getStocks().get(0));
		
		for (StockUpdater stockUpdater : stockUpdaters) {
			stockUpdater.start();
		}
		
		itemPrices = new HashMap<ItemType, Integer>();
		for (ItemType itemType : ItemType.values()) {
			itemPrices.put(itemType, 50);
		}

		Market.marketRunner = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (true) {
						Thread.sleep(100);
						MarketUpdate();
					}
				} catch (InterruptedException e) {
					System.out.println("marketRunner Interrupted");
				}
			}
		});

		Market.marketRunner.start();

		System.out.println("Market initialized");
	}

	public static int getItemPrice(ItemType itemType) {
		return itemPrices.get(itemType);
	}

	public static void MarketUpdate() {
		Random random = new Random();
		double chance = random.nextDouble() * 100;

		double roll = RANDOM_NEWS_CHANCE;
		if (chance < roll) {
			generateRandomNews();
		} else {
			roll += TRADER_CHANGE_CHANCE;
			if (chance < roll) {
				generateTraderChange();
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
		System.out.println("RandomNews event");
	}

	private static void generateTraderChange() {
		System.out.println("TraderChange event");
		ItemType type = ItemType.getRandomItemType();
		Trader trader = Trader.getRandomTrader();
		
		if(!trader.isAccessing()) {
			Random random = new Random();
			boolean increase = random.nextBoolean();
			boolean changeBuyPrice = random.nextBoolean();
			
			News news = new News("Market");
			if(changeBuyPrice) {
				double oldMultiplier = trader.getBuyPriceMultipliers().get(type);
				if(increase) {
					news.setContent(trader.getName() + " sells " + type.toString() + " more expensive.");
					double change = (random.nextDouble()*0.7)+0.1;
					double newMultiplier = (oldMultiplier + change);
					if(newMultiplier > Trader.getMaxBuyPriceMultiplier(type)) {
						newMultiplier = Trader.getMaxBuyPriceMultiplier(type);
					}
					trader.getBuyPriceMultipliers().put(type, newMultiplier);
				} else {
					news.setContent(trader.getName() + " now sells " + type.toString() + " items at a discount price!");
					double change = (random.nextDouble()*0.7)+0.1;
					double newMultiplier = (oldMultiplier - change);
					if(newMultiplier < Trader.getMinBuyPriceMultiplier(type)) {
						newMultiplier = Trader.getMinBuyPriceMultiplier(type);
					}
					trader.getBuyPriceMultipliers().put(type, newMultiplier);
				}
			} else {
				double oldMultiplier = trader.getSellPriceMultipliers().get(type);
				if(increase) {
					news.setContent("Trader "+trader.getName() + " now accepts " + type.toString() + " at better price!.");
					double change = (random.nextDouble()*0.7)+0.1;
					double newMultiplier = (oldMultiplier + change);
					if(newMultiplier >= Trader.getMaxSellPriceMultiplier(type)) {
						newMultiplier = Trader.getMaxSellPriceMultiplier(type);
					}
					trader.getSellPriceMultipliers().put(type, newMultiplier);
				} else {
					news.setContent("Trader "+trader.getName() + " now accepts " + type.toString() + " at reduced price.");
					double change = (random.nextDouble()*0.7)+0.1;
					double newMultiplier = oldMultiplier - change;
					if(newMultiplier < Trader.getMinSellPriceMultiplier(type)) {
						newMultiplier = Trader.getMinSellPriceMultiplier(type);
					}
					trader.getSellPriceMultipliers().put(type, newMultiplier);
				}
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					NewsPane.instance.addNews(news);
				}
			});
		}
		
	}

	public static void finalizeMarket() {
		for (StockUpdater stockUpdater : stockUpdaters) {
			stockUpdater.interrupt();
		}
		Market.marketRunner.interrupt();
	}
}
