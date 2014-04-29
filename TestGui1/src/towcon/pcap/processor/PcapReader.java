package towcon.pcap.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.sourceforge.jpcap.net.LinkLayers;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.PacketFactory;
import net.sourceforge.jpcap.net.TCPPacket;

public class PcapReader {
	
	private XmlProcessor input = new XmlProcessor();
	private XmlProcessor output = new XmlProcessor();
	
	private void parseFile(String fileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(fileName));
			
			byte[] fileHeader = new byte[24];
			int bytesRead = fis.read(fileHeader, 0, fileHeader.length);
			if(bytesRead < fileHeader.length)
				return;
			
			while(true) {
				
				byte[] packetHeader = new byte[16];
				bytesRead = fis.read(packetHeader, 0, packetHeader.length);
				if(bytesRead != packetHeader.length)
					return;
				
				int packetLength = getPacketLength(packetHeader);
				byte[] data = new byte[packetLength];
				bytesRead = fis.read(data, 0, data.length);
				if(bytesRead != data.length)
					return;
				
				Packet p = PacketFactory.dataToPacket(LinkLayers.EN10MB, data);
//				System.out.println(p);
				
				if(p instanceof TCPPacket) {
					processPacket((TCPPacket)p);
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
	
	private void processPacket(TCPPacket p) {
//		if(p.getSourcePort() != 5678)
//			return;
		if(p.getData().length < 10)
			return;
		
//		if(p.getSourcePort() == 5678)
//			input.addData(p.getData());
		if(p.getSourcePort() == 1837)
			output.addData(p.getData());
	}

	private int getPacketLength(byte[] packetHeader) {
		int ret = 0;
		for(int i = 0; i < 4; i++) {
			int curByte = (packetHeader[8 + i] & 0x000000ff);
			ret |= curByte << (i * 8);
		}
		return ret;
	}

	public static void main(String[] args) {
		PcapReader reader = new PcapReader();
		reader.parseFile("C:\\Users\\rwright\\Downloads\\RMB VERS Logs\\winch_misbehave_130813_2.pcap");
	}
	
}
