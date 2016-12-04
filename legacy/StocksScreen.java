package stocksview;

import javafx.scene.layout.BorderPane;

public class StocksScreen extends BorderPane{
	public StocksScreen() {
		super();
		this.setPrefSize(250, 500);
		StocksGraphs stocksGraphs = new StocksGraphs();
		this.setTop(stocksGraphs);
	}
}
