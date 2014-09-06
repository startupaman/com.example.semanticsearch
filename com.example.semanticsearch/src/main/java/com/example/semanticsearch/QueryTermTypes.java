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
public interface QueryTermTypes {

	/**
	 * Label::Token
	 */
	int TYPE1 = 1;

	/**
	 * Label::
	 */
	int TYPE2 = 2;

	/**
	 * ::Token
	 */
	int TYPE3 = 3;

	/**
	 * Label or Token
	 */
	int TYPE4 = 4;
}
