/**
 * Author : Aman Gupta
 * Email Id : aman.tit10@gmail.com
 * Project : Semantic Search over XML Documents.
 */

package com.example.semanticsearch;

/**
 * @author Aman Gupta (aman.tit10@gmail.com)
 * @version 1.0
 */
public class ProcessingNode {

	private String localName;
	private byte bitMap[] = new byte[Query.getQueryCount()];

	public ProcessingNode(String localName) {
		this.localName = localName;
	}

	/**
	 * Used to set BitMap according to Query Terms.
	 * 
	 * @param i
	 */
	public void setBitMap(int i) {
		bitMap[i] = 1;
	}

	/**
	 * Returns the Local Name.
	 * 
	 * @return Local Name
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * Returns the BitMap Status.
	 * 
	 * @return Bitmap Status
	 */
	public boolean getBitMapStatus() {
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

	/**
	 * Returns the BitMap
	 * 
	 * @return BitMap
	 */
	public byte[] getBitMap() {
		return bitMap;
	}
}
