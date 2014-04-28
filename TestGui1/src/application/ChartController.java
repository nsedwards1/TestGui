package application;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartController {
	
	@FXML
	private LineChart<Double, Double> graph;
	
	@FXML
	private LineChart<Double, Double> boolChart1;
	
	@FXML
	private LineChart<Double, Double> boolChart2;
	
	@FXML
	private LineChart<Double, Double> boolChart3;
	
	private LinkedList<Double> ll = new LinkedList<Double>();
	private LinkedList<Boolean> bll1 = new LinkedList<Boolean>();
	private LinkedList<Boolean> bll2 = new LinkedList<Boolean>();
	private LinkedList<Boolean> bll3 = new LinkedList<Boolean>();
	
	private void populateList()
	{
		ll.add(0.1);
		ll.add(0.2);
		ll.add(0.4);
		ll.add(0.8);
		ll.add(1.6);
		ll.add(0.8);
		ll.add(0.4);
		ll.add(0.2);
		ll.add(0.1);
		
		bll1.add(false);
		bll1.add(false);
		bll1.add(false);
		bll1.add(true);
		bll1.add(true);
		bll1.add(true);
		bll1.add(true);
		bll1.add(true);
		bll1.add(true);
		
		bll2.add(false);
		bll2.add(false);
		bll2.add(false);
		bll2.add(false);
		bll2.add(false);
		bll2.add(true);
		bll2.add(true);
		bll2.add(true);
		bll2.add(true);
		
		bll3.add(false);
		bll3.add(false);
		bll3.add(false);
		bll3.add(false);
		bll3.add(false);
		bll3.add(false);
		bll3.add(false);
		bll3.add(true);
		bll3.add(true);
	}
    
    @FXML
    protected void initialize() {
    }
    
    @FXML
    private void updateGraph(ActionEvent event)
    {
    	populateList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData1 = FXCollections.observableArrayList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData2 = FXCollections.observableArrayList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData3 = FXCollections.observableArrayList();
    	
//    	LineChart.Series<Double, Double> series3 = new LineChart.Series<Double, Double>();
//        series3.setName("Series 2");
//        series3.getData().add(new XYChart.Data<Double, Double>(1.0, 1.6));
//        series3.getData().add(new XYChart.Data<Double, Double>(1.8, 0.4));
//        series3.getData().add(new XYChart.Data<Double, Double>(2.4, 2.9));
//        series3.getData().add(new XYChart.Data<Double, Double>(1.1, 1.3));
//        series3.getData().add(new XYChart.Data<Double, Double>(1.6, 0.9));
//        
//        lineChartData.add(series3);
        
    	//Analog Graph
    	LineChart.Series<Double, Double> series4 = new LineChart.Series<Double, Double>();
		series4.setName("Series 4");
		double i=0;
		for (double d : ll)
		{
			series4.getData().add(new XYChart.Data<Double, Double>(i, d));
			i = i + 1;
		}
		lineChartData.add(series4);
		graph.setCreateSymbols(false);
        graph.setData(lineChartData);
        graph.createSymbolsProperty();
        
        //Boolean Chart 1
        LineChart.Series<Double, Double> boolSeries1 = new LineChart.Series<Double, Double>();
        boolSeries1.setName("Bool Series 1");
		i=0;
		for (boolean b : bll1)
		{
			boolSeries1.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i = i + 1;
		}
		lineChartData1.add(boolSeries1);
		boolChart1.setCreateSymbols(false);
		boolChart1.setData(lineChartData1);
		boolChart1.createSymbolsProperty();
		
		//Boolean Chart 2
        LineChart.Series<Double, Double> boolSeries2 = new LineChart.Series<Double, Double>();
        boolSeries2.setName("Bool Series 1");
		i=0;
		for (boolean b : bll2)
		{
			boolSeries2.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i = i + 1;
		}
		lineChartData2.add(boolSeries2);
		boolChart2.setCreateSymbols(false);
		boolChart2.setData(lineChartData2);
		boolChart2.createSymbolsProperty();
		
		//Boolean Chart 3
        LineChart.Series<Double, Double> boolSeries3 = new LineChart.Series<Double, Double>();
        boolSeries3.setName("Bool Series 1");
		i=0;
		for (boolean b : bll3)
		{
			boolSeries3.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i = i + 1;
		}
		lineChartData3.add(boolSeries3);
		boolChart3.setCreateSymbols(false);
		boolChart3.setData(lineChartData3);
		boolChart3.createSymbolsProperty();
		

    }
}
