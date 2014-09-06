package com.example.semanticsearch;

public class ProcessingNode {

	private String localName;
	private byte bitMap[] = new byte[Query.getQueryCount()];

	public ProcessingNode(String localName) {
		this.localName = localName;
	}

	public void setBitMap(int i) // Used to set BitMap according to Query Terms
	{
		bitMap[i] = 1;
	}

	public String getLocalName() // Returns the Local Name
	{
		return localName;
	}

	public boolean getBitMapStatus() // Returns the BitMap Status
	{
		int i;
		for (i = 0; i < bitMap.length; i++) {
			if (bitMap[i] == 0) {
				break;
			}
		}
		if (i == bitMap.length) {
			return true;
		} else {
			return false;
		}
	}

	public byte[] getBitMap() // Returns the BitMap
	{
		return bitMap;
	}
}
