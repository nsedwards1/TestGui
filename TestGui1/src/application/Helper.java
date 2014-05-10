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
			  					LineChart<Float, Float> lineChart,
			  					String name)
	{
		ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
        floatSeries.setName(name);
		float i=0;
		synchronized (valueList)
		{
			for (float f : valueList)
			{
				floatSeries.getData().add(new XYChart.Data<Float, Float>(i, f));
				i++;
			}
		}
		floatChartData.add(floatSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(floatChartData);
	}

	//Add multiple Linked Lists to the chart
	public void assignListToChart(List<LinkedList<Float>> valueListArray, 
				LineChart<Float, Float> lineChart,
				List<String> names)
	{
		ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
		int j=0;		
        for (LinkedList<Float> valueList : valueListArray)
        {
        	floatSeries.setName(names.get(j));
			float i=0;
			synchronized (valueList)
			{
				for (float f : valueList)
				{
					floatSeries.getData().add(new XYChart.Data<Float, Float>(i, f));
					i++;
				}
			}
			floatChartData.add(floatSeries);
			floatSeries = new LineChart.Series<Float, Float>();
			j++;
        }
		//floatChartData.add(floatSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(floatChartData);
	}	
}
