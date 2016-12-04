package stocksview;

import javafx.scene.layout.VBox;

public class StocksGraphs extends VBox{
	public StocksGraphs() {
		super();
		this.getChildren().addAll(new StocksGraphCell(),new StocksGraphCell());
	}
}