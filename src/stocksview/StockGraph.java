package stocksview;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.layout.StackPane;

public class StockGraph extends StackPane {
	
	private LineChart<Number, Number> lineChart;
	
	public StockGraph() {
		super();
		
		this.setMaxHeight(200);
		this.setStyle("-fx-background-color:black;");
		
		Axis<Number> xAxis = new NumberAxis();
		xAxis.setTickLabelsVisible(false);
		xAxis.setTickMarkVisible(false);
		((ValueAxis<Number>) xAxis).setMinorTickVisible(false);
		Axis<Number> yAxis = new NumberAxis();
		yAxis.setTickLabelsVisible(false);
		yAxis.setTickMarkVisible(false);
		((ValueAxis<Number>) yAxis).setMinorTickVisible(false);
		
		this.lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		
		this.lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		this.lineChart.lookup(".chart-horizontal-grid-lines").setVisible(false);
		this.lineChart.lookup(".chart-vertical-grid-lines").setVisible(false);
		
		this.getChildren().add(this.lineChart);
	}
}
