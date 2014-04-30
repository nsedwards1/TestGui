package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import towcon.pcap.processor.PcapReader;
import javafx.application.Platform;
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
    	
//    	DataManager.initDummyData(300);
    }
    
    @FXML
    private void updateGraph(ActionEvent event)
    {
//    	DataManager.updateDummyData(10);
        
    	//Add a bunch of values to a List to display in single graph
        List<LinkedList<Float>> fll = new ArrayList<LinkedList<Float>>();
        List<String> nameList1 = new ArrayList<String>();    // This is not a good way to do this. Feel free to change or give suggestion on better way.
        fll.add(DataManager.getFloatList("RRTowWinchPrt.wire_turn_total"));
        nameList1.add("float1");
        fll.add(DataManager.getFloatList("RRAHWinch.wire_length_paid_out"));
        nameList1.add("float2");
        fll.add(DataManager.getFloatList("RRAHWinch.wire_length_on_drum"));
        nameList1.add("float3");
        fll.add(DataManager.getBoolFloatList("boolfloat1"));
        nameList1.add("boolfloat1");
        h.assignListToChart(fll, pumpGraph, nameList1);
                
        //Add a bunch of values to a List to display in single graph
        List<LinkedList<Float>> fll2 = new ArrayList<LinkedList<Float>>();
        List<String> nameList2 = new ArrayList<String>();
        fll2.add(DataManager.getFloatList("RRTowWinchPrt.wire_turn_total"));
        nameList2.add("float4");
        fll2.add(DataManager.getFloatList("RRAHWinch.wire_length_paid_out"));
        nameList2.add("float5");
        fll2.add(DataManager.getFloatList("RRAHWinch.wire_length_on_drum"));
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
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.wire_turn_total"), pumpBool9, "chart9");//float value in bool chart
        
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
    	Thread t = new Thread() {
    	    public void run() {
    	        PcapReader reader = new PcapReader();
    	        reader.parseFile("C:\\Users\\rwright\\Downloads\\RMB VERS Logs\\winch_misbehave_130813_2.pcap");
    	    }
    	};
    	t.setName("PcapReader Thread");
    	t.setDaemon(true);
    	t.start();
    	
    	Thread t2 = new Thread() {
    	    public void run() {
    	        while(true) {
    	        	Platform.runLater(new Runnable() {
    	                @Override
    	                public void run() {
    	                	updateGraph(null);
    	                }
    	            });
    	        	
    	        	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        }
    	    }
    	};
    	t2.setName("Screen-Updater Thread");
    	t2.setDaemon(true);
    	t2.start();
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
