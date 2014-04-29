package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class DataManager {

	private static HashMap<String, LinkedList<Boolean>> booleanMap = new HashMap<String, LinkedList<Boolean>>();
	private static HashMap<String, LinkedList<Double>> doubleMap = new HashMap<String, LinkedList<Double>>();
	
	private static Random random = new Random();
	
	public static synchronized LinkedList<Boolean> getBooleanList(String name) {
		return booleanMap.get(name);
	}
	
	public static synchronized LinkedList<Double> getDoubleList(String name) {
		return doubleMap.get(name);
	}
	
	
	public static synchronized void initDummyData(int numberOfSamples) {
		for(int i = 0; i < 10; i++) {
			String bName = "bool" + i;
			String dName = "double" + i;
			LinkedList<Boolean> bList = new LinkedList<Boolean>();
			LinkedList<Double> dList = new LinkedList<Double>();
			
			bList.add(false);
			
			for(int j = 1; j < numberOfSamples; j++) {
				double val = random.nextDouble();
				if(val > 0.98)
					bList.add(!bList.getLast());		// 2% chance of a change
				else
					bList.add(bList.getLast());			// 98% chance to stay the same
				
				dList.add(val * 60);			// Value between 0 and 60
			}
			
			booleanMap.put(bName, bList);
			doubleMap.put(dName, dList);
		}
	}
	
	public static synchronized void updateDummyData(int numberOfSamples) {
		for(int i = 0; i < 10; i++) {
			String bName = "bool" + i;
			String dName = "double" + i;
			LinkedList<Boolean> bList = booleanMap.get(bName);
			LinkedList<Double> dList = doubleMap.get(dName);
			
			bList.add(false);
			
			for(int j = 0; j < 10; j++) {
				double val = random.nextDouble();
				if(val > 0.98)
					bList.add(!bList.getLast());		// 2% chance of a change
				else
					bList.add(bList.getLast());			// 98% chance to stay the same
				
				dList.add(val * 60);			// Value between 0 and 60
				
				bList.removeFirst();
				dList.removeFirst();
			}
			
		}
	}
	
	public static void main(String[] args) {
		initDummyData(30);				//Just do 30 samples for this test
		LinkedList<Double> testDoubles = getDoubleList("double1");
		for(Double iter : testDoubles)
			System.out.print(iter + "\t ");
		System.out.println("");			//Just print an empty line.
		updateDummyData(10);			//Update 10 new samples
		
		for(Double iter : testDoubles)
			System.out.print(iter + "\t ");
		System.out.println("");			//Just print an empty line.
	}
	
}
