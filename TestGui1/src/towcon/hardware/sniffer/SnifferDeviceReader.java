package towcon.hardware.sniffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.ftdi.BitModes;
import com.ftdi.FTD2XXException;
import com.ftdi.FTDevice;
import towcon.hardware.sniffer.EthernetManager;

public class SnifferDeviceReader {
	
	EthernetManager manager;
	boolean exitFlag = false;
	FileOutputStream fileOut;
	byte[] fileIn;
	int numBytesToRead;
	
	private final FTDevice device;
	private static String vrlDirectory;
	
	public SnifferDeviceReader(FTDevice device, boolean snifferTestMode)
	{
		this.device = device;
		
		manager = new EthernetManager();
		if(snifferTestMode)
//			manager.setTestFileName("C:\\swire\\towcon");
			manager.setTestFileName("sniffing\\towcon");
		numBytesToRead = 10;
		
		if(manager != null)
			manager.start();				//I don't like starting new threads in a constructor, so this should probably be moved to the start() method.
	}
	
	public void processFTDI()
	{
		try {
			
			//--------------------Read Data------------------------
			while(!exitFlag)
			{
				int dataRead = readDataToProcessor(device);
				
				if(dataRead <= 0)
					Thread.sleep(1);				//TODO: Parameterize this value
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			return;			//Can't do anything
		}
		
		finally
		{
			
			try {
				if(device != null)
				{
					device.close();
				}
			} catch (FTD2XXException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private int readDataToProcessor(FTDevice device) throws FTD2XXException 
	{
		
		long dataAvailable = device.getQueueStatus();			//52 bytes per sample time (2 frames = 2 * 24 bytes data + 2 * 2 bytes header/footer
		if(dataAvailable < numBytesToRead)										//1000 samples is 1/8th of a second (8000 samples per second) 10000 samples is 1.25 seconds
			return 0;
		
		int dataToRead = numBytesToRead;
		
		if(dataAvailable > 128000)
			dataToRead = 128000;
		else
			dataToRead = (int)dataAvailable;
		
		byte[] data = new byte[dataToRead];
		
		device.read(data, 0, dataToRead);
		if(manager != null)
			manager.splitRawData(data);
		else
			writeDataToFile(data);
		return dataToRead;
	}

	public void start() {
		Thread t = new Thread() {
		    public void run() {
		    	try {
			    	if(fileIn != null)
			    	{

			    		//---------------------------Send in the whole file
			    		manager.splitRawData(fileIn);
			    		
			    		
			    		//---------------------------Split the file
//			    		ByteBuffer buffer = ByteBuffer.wrap(fileIn);
//			    		//Break things into 193000 byte chunks (which is a second of audio for the 24 channels)
//			    		for(int i = 0; i < buffer.limit() / 193000 + 1; i++)
//			    		{
//			    			buffer.position(i * 193000);
//			    			ByteBuffer toAdd = buffer.slice();
//			    			byte[] subData;
//			    			if(toAdd.limit() > 193000)
//			    				subData = new byte[193000];
//			    			else
//			    				subData = new byte[toAdd.capacity()];
//			    			toAdd.get(subData, 0, subData.length);
//			    			manager.splitRawData(subData);
//			    		}
			    		
			    	}
			    	else
			    	{
			    		processFTDI();
			    	}
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		};
		t.setName("processFTDI Thread");
		if(t.getPriority() < Thread.MAX_PRIORITY)
			t.setPriority(t.getPriority() + 1);
//		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}

	public void flagExit() {
		if(manager != null)
			manager.flagExit();
		exitFlag = true;
	}
	
	private void writeDataToFile(byte[] data) {
		try {
			fileOut.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setMode(String mode) {
		switch(mode)
		{
		case "record":
			manager.flagExit();
			manager = null;
			try {
				fileOut = new FileOutputStream(new File(rawFileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "playback":
			try {
				fileIn = Files.readAllBytes(Paths.get(rawFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
//	private final String rawFileName = "C:\\temp\\E1_CALA_capture.raw";
	private final String rawFileName = "C:\\temp\\capture_unplugUSB.raw";
//	private final String rawFileName = "C:\\temp\\E1_capture_inbound.raw";
//	private final String rawFileName = "C:\\temp\\E1_capture_outbound.raw";
	
	public static void main(String[] args) throws FTD2XXException {
		
		setVrlDirectory("C:\\temp\\vrl\\");
		
		List<FTDevice> devices = null;
		FTDevice device = null;
		System.out.println("starting");
		devices = FTDevice.getDevices();
		System.out.println("Found devices: " + devices.size());
		String serialNumber = "";
		
		//---------------------Init-----------------------------
		
		for(FTDevice iter : devices)
		{
			System.out.println(iter);
			iter.open();
			serialNumber = iter.getDevSerialNumber();
			int vid = (iter.getDevID() >> 16) & 0xffff;
			int pid = (iter.getDevID()) & 0xffff;
			String description = iter.getDevDescription();
			String type = description.substring(description.indexOf('-') + 1);
			device = iter;
			if(device != null) {
				device.setBitMode((byte) 0xff, BitModes.BITMODE_SYNC_FIFO);			//Should be single channel synchronous 245 FIFO Mode, but not available in java wrapper yet
				break;
			}
		}
		
		if(device == null) {
			System.out.println("Couldn't find a sniffer");
			
			SnifferDeviceReader reader = new SnifferDeviceReader(device, false);
			reader.setMode("playback");
			reader.start();
			
			try {
				System.in.read();
				reader.flagExit();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return;
		}
		
		boolean snifferTestMode = true;
		SnifferDeviceReader reader = new SnifferDeviceReader(device, snifferTestMode);
//		reader.setMode("record");
//		reader.setMode("playback");
		reader.setMode("sniffer");			//This is the default, and doesn't need to be called.
		reader.start();
		
		try {
			System.in.read();
			reader.flagExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getVrlDirectory() {
		return vrlDirectory;
	}

	public static void setVrlDirectory(String directory) {
		if(directory.endsWith(File.separator))
			vrlDirectory = directory;
		else
			vrlDirectory = directory + File.separator;
	}
}
