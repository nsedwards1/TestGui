package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartController {
	
	@FXML
	private LineChart<Double, Double> graph, boolChart1, boolChart2, boolChart3, boolChart4;
	
	
	private LinkedList<Boolean> bll1 = new LinkedList<Boolean>();
	private LinkedList<Boolean> bll2 = new LinkedList<Boolean>();
	private LinkedList<Boolean> bll3 = new LinkedList<Boolean>();
	
	private HashMap<String, LinkedList<Boolean>> boolMap = new HashMap<String, LinkedList<Boolean>>();
	private HashMap<String, LinkedList<Double>> doubleMap = new HashMap<String, LinkedList<Double>>();
	
	private void populateList()
	{
		
		DataManager.initDummyData(100);;
		bll1 = DataManager.getBooleanList("bool1");
		bll2 = DataManager.getBooleanList("bool2");
		bll3 = DataManager.getBooleanList("bool3");
		
		boolMap.put("bool0", DataManager.getBooleanList("bool0"));
		boolMap.put("bool1", DataManager.getBooleanList("bool1"));
		boolMap.put("bool2", DataManager.getBooleanList("bool2"));
		boolMap.put("bool3", DataManager.getBooleanList("bool3"));
		
		doubleMap.put("double0", DataManager.getDoubleList("double0"));
		doubleMap.put("double1", DataManager.getDoubleList("double1"));
		doubleMap.put("double2", DataManager.getDoubleList("double2"));
	}
    
    @FXML
    protected void initialize() {
    }
    
    @FXML
    private void updateGraph(ActionEvent event)
    {
    	populateList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData0 = FXCollections.observableArrayList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData1 = FXCollections.observableArrayList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData2 = FXCollections.observableArrayList();
    	ObservableList<XYChart.Series<Double, Double>> lineChartData3 = FXCollections.observableArrayList();

		
		//Iterate through HashMap of Linked Lists and add each to Line Chart
		LineChart.Series<Double, Double> d_series = new LineChart.Series<Double, Double>();
    	for (Map.Entry entry : doubleMap.entrySet())
    	{
    		d_series.setName((String) entry.getKey());
    		double i=0;
    		for (double d : (LinkedList<Double>) entry.getValue())
    		{
    			d_series.getData().add(new XYChart.Data<Double, Double>(i, d));
    			i++;
    		}
    		lineChartData0.add(d_series);
    		d_series = new LineChart.Series<Double, Double>();
    	}
		graph.setCreateSymbols(false);
        graph.setData(lineChartData0);
        
        
        //Boolean Chart 1
        LineChart.Series<Double, Double> boolSeries1 = new LineChart.Series<Double, Double>();
        boolSeries1.setName("Bool Series 1");
		double i=0;
		for (boolean b : bll1)
		{
			boolSeries1.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i = i + 1;
		}
		lineChartData1.add(boolSeries1);
		boolChart1.setCreateSymbols(false);
		boolChart1.setData(lineChartData1);
		
		
		//Boolean Chart 2
        LineChart.Series<Double, Double> boolSeries2 = new LineChart.Series<Double, Double>();
        boolSeries2.setName("Bool Series 2");
		i=0;
		for (boolean b : bll2)
		{
			boolSeries2.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i = i + 1;
		}
		lineChartData2.add(boolSeries2);
		boolChart2.setCreateSymbols(false);
		boolChart2.setData(lineChartData2);
		
		
		//Boolean Chart 3
        LineChart.Series<Double, Double> boolSeries3 = new LineChart.Series<Double, Double>();
        boolSeries3.setName("Bool Series 3");
		i=0;
		for (boolean b : bll3)
		{
			boolSeries3.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i = i + 1;
		}
		lineChartData3.add(boolSeries3);
		boolChart3.setCreateSymbols(false);
		boolChart3.setData(lineChartData3);
		
		//updateBoolChart();
    }

    private void updateBoolChart()
    {
    	ObservableList<XYChart.Series<Double, Double>> lineChartData4 = FXCollections.observableArrayList();
    	
    	LineChart.Series<Double, Double> boolSeries4 = new LineChart.Series<Double, Double>();
        boolSeries4.setName("Bool Series 4");
		double i=0;
		for (boolean b : bll3)
		{
			boolSeries4.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i = i + 1;
		}
		lineChartData4.add(boolSeries4);
		boolChart4.setCreateSymbols(false);
		boolChart4.setData(lineChartData4);
    }
}
