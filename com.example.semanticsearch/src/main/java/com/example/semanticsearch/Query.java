package com.example.semanticsearch;

public class Query {

	private String query; // Used to Store the query
	private QueryTerm array[]; // Used to Store QueryTerm Array
	private static int count = 0; // Used to Store the numbers of Queries

	public Query(String query) {
		this.query = query;
		setQueryCount();
		processQuery();
	}

	private void setQueryCount() // Used to count the number of Queries
	{
		int p = -1;
		do {
			p = query.indexOf(',', p + 1);
			count++;
		} while (p != -1);
	}

	private void processQuery() // Used to divide Query in QueryTerms
	{
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

	public void printQueryTerms() // Used to print all the Query Terms(Can be
									// Deleted)
	{
		for (int i = 0; i < count; i++) {
			System.out.println("Query " + (i + 1) + "=" + array[i]);
		}
	}

	public QueryTerm[] getQueryTermArray() {
		return array;
	}

	public static int getQueryCount() // Used to return number of Query Terms
	{
		return count;
	}
}
