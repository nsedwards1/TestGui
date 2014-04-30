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
	private LineChart<Float, Float> pumpGraph, miscGraph,
									pumpBool1, pumpBool2, pumpBool3, pumpBool4, pumpBool5, pumpBool6, pumpBool7, pumpBool8, pumpBool9, pumpBool10,
									pumpBool11, pumpBool12, pumpBool13, pumpBool14, pumpBool15, pumpBool16, pumpBool17, pumpBool18, pumpBool19, pumpBool20,
									pumpBool21, pumpBool22, pumpBool23, pumpBool24, pumpBool25, pumpBool26, pumpBool27, pumpBool28, pumpBool29, pumpBool30,
									pumpBool31, pumpBool32, pumpBool33, pumpBool34, pumpBool35, pumpBool36, pumpBool37, pumpBool38, pumpBool39, pumpBool40,
									pumpBool41, pumpBool42, pumpBool43, pumpBool44, pumpBool45, pumpBool46, pumpBool47, pumpBool48, pumpBool49, pumpBool50;
		
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
        List<String> nameList1 = new ArrayList<String>();
        fll.add(DataManager.getFloatList("float1"));
        nameList1.add("float1");
        fll.add(DataManager.getFloatList("float2"));
        nameList1.add("float2");
        fll.add(DataManager.getFloatList("float3"));
        nameList1.add("float3");
        fll.add(DataManager.getBoolFloatList("boolfloat1"));
        nameList1.add("boolfloat1");
        h.assignListToChart(fll, pumpGraph, nameList1);
                
        //Add a bunch of values to a List to display in single graph
        List<LinkedList<Float>> fll2 = new ArrayList<LinkedList<Float>>();
        List<String> nameList2 = new ArrayList<String>();
        fll2.add(DataManager.getFloatList("float4"));
        nameList2.add("float4");
        fll2.add(DataManager.getFloatList("float5"));
        nameList2.add("float5");
        fll2.add(DataManager.getFloatList("float6"));
        nameList2.add("float6");
        fll2.add(DataManager.getBoolFloatList("boolfloat4"));
        nameList2.add("boolfloat4");
        h.assignListToChart(fll2, miscGraph, nameList2);
        
        //Probably best way to handle boolean-like values where chart holds one value:
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat1"), pumpBool1, "long chart1");
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat2"), pumpBool2, "chart2");
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat3"), pumpBool3, "chart3");
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat4"), pumpBool31, "chart4");
        h.assignListToChart(DataManager.getBoolFloatList("boolfloat7"), pumpBool50, "chart5");
        h.assignListToChart(DataManager.getFloatList("float9"), pumpBool9, "chart9");//float value in bool chart
        
//        //Another way to do things, but probably not the best        
//        boolChart5.setCreateSymbols(false);
//        boolChart5.setData(h.getObsList(DataManager.getBoolFloatList("boolfloat2")));
//        
//        boolChart6.setCreateSymbols(false);
//        boolChart6.setData(h.getObsList(DataManager.getBoolFloatList("boolfloat3")));
        
    }
    
    @FXML
    private void startClicked (ActionEvent event)
    {
    	System.out.println("Start button clicked.");
    }
    
    @FXML
    private void btn1MinClicked (ActionEvent event)
    {
    	System.out.println("1 Min clicked.");
    }
    
    @FXML
    private void btn5MinClicked (ActionEvent event)
    {
    	System.out.println("5 Min clicked.");
    }
    
    @FXML
    private void btn10MinClicked (ActionEvent event)
    {
    	System.out.println("10 Min clicked.");
    }

}
