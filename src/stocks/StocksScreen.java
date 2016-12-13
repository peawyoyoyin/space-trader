package stocks;

import constants.ConfigConstant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StocksScreen extends StackPane {

	public static final StocksScreen instance = new StocksScreen();

	private BorderPane container;
	private StocksList stocksList;
	private StockGraph stocksGraph;
	private StockTradePanel stockTradePanel;

	public StocksScreen() {
		super();
		Canvas back = new Canvas(ConfigConstant.gameScreen_right_width, ConfigConstant.gameScreenHeight);
		back.getGraphicsContext2D().drawImage(ConfigConstant.Resource.STOCKS_BG, 0, 0);
		
		this.container = new BorderPane();
		this.container.setMaxSize(ConfigConstant.gameScreen_right_width, ConfigConstant.gameScreenHeight);
		this.stocksList = new StocksList();
		this.stocksGraph = new StockGraph();
		this.stockTradePanel = new StockTradePanel();
		VBox topContainer = new VBox(10);
		Label header = new Label("Stocks");
		header.setFont(ConfigConstant.Resource.HUD_MID_FONT);
		header.setTextFill(Color.WHITE);
		header.setOpacity(0.5);
		topContainer.setPadding(new Insets(7,0,0,0));
		topContainer.setAlignment(Pos.TOP_CENTER);
		topContainer.getChildren().add(header);
		topContainer.getChildren().add(stocksList);
		this.container.setTop(topContainer);
		this.container.setCenter(stocksGraph);
		this.container.setBottom(stockTradePanel);
		this.container.setPadding(new Insets(5,20,20,20));
		this.getChildren().addAll(back, container);
	}

	public StocksList getStocksList() {
		return this.stocksList;
	}

	public StockGraph getStockGraph() {
		return this.stocksGraph;
	}

	public StockTradePanel getStockTradePanel() {
		return this.stockTradePanel;
	}

	public class StocksList extends VBox {
		private StockCell currentCell;

		public StocksList() {
			super();
		}

		public void addStockCell(StockCell stockCell) {
			this.getChildren().add(stockCell);
			stockCell.setOnMouseClicked(event -> {
				StocksScreen.instance.getStockGraph().setStock(stockCell.getStock());
				StocksScreen.instance.getStockTradePanel().setStock(stockCell.getStock());
				StocksScreen.instance.getStockGraph().updateGraph();
				currentCell.setActive(false);
				currentCell = stockCell;
				currentCell.setActive(true);
			});
			if (this.currentCell == null) {
				this.currentCell = stockCell;
				this.currentCell.setActive(true);
				StocksScreen.instance.getStockGraph().setStock(stockCell.getStock());
				StocksScreen.instance.getStockTradePanel().setStock(stockCell.getStock());
				StocksScreen.instance.getStockGraph().updateGraph();
			}
		}
	}

}
