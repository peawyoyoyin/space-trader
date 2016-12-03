package stocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockPriceController {
	private Stock stock;
	private Random random;
	private List<StockEvent> stockEvent;
	private int priceChangeModifier;
	
	
	public StockPriceController(Stock stock, int priceChangeModifier) {
		this.stock = stock;
		this.priceChangeModifier = priceChangeModifier;
		this.stockEvent = new ArrayList<StockEvent>();
	}
	
	public StockPriceController(Stock stock, int priceChangeModifier, Random random) {
		this.stock = stock;
		this.random = random;
		this.priceChangeModifier = priceChangeModifier;
		this.stockEvent = new ArrayList<StockEvent>();
	}
	
	public void updateStockPrice() {
	}
	
	public void addEvent(StockEvent event) {
		this.stockEvent.add(event);
		event.onStart(this);
	}
	
	public void removeEvent(StockEvent event) {
		this.stockEvent.remove(event);
		event.onEnd(this);
	}

}
