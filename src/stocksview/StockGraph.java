package stocksview;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import stocks.Stock;

public class StockGraph extends StackPane {
	
	private Stock stock;
	private LineChart<Number, Number> lineChart;
	private Series<Number, Number> series;
	
	public StockGraph() {
		super();
		
		this.setMaxHeight(200);
		this.setStyle("-fx-background-color:gray;");
		
		Axis<Number> xAxis = new NumberAxis();
		xAxis.setTickLabelsVisible(false);
		xAxis.setTickMarkVisible(false);
		((ValueAxis<Number>) xAxis).setMinorTickVisible(false);
		Axis<Number> yAxis = new NumberAxis();
		yAxis.setTickLabelsVisible(false);
		yAxis.setTickMarkVisible(false);
		((ValueAxis<Number>) yAxis).setMinorTickVisible(false);
		this.series = new Series<>();
		
		this.lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		this.lineChart.getData().add(this.series);
		this.lineChart.setTitle("");
		
		this.lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		this.lineChart.lookup(".chart-horizontal-grid-lines").setVisible(false);
//		this.lineChart.lookup(".chart-vertical-grid-lines").setVisible(false);
		
		this.getChildren().add(this.lineChart);
		this.stock = null;
	}
	
	public StockGraph(Stock stock) {
		this();
		this.stock = stock;
		this.updateGraph();
	}
	
	public void setStock(Stock stock) {
		System.out.println("setStock");
		this.stock = stock;
		this.updateGraph();
	}
	
	public synchronized void updateGraph() {
		if(stock == null) {
			return;
		}
		this.lineChart.setAnimated(false);
		this.lineChart.getData().clear();
		Axis yAxis = this.lineChart.getYAxis();
		yAxis.setAutoRanging(false);
		((NumberAxis) yAxis).setUpperBound(Stock.STOCK_PLACEHOLDER.getMaxPrice()*1.1);
		((NumberAxis) yAxis).setLowerBound(Stock.STOCK_PLACEHOLDER.getMinPrice()-1);
		this.series = new Series<Number,Number>();
		List<Integer> priceHistory = this.stock.getPriceHistory();
		System.out.println(priceHistory);
		for(int i=0; i<priceHistory.size(); i++) {
			this.series.getData().add(new Data<Number, Number>(i, priceHistory.get(i)));
		}
		this.lineChart.getData().add(this.series);
	}
}
