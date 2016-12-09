package market;

import javafx.application.Platform;
import stocks.StockPriceController;
import stocksview.StocksScreen;

public class StockUpdater extends Thread{
	
	private int updateInterval;
	private StockPriceController stockPriceController;
	
	public StockUpdater(StockPriceController stockPriceController, int updateInterval) {
		super();
		this.stockPriceController = stockPriceController;
		this.updateInterval = updateInterval;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true) {
			try {
				Thread.sleep(this.updateInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					stockPriceController.updateStockPrice();
					StocksScreen.instance.getStockGraph().updateGraph();
				}
			});

		}
	}
	
	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}
	
	public int getUpdateInterval() {
		return this.updateInterval;
	}
}