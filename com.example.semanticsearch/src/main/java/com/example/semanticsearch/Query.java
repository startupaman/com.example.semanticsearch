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
public class Query {

	/**
	 * Used to Store the query.
	 */
	private String query;

	/**
	 * Used to Store QueryTerm Array.
	 */
	private QueryTerm array[];

	/**
	 * Used to Store the numbers of Queries.
	 */
	private static int count = 0;

	public Query(String query) {
		this.query = query;
		setQueryCount();
		processQuery();
	}

	/**
	 * Used to count the number of Queries.
	 */
	private void setQueryCount() {
		int p = -1;
		do {
			p = query.indexOf(',', p + 1);
			count++;
		} while (p != -1);
	}

	/**
	 * Used to divide Query in QueryTerms.
	 */
	private void processQuery() {
		array = new QueryTerm[count];
		char target[];
		for (int i = 0, p = -1, q; i < count; i++) {
			q = p + 1;
			p = query.indexOf(',', p + 1);
			if (p == -1) {
				target = new char[query.length() - q];
				query.getChars(q, query.length(), target, 0);
			} else {
				target = new char[p - q];
				query.getChars(q, p, target, 0);
			}
			array[i] = new QueryTerm(target);
		}
	}

	/**
	 * Used to print all the Query Terms(Can be Deleted).
	 */
	public void printQueryTerms() {
		for (int i = 0; i < count; i++) {
			System.out.println("Query " + (i + 1) + "=" + array[i]);
		}
	}

	public QueryTerm[] getQueryTermArray() {
		return array;
	}

	/**
	 * Used to return number of Query Terms.
	 * 
	 * @return Query Count
	 */
	public static int getQueryCount() {
		return count;
	}
}
