package market;

import javafx.application.Platform;
import stocks.StockPriceController;
import stocksview.StocksScreen;

public class StockUpdater extends Thread {

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
		try {
			while (true) {
				Thread.sleep(this.updateInterval);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						stockPriceController.updateStockPrice();
						StocksScreen.instance.getStockGraph().updateGraph();
					}
				});

			}
		} catch (InterruptedException e) {
			System.out.println("Y");
		}

	}

	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}

	public int getUpdateInterval() {
		return this.updateInterval;
	}
}