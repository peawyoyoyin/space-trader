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
	private static final double MAX_MULTIPLIER_CHANGE = 0.7;
	private static final double MIN_MULTIPLIER_CHANGE = 0.3;

	private static List<StockUpdater> stockUpdaters = new ArrayList<StockUpdater>();
	private static Map<ItemType, Integer> itemPrices = new HashMap<ItemType, Integer>();

	public static void InitializeMarket() {
		//clear old game data, in case the Player is restarting the game
		if(marketRunner != null) {
			marketRunner.interrupt();
		}
		
		NewsPane.instance.clearFeed();
		StockHolder.instance.getStocks().clear();
		Stock.initializeGameStocks();
		PlayerStocksPortFolio.instance = new PlayerStocksPortFolio();
		
		if(!stockUpdaters.isEmpty()) {
			for (StockUpdater stockUpdater : stockUpdaters) {
				stockUpdater.interrupt();
			}
			stockUpdaters.clear();
		}
		
		//initialize the game
		stockUpdaters = new ArrayList<StockUpdater>();
		
		//initilize StocksScreen
		StocksScreen.instance.getStocksList().getChildren().clear();
		for (Stock stock : StockHolder.instance.getStocks()) {

			//create a stockCell
			StocksScreen.instance.getStocksList().addStockCell(new StockCell(stock));
			//create a stock updater with random update interval
			stockUpdaters.add(new StockUpdater(new StockPriceController(stock, 2), new Random().nextInt(500)+900));
		}

		StocksScreen.instance.getStockGraph().setStock(StockHolder.instance.getStocks().get(0));
		StocksScreen.instance.getStockTradePanel().setStock(StockHolder.instance.getStocks().get(0));
		StocksScreen.instance.getStockTradePanel().setDisable(false);
		
		//start all stockUpdater threads
		for (StockUpdater stockUpdater : stockUpdaters) {
			stockUpdater.start();
		}
		
		//set base item prices for each itemType
		itemPrices = new HashMap<ItemType, Integer>();
		for (ItemType itemType : ItemType.values()) {
			itemPrices.put(itemType, 50);
		}
		
		//create a thread to constantly call marketUpdate and start it
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
	
	public static int getItemPrice(ItemType itemType) {
		return itemPrices.get(itemType);
	}

	public static List<StockUpdater> getStockUpdaters() {
		return stockUpdaters;
	}

	private static void generateRandomNews() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				NewsPane.instance.addNews(News.getRandomNews());
			}
			
		});
		System.out.println("RandomNews event");
	}

	private static void generateTraderChange() {
		System.out.println("TraderChange event");
		//randomly selects a trader and an item type
		ItemType type = ItemType.getRandomItemType();
		Trader trader = Trader.getRandomTrader();
		
		//traderChange won't happen if player is accessing the Trader 
		if(!trader.isAccessing()) {
			Random random = new Random();
			
			boolean increase = random.nextBoolean();
			boolean changeBuyPrice = random.nextBoolean();
			
			//generate changes and add news according to the change
			News news;
			if(changeBuyPrice) {
				news = generateBuyPriceChange(trader, type, increase);
			} else {
				news = generateSellPriceChange(trader, type, increase);
			}

			//add the news to news feed
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					NewsPane.instance.addNews(news);
				}
			});
			
		} else {
			//player is accessing the trader
			System.out.println("player is accessing the trader : traderChange skipped");
		}
		
	}
	
	private static News generateBuyPriceChange(Trader trader, ItemType type, boolean increase) {
		News news = new News("Market");
		double oldMultiplier = trader.getBuyPriceMultipliers().get(type);
		//generate how much the value changes
		double change = new Random().nextDouble()*MAX_MULTIPLIER_CHANGE + MIN_MULTIPLIER_CHANGE;
		double newMultiplier;
		
		if(increase) {
			//set the news content according to the change
			news.setContent(trader.getName() + " sells " + type.toString() + " more expensive.");
			
			newMultiplier = (oldMultiplier + change);
			if(newMultiplier > Trader.getMaxBuyPriceMultiplier(type)) {
				newMultiplier = Trader.getMaxBuyPriceMultiplier(type);
			}
			
		} else {
			//set the news content
			news.setContent(trader.getName() + " now sells " + type.toString() + " items at a discount price!");
			
			newMultiplier = (oldMultiplier - change);
			if(newMultiplier < Trader.getMinBuyPriceMultiplier(type)) {
				newMultiplier = Trader.getMinBuyPriceMultiplier(type);
			}

		}
		//update the trader's Buy Price Multiplier on the type
		trader.getBuyPriceMultipliers().put(type, newMultiplier);
		
		//return the news object
		return news;
	}

	private static News generateSellPriceChange(Trader trader, ItemType type, boolean increase) {
		News news = new News("Market");
		double oldMultiplier = trader.getBuyPriceMultipliers().get(type);
		//generate how much the value changes
		double change = new Random().nextDouble()*MAX_MULTIPLIER_CHANGE + MIN_MULTIPLIER_CHANGE;
		double newMultiplier;

		oldMultiplier = trader.getSellPriceMultipliers().get(type);
		if(increase) {
			//set the news content
			news.setContent("Trader "+trader.getName() + " now accepts " + type.toString() + " at better price!.");
			
			newMultiplier = (oldMultiplier + change);
			if(newMultiplier >= Trader.getMaxSellPriceMultiplier(type)) {
				newMultiplier = Trader.getMaxSellPriceMultiplier(type);
			}
			
		} else {
			//set the news content
			news.setContent("Trader "+trader.getName() + " now accepts " + type.toString() + " at reduced price.");
			
			newMultiplier = oldMultiplier - change;
			if(newMultiplier < Trader.getMinSellPriceMultiplier(type)) {
				newMultiplier = Trader.getMinSellPriceMultiplier(type);
			}
			
		}
		//update the trader's sell Price Multiplier on the type
		trader.getSellPriceMultipliers().put(type, newMultiplier);
		return news;
	}
	
	/**
	 * finalizeMarket is called when the game is ending.
	 * interrupts (and kill) all stockUpdater threads and the marketRunner.
	 */
	public static void finalizeMarket() {
		for (StockUpdater stockUpdater : stockUpdaters) {
			stockUpdater.interrupt();
		}
		if(Market.marketRunner != null && Market.marketRunner.isAlive()) {
			Market.marketRunner.interrupt();
		}
	}
}
