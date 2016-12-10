package stocks;

import java.util.ArrayList;
import java.util.List;

public class StockHolder {
	public static final StockHolder instance = new StockHolder();
	
	private List<Stock> stocks;
	
	private StockHolder() {
		this.stocks = new ArrayList<Stock>();
	}
	
	public List<Stock> getStocks() {
		return this.stocks;
	}
}
