package stocksview;

import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StocksGraphCell extends VBox{
	public StocksGraphCell() {
		super();
		this.setPrefHeight(50);
		this.setPrefWidth(50);
		this.setStyle("-fx-background-color: gray;");
		
		GraphCellTitle cellTitle = new GraphCellTitle();
		GraphCellChart graphCellChart = new GraphCellChart();
		
		this.getChildren().addAll(cellTitle,graphCellChart);
	}
}

class GraphCellTitle extends StackPane {
	public GraphCellTitle() {
		super();
		Label name = new Label("Stock Name");
		this.getChildren().add(name);
	}
}

class GraphCellChart extends StackPane {
	
	private LineChart<Number, Number> lineChart;
	
	public GraphCellChart() {
		super();
		this.setMaxHeight(50);
		NumberAxis xAxis = new NumberAxis();
		xAxis.setMinorTickVisible(false);
		xAxis.setTickMarkVisible(false);
		xAxis.setTickLabelsVisible(false);
		NumberAxis yAxis = new NumberAxis();
		yAxis.setMinorTickVisible(false);
		yAxis.setTickMarkVisible(false);
		yAxis.setTickLabelsVisible(false);
		yAxis.setAutoRanging(false);
		yAxis.setUpperBound(10);
		yAxis.setLowerBound(0);
		this.lineChart = new LineChart<Number,Number>(xAxis, yAxis);
		
		XYChart.Series<Number,Number> series = new Series<Number,Number>();
		series.getData().add(new Data<Number, Number>(0, 1));
		series.getData().add(new Data<Number, Number>(1, 3));
		series.getData().add(new Data<Number, Number>(2, 3));
		series.getData().add(new Data<Number, Number>(3, 2));
		series.getData().add(new Data<Number, Number>(4, 4));
		
		
		this.lineChart.getData().add(series);
		this.lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		this.lineChart.lookup(".chart-horizontal-grid-lines").setVisible(false);
		this.lineChart.lookup(".chart-vertical-grid-lines").setVisible(false);
		this.getChildren().add(lineChart);
	}
}