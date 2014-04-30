package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class DataManager {

	private static HashMap<String, LinkedList<Boolean>> booleanMap = new HashMap<String, LinkedList<Boolean>>();
	private static HashMap<String, LinkedList<Double>> doubleMap = new HashMap<String, LinkedList<Double>>();
	private static HashMap<String, LinkedList<Float>> floatMap = new HashMap<String, LinkedList<Float>>();
	private static HashMap<String, LinkedList<Float>> boolfloatMap = new HashMap<String, LinkedList<Float>>();
	
	private static HashMap<String, Float> minFloatVals = new HashMap<String, Float>();
	private static HashMap<String, Float> maxFloatVals = new HashMap<String, Float>();
	
	private static Random random = new Random();
	
	public static LinkedList<Boolean> getBooleanList(String name) {
		if(!booleanMap.containsKey(name))
			booleanMap.put(name, new LinkedList<Boolean>());
		return booleanMap.get(name);
	}
	
	public static LinkedList<Double> getDoubleList(String name) {
		if(!doubleMap.containsKey(name))
			doubleMap.put(name, new LinkedList<Double>());
		return doubleMap.get(name);
	}
	
	public static LinkedList<Float> getFloatList(String name) {
		if(!floatMap.containsKey(name))
			floatMap.put(name, new LinkedList<Float>());		//Make it auto-generate if it doesn't exist.
		return floatMap.get(name);
	}
	public static LinkedList<Float> getBoolFloatList(String name) {
		if(!boolfloatMap.containsKey(name))
			boolfloatMap.put(name, new LinkedList<Float>());
		return boolfloatMap.get(name);
	}
	
	public static float addFloatSample(String name, float value) {
		float ret = -1.0f;
		LinkedList<Float> floatList = getFloatList(name);
		float min = 0.0f;
		float max = 1.0f;
		if(minFloatVals.containsKey(name))
			min = minFloatVals.get(name);
		if(maxFloatVals.containsKey(name))
			max = maxFloatVals.get(name);
		synchronized(floatList) {
			
			if(value > max) {
				rescaleAll(floatList, min, max, value);
				max = value;
				maxFloatVals.put(name, max);
			}
			if(value < min) {
				rescaleAll(floatList, min, max, value);
				min = value;
				minFloatVals.put(name, min);
			}
			
			ret = scaleFloatVal(value, min, max);
			floatList.add(ret);
			if(floatList.size() > 300)				//TODO: Make dynamic
				floatList.removeFirst();			//Limit to 300 samples
		}
		
		return ret;
	}
	
	private static void rescaleAll(LinkedList<Float> floatList, float min, float max, float value) {
		float multiplier = 1.0f;
		float offset = 0.0f;
		if(value < min) {
			multiplier = (max - min) / (max - value);
			offset = scaleFloatVal(min, value, max);		//This is what the min used to be
		}
		else if(value > max) {
			multiplier = (max - min) / (value - min);
			offset = scaleFloatVal(max, min, value);		//This is what the max used to be
		}
		else {
			return;				//No need to rescale
		}
		
		for(int i = 0; i < floatList.size(); i++) {
			float newValue = floatList.get(i) * multiplier + offset;
			newValue = Math.min(Math.max(newValue, 0.0f), 1.0f);
			floatList.set(i, newValue);
		}
	}

	private static float scaleFloatVal(float val, float min, float max) {
		return (val - min) / (max - min);
	}
	
	public static void initDummyData(int numberOfSamples) {
		for(int i = 0; i < 10; i++) {
			String bName = "bool" + i;
			String dName = "double" + i;
			String fName = "float" + i;
			String bfName = "boolfloat" + i;
			LinkedList<Boolean> bList = new LinkedList<Boolean>();
			LinkedList<Double> dList = new LinkedList<Double>();
			LinkedList<Float> fList = new LinkedList<Float>();
			LinkedList<Float> bfList = new LinkedList<Float>();
			
			bList.add(false);
			
			for(int j = 1; j < numberOfSamples; j++) {
				double val = random.nextDouble();
				if(val > 0.98)
				{
					bList.add(!bList.getLast());		// 2% chance of a change
					bfList.add(1f);
				}
				else
				{
					bList.add(bList.getLast());			// 98% chance to stay the same
					bfList.add(0f);
				}
				
				dList.add(val * 60);			// Value between 0 and 60
				fList.add((float) (val * 60));			// Value between 0 and 60
			}
			
			booleanMap.put(bName, bList);
			doubleMap.put(dName, dList);
			floatMap.put(fName, fList);
			boolfloatMap.put(bfName, bfList);
		}
	}
	
	public static void updateDummyData(int numberOfSamples) {
		for(int i = 0; i < 10; i++) {
			String bName = "bool" + i;
			String dName = "double" + i;
			String fName = "float" + i;
			String bfName = "boolfloat" + i;
			LinkedList<Boolean> bList = booleanMap.get(bName);
			LinkedList<Double> dList = doubleMap.get(dName);
			LinkedList<Float> fList = floatMap.get(fName);
			LinkedList<Float> bfList = boolfloatMap.get(bfName);
			
			bList.add(false);
			
			for(int j = 0; j < 10; j++) {
				double val = random.nextDouble();
				if(val > 0.98)
				{
					bList.add(!bList.getLast());		// 2% chance of a change
					bfList.add(1f);
				}
				else
				{
					bList.add(bList.getLast());			// 98% chance to stay the same
					bfList.add(0f);
				}
				dList.add(val * 60);			// Value between 0 and 60
				fList.add((float) (val * 60));			// Value between 0 and 60
				
				bList.removeFirst();
				dList.removeFirst();
				fList.removeFirst();
				bfList.removeFirst();
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
