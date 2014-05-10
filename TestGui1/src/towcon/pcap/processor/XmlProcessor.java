package towcon.pcap.processor;

import java.util.HashSet;

import application.DataManager;

public class XmlProcessor {

	private String lastPartialString = "";
	private HashSet<String> names = new HashSet<String>();
	private boolean outputString = false;
	
	public void addData(byte[] data) {
		String currentString = new String(data);
		String[] splitter = currentString.split("\n");
		if(splitter.length > 0) {
			splitter[0] = lastPartialString + splitter[0];
			for(int i = 0; i < splitter.length; i++) {
				processString(splitter[i]);
			}
			if(splitter[splitter.length - 1].endsWith("/>"))
				lastPartialString = "";
			else
				lastPartialString = splitter[splitter.length - 1];
		}
	}
	
	private String name = "";

	private void processString(String val) {
		if(!val.endsWith("/>"))
			return;
		
		int dumb = -1;
		
		
		if(val.startsWith("<update")) {
			int start = val.indexOf(".") + 1;
			int end = val.indexOf("\"", start + 1);
			dumb = end + 1;
			name = val.substring(start, end);
			if(!names.contains(name)) {
				names.add(name);
				outputString = true;
			}
		}

//		if(!outputString)
//			return;
		
		
//		System.out.print(val);
//		System.out.println("");
		

		if(dumb > 0) {
			String temp = val.substring(dumb).trim();
			String[] params = temp.split(" ");
			for(String iter : params) {
				if(iter.contains("=")) {
					int equals = iter.indexOf("=");
					String dmName = name + "." + iter.substring(0, equals);
					
//					if("RRTowWinchPrt".equals(name)) {
//						System.out.println(dmName);
//					}
//					if(dmName.endsWith("Prt.wire_tension"))
//						System.out.println("asdf");
					
					String parseValue = iter.substring(equals + 2, iter.length() - 1);
					float dmValue = 0;
					try {
						if("false".equals(parseValue))
							dmValue = 0.0f;
						else if("true".equals(parseValue))
							dmValue = 1.0f;
						else
							dmValue = Float.parseFloat(parseValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					float scaledValue = DataManager.addFloatSample(dmName, dmValue);
//					System.out.println(iter + " ==> " + dmName + " = " + dmValue + " scaled: " + scaledValue);
				}
				else if(iter.endsWith("/>"))
					System.out.print("");			//Do Nothing
				else
					System.out.println("No = means bad: |" + iter + "|");
			}
		}
		
		outputString = false;
	}
	
}
