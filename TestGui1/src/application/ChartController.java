package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartController {
	
	@FXML
	private LineChart<Double, Double> graph, boolChart1, boolChart2, boolChart3;
	
	@FXML
	private LineChart<Float, Float> graphF, boolChart4, boolChart5, boolChart6;
		

    Helper h = new Helper();
    
    
    @FXML
    protected void initialize() {
    	
    	DataManager.initDummyData(300);
    }
    
    @FXML
    private void updateGraph(ActionEvent event)
    {
    	h.floatChartData.clear();
    	DataManager.updateDummyData(10);
        
        h.addDoubleToChart(DataManager.getDoubleList("double1"), graph);
        h.addDoubleToChart(DataManager.getDoubleList("double2"), graph);
        h.addDoubleToChart(DataManager.getDoubleList("double3"), graph);
        
        //Add a bunch of values to a List to display in single graph
        List<LinkedList<Float>> fll = new ArrayList<LinkedList<Float>>();
        fll.add(DataManager.getFloatList("float1"));
        fll.add(DataManager.getFloatList("float2"));
        fll.add(DataManager.getFloatList("float3"));
        h.addFloatToChart(fll, graphF);
        
        h.addFloatToChart(DataManager.getFloatList("float1"), graphF);
        h.addFloatToChart(DataManager.getFloatList("float2"), graphF);
        h.addFloatToChart(DataManager.getFloatList("float3"), graphF);
                		
        h.addBoolToChart(DataManager.getBooleanList("bool1"), boolChart1);
        h.addBoolToChart(DataManager.getBooleanList("bool2"), boolChart2);
        h.addBoolToChart(DataManager.getBooleanList("bool3"), boolChart3);
        
        //h.addFloatToChart(DataManager.getBoolFloatList("boolfloat1"), boolChart4);
    }

}
