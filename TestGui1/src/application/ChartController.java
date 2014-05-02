package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import towcon.pcap.processor.PcapReader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class ChartController {
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private LineChart<Float, Float> pumpGraph, miscGraph, AHGraph, SHGraph, TowStbGraph, TowPrtGraph,
									pumpBool1,  pumpBool2,  pumpBool3,  pumpBool4,  pumpBool5,  pumpBool6,  pumpBool7,  pumpBool8,  pumpBool9,  pumpBool10,
									pumpBool11, pumpBool12, pumpBool13, pumpBool14, pumpBool15, pumpBool16, pumpBool17, pumpBool18, pumpBool19, pumpBool20,
									pumpBool21, pumpBool22, pumpBool23, pumpBool24, pumpBool25, pumpBool26, pumpBool27, pumpBool28, pumpBool29, pumpBool30,
									pumpBool31, pumpBool32, pumpBool33, pumpBool34, pumpBool35, pumpBool36, pumpBool37, pumpBool38, pumpBool39, pumpBool40,
									pumpBool41, pumpBool42, pumpBool43, pumpBool44, pumpBool45, pumpBool46, pumpBool47, pumpBool48, pumpBool49, pumpBool50,
									
									miscBool1,  miscBool2,  miscBool3,  miscBool4,  miscBool5,  miscBool6,  miscBool7,  miscBool8,  miscBool9,  miscBool10,
									miscBool11, miscBool12, miscBool13, miscBool14, miscBool15, miscBool16, miscBool17, miscBool18, miscBool19, miscBool20,
									miscBool21, miscBool22, miscBool23, miscBool24, miscBool25, miscBool26, miscBool27, miscBool28, miscBool29, miscBool30,
									miscBool31, miscBool32,
									
									AHWinchBool1,  AHWinchBool2,  AHWinchBool3,  AHWinchBool4,  AHWinchBool5,  AHWinchBool6,  AHWinchBool7,  AHWinchBool8,  AHWinchBool9,  AHWinchBool10,
									AHWinchBool11, AHWinchBool12, AHWinchBool13, AHWinchBool14, AHWinchBool15, AHWinchBool16, AHWinchBool17, AHWinchBool18, AHWinchBool19, AHWinchBool20,
									AHWinchBool21, AHWinchBool22, AHWinchBool23, AHWinchBool24, AHWinchBool25, AHWinchBool26, AHWinchBool27, AHWinchBool28,
									
									SHWinchBool1,  SHWinchBool2,  SHWinchBool3,  SHWinchBool4,  SHWinchBool5,  SHWinchBool6,  SHWinchBool7,  SHWinchBool8,  SHWinchBool9,  SHWinchBool10,
									SHWinchBool11, SHWinchBool12, SHWinchBool13, SHWinchBool14, SHWinchBool15, SHWinchBool16, SHWinchBool17, SHWinchBool18, SHWinchBool19, SHWinchBool20,
									SHWinchBool21, SHWinchBool22, SHWinchBool23, SHWinchBool24, SHWinchBool25, SHWinchBool26, SHWinchBool27, SHWinchBool28,
									
									TowWinchPrtBool1,  TowWinchPrtBool2,  TowWinchPrtBool3,  TowWinchPrtBool4,  TowWinchPrtBool5,  TowWinchPrtBool6,  TowWinchPrtBool7,  TowWinchPrtBool8,  TowWinchPrtBool9,  TowWinchPrtBool10,
									TowWinchPrtBool11, TowWinchPrtBool12, TowWinchPrtBool13, TowWinchPrtBool14, TowWinchPrtBool15, TowWinchPrtBool16, TowWinchPrtBool17, TowWinchPrtBool18, TowWinchPrtBool19, TowWinchPrtBool20,
									TowWinchPrtBool21, TowWinchPrtBool22, TowWinchPrtBool23, TowWinchPrtBool24, TowWinchPrtBool25, TowWinchPrtBool26, TowWinchPrtBool27, TowWinchPrtBool28,
									
									TowWinchStbBool1,  TowWinchStbBool2,  TowWinchStbBool3,  TowWinchStbBool4,  TowWinchStbBool5,  TowWinchStbBool6,  TowWinchStbBool7,  TowWinchStbBool8,  TowWinchStbBool9,  TowWinchStbBool10,
									TowWinchStbBool11, TowWinchStbBool12, TowWinchStbBool13, TowWinchStbBool14, TowWinchStbBool15, TowWinchStbBool16, TowWinchStbBool17, TowWinchStbBool18, TowWinchStbBool19, TowWinchStbBool20,
									TowWinchStbBool21, TowWinchStbBool22, TowWinchStbBool23, TowWinchStbBool24, TowWinchStbBool25, TowWinchStbBool26, TowWinchStbBool27, TowWinchStbBool28
									;
		
    Helper h = new Helper();
    
    
    @FXML
    protected void initialize() {
    	
//    	DataManager.initDummyData(300);
    }

    @FXML
    private void updateGraph(ActionEvent event)
    {
//    	DataManager.updateDummyData(10);
    	
    	String selected = tabPane.getSelectionModel().getSelectedItem().getText();
    	
    	switch(selected) {
    	case "Pump Room":
    		updatePumpRoom();
    		break;
    	case "Misc":
            updateMisc();
    		break;
    	case "AH Winch":
            updateAHWinch();
    		break;
    	case "SH Winch":
            updateSHWinch();
    		break;
    	case "Tow Winch Stbd":
            updateStbd();
    		break;
    	case "Tow Winch Port":
            updatePort();
    		break;
		default:
			break;
    	}
    	
    }

	private void updatePort() {
		//TowPrt Winch Big Graph
        List<LinkedList<Float>> TowPrtLL = new ArrayList<LinkedList<Float>>();
        List<String> TowPrtList = new ArrayList<String>();
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.brake_on"));
        TowPrtList.add("brake_on");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.coupling_in"));
        TowPrtList.add("coupling_in");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.drum_ang_vel"));
        TowPrtList.add("drum_ang_vel");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.drum_radius"));
        TowPrtList.add("drum_radius");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.drum_rot_pos"));
        TowPrtList.add("drum_rot_pos");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.drum_rpm"));
        TowPrtList.add("drum_rpm");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.wire_layer"));
        TowPrtList.add("wire_layer");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.wire_length_on_drum"));
        TowPrtList.add("wire_length_on_drum");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.wire_length_paid_out"));
        TowPrtList.add("wire_length_paid_out");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.wire_speed"));
        TowPrtList.add("wire_speed");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.wire_turn"));
        TowPrtList.add("wire_turn");
        TowPrtLL.add(DataManager.getFloatList("RRTowWinchPrt.wire_turn_total"));
        TowPrtList.add("wire_turn_total");
        h.assignListToChart(TowPrtLL, TowPrtGraph, TowPrtList);
        //TowWinchPrt Sent Values
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.cmd_brake_on"),				TowWinchPrtBool1,  "cmd_brake_on             ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.cmd_speed"),					TowWinchPrtBool2,  "cmd_speed                ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.init_wire_length_paid_out"),	TowWinchPrtBool3,  "init_wire_length_paid_out");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.init_total_wire_length"),		TowWinchPrtBool4,  "init_total_wire_length   ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.reset_wirelengths"),			TowWinchPrtBool5,  "reset_wirelengths        ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.wire_diameter"),				TowWinchPrtBool6,  "wire_diameter            ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.wire_weigth"),				TowWinchPrtBool7,  "wire_weigth              ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.wire_tension"),				TowWinchPrtBool8,  "wire_tension             ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.active_drum"),				TowWinchPrtBool9,  "active_drum              ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.config_kabelar_number"),		TowWinchPrtBool10, "config_kabelar_number    ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.config_kabelar_typeid"),		TowWinchPrtBool11, "config_kabelar_typeid    ");
        h.assignListToChart(DataManager.getFloatList("TowWinchPrt.cmd_config_kabelar"),			TowWinchPrtBool12, "cmd_config_kabelar       ");
        //TowWinchPrt Received Values
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.brake_on"),  			TowWinchPrtBool13, "brake_on                 "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.coupling_in"), 			TowWinchPrtBool14, "coupling_in              "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.drum_ang_vel"),			TowWinchPrtBool15, "drum_ang_vel             "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.drum_radius"),			TowWinchPrtBool16, "drum_radius              "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.drum_rot_pos"),			TowWinchPrtBool17, "drum_rot_pos             "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.drum_rpm"),  			TowWinchPrtBool18, "drum_rpm                 "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.wire_layer"),			TowWinchPrtBool19, "wire_layer               "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.wire_length_on_drum"),	TowWinchPrtBool20, "wire_length_on_drum      "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.wire_length_paid_out"),	TowWinchPrtBool21, "wire_length_paid_out     "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.wire_speed"),			TowWinchPrtBool22, "wire_speed               "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.wire_turn"),			TowWinchPrtBool23, "wire_turn                "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrt.wire_turn_total"),		TowWinchPrtBool24, "wire_turn_total          "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrtSpooler.cmd_move_port_port"),	TowWinchPrtBool25, "cmd_move_port_port       "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrtSpooler.cmd_move_port_stb"),		TowWinchPrtBool26, "cmd_move_port_stb        "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrtSpooler.cmd_move_stb_port"),		TowWinchPrtBool27, "cmd_move_stb_port        "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchPrtSpooler.cmd_move_stb_stb"),		TowWinchPrtBool28, "cmd_move_stb_stb         "); 
	}

	private void updateStbd() {
		//TowStb Winch Big Graph
        List<LinkedList<Float>> TowStbLL = new ArrayList<LinkedList<Float>>();
        List<String> TowStbList = new ArrayList<String>();
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.brake_on"));
        TowStbList.add("brake_on");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.coupling_in"));
        TowStbList.add("coupling_in");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.drum_ang_vel"));
        TowStbList.add("drum_ang_vel");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.drum_radius"));
        TowStbList.add("drum_radius");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.drum_rot_pos"));
        TowStbList.add("drum_rot_pos");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.drum_rpm"));
        TowStbList.add("drum_rpm");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.wire_layer"));
        TowStbList.add("wire_layer");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.wire_length_on_drum"));
        TowStbList.add("wire_length_on_drum");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.wire_length_paid_out"));
        TowStbList.add("wire_length_paid_out");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.wire_speed"));
        TowStbList.add("wire_speed");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.wire_turn"));
        TowStbList.add("wire_turn");
        TowStbLL.add(DataManager.getFloatList("RRTowWinchStb.wire_turn_total"));
        TowStbList.add("wire_turn_total");
        h.assignListToChart(TowStbLL, TowStbGraph, TowStbList);
        //TowWinchStb Sent Values
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.cmd_brake_on"),				TowWinchStbBool1,  "cmd_brake_on             ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.cmd_speed"),					TowWinchStbBool2,  "cmd_speed                ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.init_wire_length_paid_out"),	TowWinchStbBool3,  "init_wire_length_paid_out");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.init_total_wire_length"),		TowWinchStbBool4,  "init_total_wire_length   ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.reset_wirelengths"),			TowWinchStbBool5,  "reset_wirelengths        ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.wire_diameter"),				TowWinchStbBool6,  "wire_diameter            ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.wire_weigth"),				TowWinchStbBool7,  "wire_weigth              ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.wire_tension"),				TowWinchStbBool8,  "wire_tension             ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.active_drum"),				TowWinchStbBool9,  "active_drum              ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.config_kabelar_number"),		TowWinchStbBool10, "config_kabelar_number    ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.config_kabelar_typeid"),		TowWinchStbBool11, "config_kabelar_typeid    ");
        h.assignListToChart(DataManager.getFloatList("TowWinchStb.cmd_config_kabelar"),			TowWinchStbBool12, "cmd_config_kabelar       ");
        //TowWinchStb Received Values
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.brake_on"),  			TowWinchStbBool13, "brake_on                 ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.coupling_in"), 			TowWinchStbBool14, "coupling_in              ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.drum_ang_vel"),			TowWinchStbBool15, "drum_ang_vel             ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.drum_radius"),			TowWinchStbBool16, "drum_radius              ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.drum_rot_pos"),			TowWinchStbBool17, "drum_rot_pos             ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.drum_rpm"),  			TowWinchStbBool18, "drum_rpm                 ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.wire_layer"),			TowWinchStbBool19, "wire_layer               ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.wire_length_on_drum"),	TowWinchStbBool20, "wire_length_on_drum      ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.wire_length_paid_out"),	TowWinchStbBool21, "wire_length_paid_out     ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.wire_speed"),			TowWinchStbBool22, "wire_speed               ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.wire_turn"),			TowWinchStbBool23, "wire_turn                ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStb.wire_turn_total"),		TowWinchStbBool24, "wire_turn_total          ");
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStbSpooler.cmd_move_port_port"),	TowWinchStbBool25, "cmd_move_port_port     "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStbSpooler.cmd_move_port_stb"),		TowWinchStbBool26, "cmd_move_port_stb        "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStbSpooler.cmd_move_stb_port"),		TowWinchStbBool27, "cmd_move_stb_port        "); 
        h.assignListToChart(DataManager.getFloatList("RRTowWinchStbSpooler.cmd_move_stb_stb"),		TowWinchStbBool28, "cmd_move_stb_stb         "); 
	}

	private void updateSHWinch() {
		//SW Winch Big Graph
        List<LinkedList<Float>> SHLL = new ArrayList<LinkedList<Float>>();
        List<String> SHList = new ArrayList<String>();
        SHLL.add(DataManager.getFloatList("RRSHWinch.brake_on"));
        SHList.add("brake_on");
        SHLL.add(DataManager.getFloatList("RRSHWinch.coupling_in"));
        SHList.add("coupling_in");
        SHLL.add(DataManager.getFloatList("RRSHWinch.drum_ang_vel"));
        SHList.add("drum_ang_vel");
        SHLL.add(DataManager.getFloatList("RRSHWinch.drum_radius"));
        SHList.add("drum_radius");
        SHLL.add(DataManager.getFloatList("RRSHWinch.drum_rot_pos"));
        SHList.add("drum_rot_pos");
        SHLL.add(DataManager.getFloatList("RRSHWinch.drum_rpm"));
        SHList.add("drum_rpm");
        SHLL.add(DataManager.getFloatList("RRSHWinch.wire_layer"));
        SHList.add("wire_layer");
        SHLL.add(DataManager.getFloatList("RRSHWinch.wire_length_on_drum"));
        SHList.add("wire_length_on_drum");
        SHLL.add(DataManager.getFloatList("RRSHWinch.wire_length_paid_out"));
        SHList.add("wire_length_paid_out");
        SHLL.add(DataManager.getFloatList("RRSHWinch.wire_speed"));
        SHList.add("wire_speed");
        SHLL.add(DataManager.getFloatList("RRSHWinch.wire_turn"));
        SHList.add("wire_turn");
        SHLL.add(DataManager.getFloatList("RRSHWinch.wire_turn_total"));
        SHList.add("wire_turn_total");
        h.assignListToChart(SHLL, SHGraph, SHList);
        //SHWinch Sent Values
        h.assignListToChart(DataManager.getFloatList("SHWinch.cmd_brake_on"),				SHWinchBool1,  "cmd_brake_on             ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.cmd_speed"),					SHWinchBool2,  "cmd_speed                ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.init_wire_length_paid_out"),	SHWinchBool3,  "init_wire_length_paid_out");
        h.assignListToChart(DataManager.getFloatList("SHWinch.init_total_wire_length"),		SHWinchBool4,  "init_total_wire_length   ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.reset_wirelengths"),			SHWinchBool5,  "reset_wirelengths        ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.wire_diameter"),				SHWinchBool6,  "wire_diameter            ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.wire_weigth"),				SHWinchBool7,  "wire_weigth              ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.wire_tension"),				SHWinchBool8,  "wire_tension             ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.active_drum"),				SHWinchBool9,  "active_drum              ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.config_kabelar_number"),		SHWinchBool10, "config_kabelar_number    ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.config_kabelar_typeid"),		SHWinchBool11, "config_kabelar_typeid    ");
        h.assignListToChart(DataManager.getFloatList("SHWinch.cmd_config_kabelar"),			SHWinchBool12, "cmd_config_kabelar       ");
        //SHWinch Received Values
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.brake_on"),  			SHWinchBool13, "brake_on                 ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.coupling_in"), 			SHWinchBool14, "coupling_in              ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.drum_ang_vel"),			SHWinchBool15, "drum_ang_vel             ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.drum_radius"),			SHWinchBool16, "drum_radius              ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.drum_rot_pos"),			SHWinchBool17, "drum_rot_pos             ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.drum_rpm"),  			SHWinchBool18, "drum_rpm                 ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.wire_layer"),			SHWinchBool19, "wire_layer               ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.wire_length_on_drum"),	SHWinchBool20, "wire_length_on_drum      ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.wire_length_paid_out"),	SHWinchBool21, "wire_length_paid_out     ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.wire_speed"),			SHWinchBool22, "wire_speed               ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.wire_turn"),			SHWinchBool23, "wire_turn                ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinch.wire_turn_total"),		SHWinchBool24, "wire_turn_total          ");
        h.assignListToChart(DataManager.getFloatList("RRSHWinchSpooler.cmd_move_port_port"),	SHWinchBool25, "cmd_move_port_port       "); 
        h.assignListToChart(DataManager.getFloatList("RRSHWinchSpooler.cmd_move_port_stb"),		SHWinchBool26, "cmd_move_port_stb        "); 
        h.assignListToChart(DataManager.getFloatList("RRSHWinchSpooler.cmd_move_stb_port"),		SHWinchBool27, "cmd_move_stb_port        "); 
        h.assignListToChart(DataManager.getFloatList("RRSHWinchSpooler.cmd_move_stb_stb"),		SHWinchBool28, "cmd_move_stb_stb         "); 
	}

	private void updateAHWinch() {
		//AH Winch Big Graph
        List<LinkedList<Float>> AHLL = new ArrayList<LinkedList<Float>>();
        List<String> ahList = new ArrayList<String>();
        AHLL.add(DataManager.getFloatList("RRAHWinch.brake_on"));
        ahList.add("brake_on");
        AHLL.add(DataManager.getFloatList("RRAHWinch.coupling_in"));
        ahList.add("coupling_in");
        AHLL.add(DataManager.getFloatList("RRAHWinch.drum_ang_vel"));
        ahList.add("drum_ang_vel");
        AHLL.add(DataManager.getFloatList("RRAHWinch.drum_radius"));
        ahList.add("drum_radius");
        AHLL.add(DataManager.getFloatList("RRAHWinch.drum_rot_pos"));
        ahList.add("drum_rot_pos");
        AHLL.add(DataManager.getFloatList("RRAHWinch.drum_rpm"));
        ahList.add("drum_rpm");
        AHLL.add(DataManager.getFloatList("RRAHWinch.wire_layer"));
        ahList.add("wire_layer");
        AHLL.add(DataManager.getFloatList("RRAHWinch.wire_length_on_drum"));
        ahList.add("wire_length_on_drum");
        AHLL.add(DataManager.getFloatList("RRAHWinch.wire_length_paid_out"));
        ahList.add("wire_length_paid_out");
        AHLL.add(DataManager.getFloatList("RRAHWinch.wire_speed"));
        ahList.add("wire_speed");
        AHLL.add(DataManager.getFloatList("RRAHWinch.wire_turn"));
        ahList.add("wire_turn");
        AHLL.add(DataManager.getFloatList("RRAHWinch.wire_turn_total"));
        ahList.add("wire_turn_total");
        h.assignListToChart(AHLL, AHGraph, ahList);
        //AHWinch Sent Values
        h.assignListToChart(DataManager.getFloatList("AHWinch.cmd_brake_on"),				AHWinchBool1,  		"cmd_brake_on             ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.cmd_speed"),					AHWinchBool2, 		"cmd_speed                ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.init_wire_length_paid_out"),	AHWinchBool3,  		"init_wire_length_paid_out");
        h.assignListToChart(DataManager.getFloatList("AHWinch.init_total_wire_length"),		AHWinchBool4,  		"init_total_wire_length   ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.reset_wirelengths"),			AHWinchBool5,  		"reset_wirelengths        ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.wire_diameter"),				AHWinchBool6,  		"wire_diameter            ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.wire_weigth"),				AHWinchBool7, 		"wire_weigth              ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.wire_tension"),				AHWinchBool8,  		"wire_tension             ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.active_drum"),				AHWinchBool9,  		"active_drum              ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.config_kabelar_number"),		AHWinchBool10, 		"config_kabelar_number    ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.config_kabelar_typeid"),		AHWinchBool11, 		"config_kabelar_typeid    ");
        h.assignListToChart(DataManager.getFloatList("AHWinch.cmd_config_kabelar"),			AHWinchBool12, 		"cmd_config_kabelar       ");
        //AHWinch Received Values
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.brake_on"),  				AHWinchBool13, 		"brake_on                 ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.coupling_in"), 				AHWinchBool14, 		"coupling_in              ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.drum_ang_vel"),				AHWinchBool15, 		"drum_ang_vel             ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.drum_radius"),				AHWinchBool16, 		"drum_radius              ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.drum_rot_pos"),				AHWinchBool17, 		"drum_rot_pos             ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.drum_rpm"),  				AHWinchBool18, 		"drum_rpm                 ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.wire_layer"),				AHWinchBool19, 		"wire_layer               ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.wire_length_on_drum"),		AHWinchBool20, 		"wire_length_on_drum      ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.wire_length_paid_out"),		AHWinchBool21, 		"wire_length_paid_out     ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.wire_speed"),				AHWinchBool22, 		"wire_speed               ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.wire_turn"),				AHWinchBool23, 		"wire_turn                ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinch.wire_turn_total"),			AHWinchBool24, 		"wire_turn_total          ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinchSpooler.cmd_move_port_port"),	AHWinchBool25, 	"cmd_move_port_port       ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinchSpooler.cmd_move_port_stb"),		AHWinchBool26, 	"cmd_move_port_stb        ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinchSpooler.cmd_move_stb_port"),		AHWinchBool27, 	"cmd_move_stb_port        ");
        h.assignListToChart(DataManager.getFloatList("RRAHWinchSpooler.cmd_move_stb_stb"),		AHWinchBool28, 	"cmd_move_stb_stb         ");
	}

	private void updateMisc() {
		//Misc Big Graph
        List<LinkedList<Float>> miscLL = new ArrayList<LinkedList<Float>>();
        List<String> miscNameList = new ArrayList<String>();
        miscLL.add(DataManager.getFloatList("RRSJTPPanel.signal_on"));
        miscNameList.add("signal_on");
        miscLL.add(DataManager.getFloatList("RRSJTPPanel.cmd_popup_port"));
        miscNameList.add("cmd_popup_port");
        miscLL.add(DataManager.getFloatList("RRSJTPPanel.cmd_popup_stbd"));
        miscNameList.add("cmd_popup_stbd");
        miscLL.add(DataManager.getFloatList("RRSJTPPanel.ind_oil_temp"));
        miscNameList.add("ind_oil_temp");
        h.assignListToChart(miscLL, miscGraph, miscNameList);
        
        //Misc Sent Values
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_1_cent_port"),  	miscBool1,  "cmd_1_cent_port          ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_1_cent_stbd"),   	miscBool2,  "cmd_1_cent_stbd          ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_1_sharkjaw"),  	miscBool3,  "cmd_1_sharkjaw           ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_1_tp_port"),   	miscBool4,  "cmd_1_tp_port            ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_1_tp_port_arm"),	miscBool5,  "cmd_1_tp_port_arm        ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_1_tp_stbd"),		miscBool6,  "cmd_1_tp_stbd            ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_1_tp_stbd_arm"),	miscBool7,  "cmd_1_tp_stbd_arm        ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_2_cent_port"),   	miscBool8,  "cmd_2_cent_port          ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_2_cent_stbd"),  	miscBool9,  "cmd_2_cent_stbd          ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_2_sharkjaw"),   	miscBool10, "cmd_2_sharkjaw           ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_2_tp_port"),  	miscBool11, "cmd_2_tp_port            ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_2_tp_port_arm"),  miscBool12, "cmd_2_tp_port_arm        ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_2_tp_stbd"),  	miscBool13, "cmd_2_tp_stbd            ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_2_tp_stbd_arm"),  miscBool14, "cmd_2_tp_stbd_arm        ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_hpu_start"),  	miscBool15, "cmd_hpu_start            ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_hpu_stop"),   	miscBool16, "cmd_hpu_stop             ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_popup_port"),  	miscBool17, "cmd_popup_port           ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_popup_stbd"),  	miscBool18, "cmd_popup_stbd           ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.signal_on"), 			miscBool19, "signal_on                ");
        //Misc Received Values
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_load_configuration"),		miscBool20, "cmd_load_configuration   ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_pause"), 					miscBool21, "cmd_pause                ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_reset_configuration"),	miscBool22, "cmd_reset_configuration  ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.cmd_manual_override"), 		miscBool23, "cmd_manual_override      ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.reset_drums_from_towcon"), 	miscBool24, "reset_drums_from_towcon  ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.fm_servo_failure"), 			miscBool25, "fm_servo_failure         ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.ind_oil_filter"),  			miscBool26, "ind_oil_filter           ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.ind_oil_level_low"), 			miscBool27, "ind_oil_level_low        ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.ind_oil_pressure_low"),  		miscBool28, "ind_oil_pressure_low     ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.ind_lub_power_off"), 			miscBool29, "ind_lub_power_off        ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.ind_oil_temp"),  				miscBool30, "ind_oil_temp             ");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.fm_emergency_stop_invoked"),  miscBool31, "fm_emergency_stop_invoked");
        h.assignListToChart(DataManager.getFloatList("RRSJTPPanel.winch_system_config"), 		miscBool32, "winch_system_config      ");
	}

	private void updatePumpRoom() {
		//Pump Room big Graph
        List<LinkedList<Float>> pumpLL = new ArrayList<LinkedList<Float>>();
        List<String> pumpNameList = new ArrayList<String>();    // This is not a good way to do this. Feel free to change or give suggestion on better way.
        pumpLL.add(DataManager.getFloatList("RRPumpRoom.longTimeTow_active"));
        pumpNameList.add("longTimeTow_active");
        pumpLL.add(DataManager.getFloatList("RRPumpRoom.emg_active"));
        pumpNameList.add("emg_active");
        pumpLL.add(DataManager.getFloatList("PumpRoom.engineRoom_active"));
        pumpNameList.add("engineRoom_active");
        pumpLL.add(DataManager.getFloatList("PumpRoom.filterPump_running"));
        pumpNameList.add("filterPump_running");
        h.assignListToChart(pumpLL, pumpGraph, pumpNameList);
        
        //Pump Room Sent Values
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump1_start"),  pumpBool1,  "pump1_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump1_stop"),   pumpBool2,  "pump1_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump2_start"),  pumpBool3,  "pump2_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump2_stop"),   pumpBool4,  "pump2_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump3_start"),  pumpBool5,  "pump3_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump3_stop"),   pumpBool6,  "pump3_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump4_start"),  pumpBool7,  "pump4_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump4_stop"),   pumpBool8,  "pump4_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump5_start"),  pumpBool9,  "pump5_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump5_stop"),   pumpBool10, "pump5_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump6_start"),  pumpBool11, "pump6_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump6_stop"),   pumpBool12, "pump6_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump7_start"),  pumpBool13, "pump7_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump7_stop"),   pumpBool14, "pump7_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump8_start"),  pumpBool15, "pump8_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump8_stop"),   pumpBool16, "pump8_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump9_start"),  pumpBool17, "pump9_start              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump9_stop"),   pumpBool18, "pump9_stop               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump10_start"), pumpBool19, "pump10_start             ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump10_stop"),  pumpBool20, "pump10_stop              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump11_start"), pumpBool21, "pump11_start             ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump11_stop"),  pumpBool22, "pump11_stop              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump12_start"), pumpBool23, "pump12_start             ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.pump12_stop"),  pumpBool24, "pump12_stop              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.servo1_start"), pumpBool25, "servo1_start             ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.servo1_stop"),  pumpBool26, "servo1_stop              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.servo2_start"), pumpBool27, "servo2_start             ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.servo2_stop"),  pumpBool28, "servo2_stop              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.servo3_start"), pumpBool29, "servo3_start             ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.servo3_stop"),  pumpBool30, "servo3_stop              ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.emg_active"),   pumpBool31, 		"emg_active               ");
        h.assignListToChart(DataManager.getFloatList("RRPumpRoom.longTimeTow_active"), pumpBool32, 	"longTimeTow_active       ");
        //Pump Room Returned Values
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump1_running"),  pumpBool33, 	"pump1_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump2_running"),  pumpBool34, 	"pump2_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump3_running"),  pumpBool35, 	"pump3_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump4_running"),  pumpBool36, 	"pump4_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump5_running"),  pumpBool37, 	"pump5_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump6_running"),  pumpBool38, 	"pump6_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump7_running"),  pumpBool39, 	"pump7_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump8_running"),  pumpBool40, 	"pump8_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump9_running"),  pumpBool41, 	"pump9_running            ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump10_running"), pumpBool42, 	"pump10_running           ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump11_running"), pumpBool43, 	"pump11_running           ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.pump12_running"), pumpBool44, 	"pump12_running           ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.servo1_running"), pumpBool45, 	"servo1_running           ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.servo2_running"), pumpBool46, 	"servo2_running           ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.servo3_running"), pumpBool47, 	"servo3_running           ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.engineRoom_active"),  pumpBool48,"engineRoom_active        ");
        h.assignListToChart(DataManager.getFloatList("PumpRoom.filterPump_running"), pumpBool49,"filterPump_running       ");
	}
    
    @FXML
    private void startClicked (ActionEvent event)
    {
    	Thread t = new Thread() {
    	    public void run() {
    	        PcapReader reader = new PcapReader();
//    	        reader.parseFile("C:\\Users\\rwright\\Downloads\\RMB VERS Logs\\good.pcap");
    	        reader.parseFile("C:\\Users\\User\\Downloads\\good.pcap");
//    	        reader.parseFile("C:\\Users\\User\\Downloads\\winch_misbehave_130813_2.pcap");
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
    private void addLineGraph (MouseEvent event)
    {
//    	pumpLL.add((LinkedList<Float>) event.getSource()); //doesn't work
    	System.out.println("got a click");
    }
    
    @FXML
    private void btn1MinClicked (ActionEvent event)
    {
    	DataManager.setNumSamplesToKeep(30);
    }
    
    @FXML
    private void btn5MinClicked (ActionEvent event)
    {
    	DataManager.setNumSamplesToKeep(150);
    }
    
    @FXML
    private void btn10MinClicked (ActionEvent event)
    {
    	DataManager.setNumSamplesToKeep(1500);
    }

}
