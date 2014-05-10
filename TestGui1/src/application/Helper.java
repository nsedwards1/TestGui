package application;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class Helper {
	
	//Add a single Linked List to the chart
	public void assignListToChart(LinkedList<XYChart.Data<Float, Float>> valueList, 
			  					LineChart<Float, Float> lineChart,
			  					String name)
	{
		ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
        floatSeries.setName(name);
		float i=0;
		synchronized (valueList)
		{
			float offset = 0;
			for (Data<Float, Float> iter : valueList)
			{
				if(i == 0)
					offset = iter.getXValue();
				
				floatSeries.getData().add(new Data<Float, Float>(iter.getXValue() - offset, iter.getYValue()));
				i++;
			}
		}
		floatChartData.add(floatSeries);
		lineChart.setCreateSymbols(false);
		lineChart.setData(floatChartData);
		lineChart.getXAxis().setAutoRanging(true);
	}

	//Add multiple Linked Lists to the chart
	public void assignListToChart(List<LinkedList<XYChart.Data<Float, Float>>> valueListArray, 
				LineChart<Float, Float> lineChart,
				List<String> names)
	{
		ObservableList<XYChart.Series<Float, Float>> floatChartData = FXCollections.observableArrayList();
		
		LineChart.Series<Float, Float> floatSeries = new LineChart.Series<Float, Float>();
		int j=0;		
        for (LinkedList<Data<Float, Float>> valueList : valueListArray)
        {
        	floatSeries.setName(names.get(j));
			float i=0;
			synchronized (valueList)
			{
				float offset = 0;
				for (Data<Float, Float> iter : valueList)
				{
					if(i == 0)
						offset = iter.getXValue();
					
					floatSeries.getData().add(new Data<Float, Float>(iter.getXValue() - offset, iter.getYValue()));
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
