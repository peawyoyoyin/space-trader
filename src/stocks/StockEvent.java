package stocks;

public abstract class StockEvent {
	public abstract void onStart(StockPriceController stockPriceController);
	public abstract void onUpdate(StockPriceController stockPriceController);
	public abstract void onEnd(StockPriceController stockPriceController);
}
