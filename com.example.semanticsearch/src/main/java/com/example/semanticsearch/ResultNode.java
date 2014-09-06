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
public class ResultNode {

	private String localName;

	public ResultNode(String localName) {
		this.localName = localName;
	}

	/**
	 * Returns the Local Name of Result Node.
	 * 
	 * @return Local Name of Result Node
	 */
	public String getLocalName() //
	{
		return localName;
	}
}
