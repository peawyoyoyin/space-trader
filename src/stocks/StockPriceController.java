package stocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockPriceController {
	private Stock stock;
	private Random random;
	private List<StockEvent> stockEvent;
	private int priceChangeModifier;
	
	private int maxStep;
	private int step;
	private int direction;
	
	public static final int DIRECTION_DOWN = -1;
	public static final int DIRECTION_UP = 1;
	
	public StockPriceController(Stock stock, int priceChangeModifier) {
		this.stock = stock;
		this.priceChangeModifier = priceChangeModifier;
		this.stockEvent = new ArrayList<StockEvent>();
		this.maxStep = 5;
		this.direction = 1;
		this.random = new Random();
	}
	
	public StockPriceController(Stock stock, int priceChangeModifier, Random random) {
		this.stock = stock;
		this.random = random;
		this.priceChangeModifier = priceChangeModifier;
		this.stockEvent = new ArrayList<StockEvent>();
	}
	
	public void updateStockPrice() {
		this.step = this.random.nextInt(this.maxStep+1);
		int[] temp = {DIRECTION_UP,DIRECTION_DOWN};
		this.direction = temp[this.random.nextInt(2)];
		
		for (StockEvent stockEvent : this.stockEvent) {
			stockEvent.onUpdate(this);
		}
		
		switch(this.direction) {
		case DIRECTION_UP: {
			this.stock.increasePrice(this.step * this.priceChangeModifier);
			break;
		}
		case DIRECTION_DOWN: {
			this.stock.decreasePrice(this.step * this.priceChangeModifier);
			break;
		}
		}
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
