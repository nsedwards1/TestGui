package towcon.pcap.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import application.DataManager;
import net.sourceforge.jpcap.net.LinkLayers;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.PacketFactory;
import net.sourceforge.jpcap.net.TCPPacket;

public class PcapReader {
	
	private XmlProcessor input = new XmlProcessor();
	private XmlProcessor output = new XmlProcessor();
	
	private long startClockTime = -1;
	private long startPcapTime = -1;
	
	public void parseFile(String fileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(fileName));
			
			byte[] fileHeader = new byte[24];
			int bytesRead = fis.read(fileHeader, 0, fileHeader.length);
			if(bytesRead < fileHeader.length)
				return;
			
			int counter = 0;
			
			while(true) {
				
				byte[] packetHeader = new byte[16];
				bytesRead = fis.read(packetHeader, 0, packetHeader.length);
				if(bytesRead != packetHeader.length)
					return;
				
				float curTime = 0.0f;		//Current time in seconds (partial seconds after decimal point
				
				long seconds = extractInt(packetHeader, 0);
				long microseconds = extractInt(packetHeader, 4);
				if(startPcapTime == -1) {
					startPcapTime = seconds * 1000 + microseconds / 1000;
					startClockTime = System.currentTimeMillis();
				}
				else {
					long pcapElapsedTime = ((seconds * 1000 + microseconds / 1000) - startPcapTime);		//in ms
					long clockElapsedTime = (System.currentTimeMillis() - startClockTime) * 5;	//in ms
					if(clockElapsedTime < pcapElapsedTime)
						sleep(pcapElapsedTime - clockElapsedTime);
					
//					counter++;
//					if(counter > 10) {
//						sleep(10);
//						counter = 0;
//					}
					
					curTime = (float)(pcapElapsedTime) / 1000.0F;
				}
				
				int packetLength = extractInt(packetHeader, 8);
				byte[] data = new byte[packetLength];
				bytesRead = fis.read(data, 0, data.length);
				if(bytesRead != data.length)
					return;
				
				Packet p = PacketFactory.dataToPacket(LinkLayers.EN10MB, data);
//				System.out.println(p);
				
				if(p instanceof TCPPacket) {
					processPacket((TCPPacket)p, curTime);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fis != null)
					fis.close();
			} catch (IOException e) {
			}
		}
	}
	
	private void sleep(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void processPacket(TCPPacket p, float curTime) {
//		if(p.getSourcePort() != 5678)
//			return;
		if(p.getData().length < 10)
			return;
		
		if(p.getSourceAddress().equals("192.168.111.13"))
			input.addData(p.getData(), curTime);
		if(p.getSourceAddress().equals("192.168.111.23"))
			output.addData(p.getData(), curTime);
	}

	private int extractInt(byte[] packetHeader, int startByte) {
		int ret = 0;
		for(int i = 0; i < 4; i++) {
			int curByte = (packetHeader[startByte + i] & 0x000000ff);
			ret |= curByte << (i * 8);
		}
		return ret;
	}

	public static void main(String[] args) {
		PcapReader reader = new PcapReader();
//		reader.parseFile("C:\\swire\\towcon_1399694024446_Crashed.pcap");
		reader.parseFile("C:\\Users\\rwright\\Downloads\\RMB VERS Logs\\good.pcap");
//		reader.parseFile("C:\\Users\\User\\Downloads\\winch_misbehave_130813_2.pcap");
		
		DataManager.printMinMaxValues();
	}
	
}
