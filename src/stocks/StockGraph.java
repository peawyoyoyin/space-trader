package stocks;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class StockGraph extends StackPane {
	
	private Stock stock;
	private LineChart<Number, Number> lineChart;
	private Series<Number, Number> series;
	
	public StockGraph() {
		super();
		//create a canvas to draw background
		Canvas back = new Canvas(200,200);
		back.getGraphicsContext2D().setFill(Color.BLACK);
		back.getGraphicsContext2D().fillRect(0, 0, 200, 200);
		this.getChildren().add(back);
		this.setMaxWidth(250);
		this.setMaxHeight(200);

		this.setEffect(new Glow(5));
		
		//create the chart
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
		this.lineChart.setEffect(new DropShadow(5, Color.AQUAMARINE));
		this.lineChart.setCreateSymbols(false);
		this.lineChart.getData().add(this.series);
		this.lineChart.setTitle("");
		this.lineChart.setAnimated(false);
		this.lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		this.lineChart.lookup(".chart-horizontal-grid-lines").setVisible(false);
		this.lineChart.lookup(".chart-vertical-grid-lines").setVisible(false);
		
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
		
		//clear the chart
		this.lineChart.getData().clear();
		
		//plots the chart using the stock's priceHistory
		Axis<Number> yAxis = this.lineChart.getYAxis();
		yAxis.setAutoRanging(false);
		
		//set the y axis's bounds so the lines don't actually touch the top and the bottom of the chart
		//this makes reading the chart easier
		((NumberAxis) yAxis).setUpperBound(this.stock.getMaxPrice()*1.1);
		((NumberAxis) yAxis).setLowerBound(this.stock.getMinPrice()-9);

		this.series = new Series<Number,Number>();
		List<Integer> priceHistory = this.stock.getPriceHistory();
		for(int i=0; i<priceHistory.size(); i++) {
			this.series.getData().add(new Data<Number, Number>(i, priceHistory.get(i)));
		}
		
		this.lineChart.getData().add(this.series);
	}
}
