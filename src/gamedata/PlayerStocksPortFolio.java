package gamedata;

import java.util.ArrayList;
import java.util.List;

import stocks.Stock;
import stocks.StockHolder;

public class PlayerStocksPortFolio {
	
	public static PlayerStocksPortFolio instance = new PlayerStocksPortFolio();
	
	List<StockTradeData> allTradeData;
	
	public PlayerStocksPortFolio() {
		this.allTradeData = new ArrayList<StockTradeData>();
		for (Stock stock : StockHolder.getInstance().getStocks()) {
			this.allTradeData.add(new StockTradeData(stock));
		}
	}
	
	public StockTradeData getStockTradeData(Stock stock) {
		for (StockTradeData stockTradeData : allTradeData) {
			if(stockTradeData.getStock() == stock) {
				return stockTradeData;
			}
		}
		return null;
	}
}
