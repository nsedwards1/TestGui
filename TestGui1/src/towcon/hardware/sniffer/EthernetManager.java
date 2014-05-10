package towcon.hardware.sniffer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import net.sourceforge.jpcap.net.LinkLayers;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.PacketFactory;
import net.sourceforge.jpcap.net.UDPPacket;

public class EthernetManager {
	
	private boolean exitFlag = false;
	private String testFileName = null;
	EthernetPacketSplitter splitter = new EthernetPacketSplitter();
	
	//This header is built from information from: http://www.kroosec.com/2012/10/a-look-at-pcap-file-format.html
	private static byte[] pcapGlobalHeader = {
		(byte) 0xd4, (byte) 0xc3, (byte) 0xb2, (byte) 0xa1,				//pcap's magic number
		0x02, 0x00, 0x04, 0x00,											//Major/minor number
		0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 				//Global time information
		(byte) 0xff, (byte) 0xff, 0x00, 0x00,							//Maximum packet length (65535)
		0x01, 0x00, 0x00, 0x00											//Link layer = Ethernet
	};


	public EthernetManager() {
	}

	public void splitRawData(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		splitter.addByteBuffer(buffer);
	}
	
	private byte[] intToByteArray(int val) {
		byte[] ret = new byte[]{
				(byte)(val),
				(byte)(val >>> 8),
				(byte)(val >>> 16),
				(byte)(val >>> 24)};
		return ret;
	}
	
	
	private void parseDataToWavs() throws IOException {
		OutputStream out = null;
		long fileOpenTime = 0;
		
		
		while(!exitFlag)
		{
			
			try {
				
				if(!"".equals(testFileName) && out == null) {
					try {
						fileOpenTime = System.currentTimeMillis();
						out = new FileOutputStream(testFileName + "_" + fileOpenTime + ".pcap");
						out.write(pcapGlobalHeader);
					} catch (IOException e1) {
						e1.printStackTrace();
						//DON'T RETURN HERE! FAILED FILE DOESN'T MEAN WE CAN'T PROCESS STUFF!!!
					}
				}
				
				
				byte[] data = splitter.getNextEthernetPacket();
				if(data == null)
				{
					try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
					continue;							//continue? Or break and return?
				}
				
				Packet p = PacketFactory.dataToPacket(LinkLayers.EN10MB, data);
				
				if(p instanceof UDPPacket)
				{
					UDPPacket udp = (UDPPacket)p;
					processPacket(udp);
				}
//				else if(p instanceof TCPPacket)
//				{
//					TCPPacket tcp = (TCPPacket)p;
//					logger.debug(tcp.toString());
//				}
				
				if(out != null) {
					int dataLength = data.length;
					long curTime = System.currentTimeMillis();
					int seconds = (int)(curTime / 1000);
					int ms = (int)(curTime % 1000);
					out.write(intToByteArray(seconds));		//Timestamp seconds
					out.write(intToByteArray(ms * 1000));			//Timestamp microSeconds (microseconds = ms * 1000)
					out.write(intToByteArray(dataLength));
					out.write(intToByteArray(dataLength));
					out.write(data);
				}
				
				if(System.currentTimeMillis() - fileOpenTime > 5 * 60 * 1000) {		//New file every 5 minutes (5*60 seconds)
					out.close();
					out = null;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		
		if(out != null) {
			try {
				out.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void processPacket(UDPPacket udp) 
	{
		try
		{
			
		}
		catch (Exception e)
		{
			
		}
	}
	
	public void start() {
		Thread t = new Thread() {
		    public void run() {
		    	try {
					parseDataToWavs();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		};
		t.setName("SIPManager Thread");
		if(t.getPriority() > Thread.MIN_PRIORITY)
			t.setPriority(t.getPriority() - 1);
//		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}

	public void flagExit() {
		exitFlag = true;
		if(splitter != null)
			splitter.interruptStreamToExit();
	}

	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}
}
