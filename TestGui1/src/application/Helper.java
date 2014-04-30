package application;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Helper {
	
	//Add a single Linked List to the chart
	public void assignListToChart(LinkedList<Float> valueList, 
			  					LineChart<Float, Float> lineChart)
	{
		ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
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

	//Add a single Linked List to the chart
	public void assignListToChart(List<LinkedList<Float>> valueListArray, 
				LineChart<Float, Float> lineChart)
	{
		ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
				
        for (LinkedList<Float> ll : valueListArray)
        {
			float i=0;
			for (float f : ll)
			{
				floatSeries.getData().add(new XYChart.Data<Float, Float>(i, f));
				i++;
			}
			floatChartData.add(floatSeries);
			floatSeries = new LineChart.Series<Float, Float>();
        }
		//floatChartData.add(floatSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(floatChartData);
	}
	
	public ObservableList<XYChart.Series<Float, Float>> getObsList (LinkedList<Float> valueList)
	{
		ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
        //floatSeries.setName("Float Series 1");
		float i=0;
		for (float f : valueList)
		{
			floatSeries.getData().add(new XYChart.Data<Float, Float>(i, f));
			i++;
		}
		floatChartData.add(floatSeries);
		
		return floatChartData;
	}
	
}
