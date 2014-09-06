package com.example.semanticsearch;

public class QueryTerm implements QueryTermTypes {

	private String queryTerm, label, token;
	private int queryTermType;

	private void divideQueryTerm() // Used to divide Query Term in Label and
									// Token
	{
		char target[];
		int p = queryTerm.indexOf("::", 0);
		if (p == -1) {
			target = new char[queryTerm.length()];
			queryTerm.getChars(0, queryTerm.length(), target, 0);
			label = token = new String(target);
			queryTermType = TYPE4;
		} else if (p == 0) {
			target = new char[queryTerm.length() - 2];
			queryTerm.getChars(2, queryTerm.length(), target, 0);
			label = "[NONE]";
			token = new String(target);
			queryTermType = TYPE3;
		} else if (p == queryTerm.length() - 2) {
			target = new char[queryTerm.length() - 2];
			queryTerm.getChars(0, queryTerm.length() - 2, target, 0);
			label = new String(target);
			token = "[NONE]";
			queryTermType = TYPE2;
		} else {
			target = new char[p];
			queryTerm.getChars(0, p, target, 0);
			label = new String(target);
			target = new char[queryTerm.length() - p];
			queryTerm.getChars(p + 2, queryTerm.length(), target, 0);
			token = new String(target);
			queryTermType = TYPE1;
		}
		label = label.trim();
		token = token.trim();
		label = label.toLowerCase();
		token = token.toLowerCase();
	}

	public QueryTerm(char target[]) {
		queryTerm = new String(target);
		divideQueryTerm();
	}

	public String toString() // Returns the Query Term
	{
		return queryTerm;
	}

	public int getQueryTermType() // Returns the Query Term Type
	{
		return queryTermType;
	}

	public String getLabel() // Returns the Label
	{
		return label;
	}

	public String getToken() // Returns the Token
	{
		return token;
	}
}
