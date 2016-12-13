package market;

import javafx.application.Platform;
import stocks.StockPriceController;
import stocks.StocksScreen;

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
		super.run();
		try {
			//until it is interrupted, update the stockPrice every for updateInterval, forever.
			while (true) {
				Thread.sleep(this.updateInterval);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						stockPriceController.updateStockPrice();
						StocksScreen.instance.getStockGraph().updateGraph();
					}
				});

			}
		} catch (InterruptedException e) {
			System.out.println("Stockupdater Interrupted");
		}

	}
}