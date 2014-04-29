package towcon.pcap.processor;

import java.util.HashSet;

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

		if(!outputString)
			return;
		
		
//		System.out.print(val);
//		System.out.println("");
		

		if(dumb > 0) {
			String temp = val.substring(dumb).trim();
			String[] params = temp.split(" ");
			for(String iter : params) {
				if(iter.contains("="))
					System.out.println(name + "." + iter.substring(0, iter.indexOf("=")));
				else if(iter.endsWith("/>"))
					System.out.print("");			//Do Nothing
				else
					System.out.println("No = means bad: |" + iter + "|");
			}
		}
		
		outputString = false;
	}
	
}
