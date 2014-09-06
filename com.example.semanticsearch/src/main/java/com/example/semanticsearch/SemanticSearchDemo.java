package com.example.semanticsearch;

import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.SAXException;

public class SemanticSearchDemo implements QueryTermTypes {

	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws SAXException, IOException {
		try {
			// Stream of Documents to be Supplied
			String documentStreamUri[] = {
			// Supply Document Streams Here
			// "D:\\Aman\\MARS\\Programming\\JAVA\\eclipse\\artifacts.xml"
			// "D:\\Aman\\MARS\\Programming\\JAVA\\workspace\\XMLExamples\\SpaceWarGame\\ResultSheet.xml"
			// "D:\\Aman\\MARS\\Programming\\JAVA\\workspace\\XMLExamples\\Invoice\\Invoice.xml"
			// "D:\\Aman\\MARS\\Programming\\JAVA\\SemanticSearchProject\\SemanticSearch\\TestFile2.xml"
			"D:\\Aman\\MARS\\Programming\\JAVA\\workspace\\XMLExamples\\GolfCountryClub\\golfcountryclub.xml" };

			// Taking Query Input
			Scanner kb = new Scanner(System.in);
			System.out.println("\t\t\t\tSemantic Search");
			System.out
					.println("Enter Query in the form of label::token(Seperated by ',') :");
			String input = kb.nextLine();

			// Query Object Creation
			Query query = new Query(input);

			// SemanticSearch Object Creation

			SemanticSearch semanticSearch = new SemanticSearch(query,
					documentStreamUri);
			semanticSearch.printResultSet();
		} catch (SAXException exception) {
			System.out.println("Exception :" + exception);
		} catch (IOException exception) {
			System.out.println("Exception :" + exception);
		} catch (Exception exception) {
			System.out.println("Exception :" + exception);
		}

	}
}
