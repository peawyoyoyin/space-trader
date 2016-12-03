package stocks;

import java.util.ArrayList;
import java.util.List;

public class StockHolder {
	private static final StockHolder instance = new StockHolder();
	
	private List<Stock> stocks;
	
	private StockHolder() {
		this.stocks = new ArrayList<Stock>();
	}
	
	public static StockHolder getInstance() {
		return StockHolder.instance;
	}
	
	public List<Stock> getStocks() {
		return this.stocks;
	}
}
