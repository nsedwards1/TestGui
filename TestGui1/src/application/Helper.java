package application;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Helper {
	
	public void addBoolToChart (LinkedList<Boolean> ll, 
								LineChart<Double, Double> lineChart)
	{
		ObservableList<XYChart.Series<Double, Double>> boolChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Double, Double> boolSeries = new LineChart.Series<Double, Double>();
        boolSeries.setName("Bool Series 1");
		double i=0;
		for (boolean b : ll)
		{
			boolSeries.getData().add(new XYChart.Data<Double, Double>(i, (b) ? 1.0 : 0.0));
			i++;
		}
		boolChartData.add(boolSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(boolChartData);
	}
	
	//ObservableList<XYChart.Series<Double, Double>> lineChartData2 = FXCollections.observableArrayList();
	public void addDoubleToChart (LinkedList<Double> ll, 
								  LineChart<Double, Double> lineChart)
	{
		ObservableList<XYChart.Series<Double, Double>> doubleChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Double, Double> doubleSeries = new LineChart.Series<Double, Double>();
        doubleSeries.setName("Double Series 1");
		double i=0;
		for (double d : ll)
		{
			doubleSeries.getData().add(new XYChart.Data<Double, Double>(i, d));
			i++;
		}
		doubleChartData.add(doubleSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(doubleChartData);
	}
	
	public ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
	public void addFloatToChart(LinkedList<Float> valueList, 
			  					LineChart<Float, Float> lineChart)
	{
		//ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
        //floatSeries.setName("Float Series 1");
		float i=0;
		for (float f : valueList)
		{
			floatSeries.getData().add(new XYChart.Data<Float, Float>(i, f));
			i++;
		}
		floatChartData.add(floatSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(floatChartData);
	}

	public void addFloatToChart(List<LinkedList<Float>> valueListArray, 
				LineChart<Float, Float> lineChart)
	{
		ObservableList<XYChart.Series<Float, Float>> lineChartData3 = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
				
        for (LinkedList<Float> ll : valueListArray)
        {
			float i=0;
			for (float f : ll)
			{
				floatSeries.getData().add(new XYChart.Data<Float, Float>(i, f));
				i++;
			}
			lineChartData3.add(floatSeries);
			floatSeries = new LineChart.Series<Float, Float>();
        }
		//lineChartData3.add(floatSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(lineChartData3);
	}
	
}
