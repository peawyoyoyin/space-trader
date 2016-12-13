package stocks;

import java.util.Random;

public class StockPriceController {
	private Stock stock;
	private Random random;
	private int priceChangeModifier;
	
	private int maxStep;
	private int step;
	private int direction;
	
	public static final int DIRECTION_DOWN = -1;
	public static final int DIRECTION_UP = 1;
	
	public StockPriceController(Stock stock, int priceChangeModifier) {
		this.stock = stock;
		this.priceChangeModifier = priceChangeModifier;
		this.maxStep = 5;
		this.direction = 1;
		this.random = new Random();
	}
	
	public void updateStockPrice() {
		this.step = this.random.nextInt(this.maxStep+1);
		int[] temp = {DIRECTION_UP,DIRECTION_DOWN};
		this.direction = temp[this.random.nextInt(2)];
		
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
}
