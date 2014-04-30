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
	private LineChart<Float, Float> graph, graphF, boolChart1, boolChart2, boolChart3, boolChart4, boolChart5, boolChart6;
		

    Helper h = new Helper();
    
    
    @FXML
    protected void initialize() {
    	
    	DataManager.initDummyData(300);
    }
    
    @FXML
    private void updateGraph(ActionEvent event)
    {
    	DataManager.updateDummyData(10);
        
    	//Add a bunch of values to a List to display in single graph
        List<LinkedList<Float>> fll = new ArrayList<LinkedList<Float>>();
        fll.add(DataManager.getFloatList("float1"));
        fll.add(DataManager.getFloatList("float2"));
        fll.add(DataManager.getFloatList("float3"));
        fll.add(DataManager.getBoolFloatList("boolfloat1"));
        h.assignListToChart(fll, graph);
                
        //Add a bunch of values to a List to display in single graph
        List<LinkedList<Float>> fll2 = new ArrayList<LinkedList<Float>>();
        fll2.add(DataManager.getFloatList("float4"));
        fll2.add(DataManager.getFloatList("float5"));
        fll2.add(DataManager.getFloatList("float6"));
        fll2.add(DataManager.getBoolFloatList("boolfloat4"));
        h.assignListToChart(fll2, graphF);
        
        //Probably best way to handle boolean-like values where chart holds one value:
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat4"), boolChart4);
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat1"), boolChart1);
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat2"), boolChart2);
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat3"), boolChart3);
        
        //Another way to do things, but probably not the best        
        boolChart5.setCreateSymbols(false);
        boolChart5.setData(h.getObsList(DataManager.getBoolFloatList("boolfloat2")));
        
        boolChart6.setCreateSymbols(false);
        boolChart6.setData(h.getObsList(DataManager.getBoolFloatList("boolfloat3")));
        
    }

}
