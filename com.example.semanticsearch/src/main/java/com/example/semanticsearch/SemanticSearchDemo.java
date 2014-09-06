/**
 * Author : Aman Gupta
 * Email Id : aman.tit10@gmail.com
 * Project : Semantic Search over XML Documents.
 */

package com.example.semanticsearch;

import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.SAXException;

/**
 * @author Aman Gupta (aman.tit10@gmail.com)
 * @version 1.0
 */
public class SemanticSearchDemo implements QueryTermTypes {

	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws SAXException, IOException {
		Scanner scanner = null;
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
			scanner = new Scanner(System.in);
			System.out.println("\t\t\t\tSemantic Search");
			System.out
					.println("Enter Query in the form of label::token(Seperated by ',') :");
			String input = scanner.nextLine();

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
		} finally {
			scanner.close();
		}

	}
}
