package towcon.hardware.sniffer;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * A stream of bits that can be read. Because they come from an underlying byte stream, the total number of bits is always a multiple of 8. The bits are read in big endian.
 */
public class EthernetPacketSplitter {
	
	byte[] preamble = { 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, (byte) 0xd5 };
	

	// Underlying byte stream to read from.
	private ByteBuffer currentBuffer;
	private long currentBufferStartIndex;
	private LinkedBlockingQueue<ByteBuffer> queue;
	ByteBuffer lastBuffer;
	private long maxBytes;
	
	LinkedList<ByteBuffer> intermediateBuffers;
	

	private boolean isEndOfStream;
	
	private long totalNumberBytesRead;
	long lastPreamble;

	public EthernetPacketSplitter() {
		byte[] dumb = new byte[0];
		currentBuffer = ByteBuffer.wrap(dumb);
		init();
	}

	// Creates a bit input stream based on the given byte input stream.
	public EthernetPacketSplitter(ByteBuffer buffer) {
		currentBuffer = buffer;
		init();
	}
	
	private void init()
	{
		intermediateBuffers = new LinkedList<ByteBuffer>();
		queue = new LinkedBlockingQueue<ByteBuffer>();
		currentBufferStartIndex = 0;
		maxBytes = 0;
		maxBytes += currentBuffer.limit();
		lastPreamble = -1;
		resetState();
	}
	
	public void addByteBuffer(ByteBuffer buffer)
	{
		maxBytes += buffer.limit();
		queue.add(buffer);
	}
	
	private void resetState()
	{
		isEndOfStream = false;
		totalNumberBytesRead = currentBufferStartIndex;
	}

	public void interruptStreamToExit()
	{
		isEndOfStream = true;
		queue.add(ByteBuffer.allocate(0));
	}

	// Reads a byte from the stream. Returns  -1 if the end of stream is reached, or blocks until next byte is available
	private int read() {
		if (isEndOfStream)
			return -1;
		
		int ret = -1;
			
		if(currentBuffer.position() < currentBuffer.capacity())
		{
			ret = currentBuffer.get();
		}
		else
		{
			try {
				lastBuffer = currentBuffer;
				currentBuffer = queue.take();
				if(currentBuffer.capacity() == 0)			//Special flag to exit
				{
					isEndOfStream = true;
					return -1;
				}
				

				if(lastPreamble < currentBufferStartIndex && lastPreamble != -1)
				{
					//This means we are skipping multiple buffers in one 'packet', thus we need to cache the intermediate data
					intermediateBuffers.add(lastBuffer);
				}
				
				currentBuffer.position(0);
				currentBufferStartIndex = totalNumberBytesRead;
				return read();
			} catch (InterruptedException e1) {
				isEndOfStream = true;
				return -1;
			}
		}
			
		totalNumberBytesRead++;
		return ret & 0xff;
	}
	
	public byte[] getNextEthernetPacket()
	{
		if(lastPreamble == -1)
			lastPreamble = findStartOfNextPacket();
		
		ByteBuffer lastPacket = currentBuffer.slice();
		while(lastPreamble != -1 && !isEndOfStream)
		{
			long thisPreamble = findStartOfNextPacket();
			if(thisPreamble == -1)
				return null;
			
			int dataLength = ((int)(thisPreamble - lastPreamble - 12 - 4));	//12 = 8 bytes for pre-amble, and there are 2 bytes before preamble and 2 bytes after preamble... Keep getting 4 bytes more than I want... Need to figure out why.
			if(dataLength <= 0)
				continue;
			byte[] packet = new byte[dataLength];
			
			if(lastPreamble > currentBufferStartIndex)			//The full packet is within this single ByteBuffer, thus we just grab it out
			{
				lastPacket.get(packet, 0, packet.length);
			}
			else			//We spanned byte buffers, so we need to get better logic (take half of packet from lastBuffer and half from currentBuffer)
			{
				if(intermediateBuffers.size() > 0)				//The data spans multiple buffers... get ready for some fun
				{
					int firstLength = lastPacket.capacity();
					try
					{
					if(dataLength <= firstLength)
					{
						//In this case, the data is actually in one single ByteBuffer, but the synchronizing data spans the previous ByteBuffer and the next ByteBuffer both.
						//Thus, just read the dataLength, and then secondLength below will be <= 0, and we will just clear intermediateBuffers.
						lastPacket.get(packet, 0, dataLength);
					}
					else
					{
						lastPacket.get(packet, 0, firstLength);
						
						for(ByteBuffer iter : intermediateBuffers)
						{
							iter.position(0);				//These intermediate buffers need to be FULLY read, so go back to 0
							int toRead = iter.remaining();
							if(dataLength < firstLength + toRead)
								toRead = dataLength - firstLength;
							iter.get(packet, firstLength, toRead);
							firstLength += toRead;
						}
					}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					int secondLength = dataLength - firstLength;
					if(secondLength > 0)			//The final gap might be spanned by synchronization information, and not actually by data
						readRestOfPacket(packet, firstLength, secondLength);
					
					intermediateBuffers.clear();
				}
				else
				{
					int firstLength = lastPacket.capacity();
					int secondLength = dataLength - firstLength;
					if(secondLength <= 0)			//The gap is being spanned by synchronization information, and not actually by data
					{
						lastPacket.get(packet, 0, dataLength);			//Don't touch second buffer's position, and don't need to read from it.
					}
					else			//The data spans the gap, thus get it from both sides
					{
						lastPacket.get(packet, 0, firstLength);
						readRestOfPacket(packet, firstLength, secondLength);
					}
				}
			}
			
			
			
			lastPreamble = thisPreamble;
			lastPacket = currentBuffer.slice();
			
			return packet;
		}
		
		return null;
	}

	private void readRestOfPacket(byte[] packet, int readThusFar, int bytesToRead) {
		int oldPosition = currentBuffer.position();			//Save the current position, then reset to 0, and reset it to oldPosition again.

		try
		{
			currentBuffer.position(0);
			currentBuffer.get(packet, readThusFar, bytesToRead);
			currentBuffer.position(oldPosition);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private long findStartOfNextPacket()
	{
		byte[] window = new byte[preamble.length];
		int numReads = 0;
		while(!isEndOfStream)
		{
			if(maxBytes < totalNumberBytesRead + 200)
			{
				try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
			}
			
			//Find the preamble
			boolean hasFoundPreamble = true;
			int valRead = read();
			if(valRead == -1)
				return -1;
			byte byteRead = (byte)(valRead & 0xff);
			numReads++;
			for(int i = 0; i < window.length - 1; i++)
			{
				window[i] = window[i+1];
				if(window[i] != preamble[i])			//All of the first bytes MUST be 0x55
					hasFoundPreamble = false;
			}
			window[window.length - 1] = byteRead;
			if(window[window.length - 1] != preamble[window.length - 1])			//All of the first bytes MUST be 0x55
				hasFoundPreamble = false;
			
			if(hasFoundPreamble)
			{
				return totalNumberBytesRead - 8;
			}
		}
		return -1;
	}
	
	public String getStats() {
		return "totalNumberBytesRead: " + totalNumberBytesRead + "\t\tqueue.size(): " + queue.size();
	}

}
