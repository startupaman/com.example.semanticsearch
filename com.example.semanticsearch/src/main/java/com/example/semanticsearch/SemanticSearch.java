package com.example.semanticsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import org.apache.xerces.parsers.SAXParser;

public class SemanticSearch implements ContentHandler, QueryTermTypes,
		ErrorHandler {

	private String vendorParserClass = "org.apache.xerces.parsers.SAXParser";
	private Query query;
	private String documentStreamUri[];
	private QueryTerm array[];
	@SuppressWarnings("unused")
	private Locator locator;
	private Map<String, String> namespaceMappings = new HashMap<>();
	private Stack<ProcessingNode> stack;
	private Vector<ResultNode> resultSet = new Vector<>();

	// Constructor is Used to register XMLReader,Content Handler,Error Handler
	// Initialize Query,QueryTerm Array,Document Stream URI
	public SemanticSearch(Query query, String documentStreamUri[])
			throws SAXException, IOException {
		this.documentStreamUri = documentStreamUri;
		this.query = query;
		array = query.getQueryTermArray();
		XMLReader reader = XMLReaderFactory.createXMLReader(vendorParserClass);
		reader.setContentHandler(this);
		reader.setErrorHandler(this);
		for (int i = 0; i < this.documentStreamUri.length; i++) {
			InputSource inputSource = new InputSource(documentStreamUri[i]);
			reader.parse(inputSource);
		}
	}

	@Override
	// Used to Receive Contents between two Elements i.e.
	// <Element>Content</Element>
	// Compares TYPE2,TYPE3,TYPE4 Query Term with Contents
	public void characters(char ch[], int start, int length)
			throws SAXException {
		String data = new String(ch, start, length);
		data = data.trim();
		data = data.toLowerCase();
		if (data.equals("")) {
		} else {
			for (int i = 0; i < Query.getQueryCount(); i++) {
				if ((array[i].getQueryTermType() == TYPE3)
						|| (array[i].getQueryTermType() == TYPE4)) {
					if (data.contains(array[i].getToken())) {
						(stack.peek()).setBitMap(i);
					}
				}

				if (array[i].getQueryTermType() == TYPE1) {
					if (data.contains(array[i].getToken())
							&& (array[i].getLabel()).equalsIgnoreCase((stack
									.peek()).getLocalName())) {
						(stack.peek()).setBitMap(i);
					}
				}
			}
		}
	}

	@Override
	// Prints the ending of Documents
	public void endDocument() throws SAXException {
		System.out.println("Document Ending");
	}

	@Override
	// Invoked when Element ends
	// Put ProcessedNode in ResultNode
	public void endElement(String namespaceUri, String localName, String qName)
			throws SAXException {
		ProcessingNode node = stack.pop();
		node.getBitMapStatus();
		if (stack.empty() == false) {
			if (node.getBitMapStatus() == true) {
				ResultNode resultNode = new ResultNode(node.getLocalName());
				resultSet.add(resultNode);
			} else {
				byte bitMap[] = node.getBitMap();
				for (int i = 0; i < Query.getQueryCount(); i++) {
					if (bitMap[i] == 1) {
						(stack.peek()).setBitMap(i);
					}
				}
			}
		} else {
			System.out.println("Stack Empty");
			System.out.println("Waiting For Next Document");
		}

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		for (Iterator<String> i = namespaceMappings.keySet().iterator(); i
				.hasNext();) {
			String uri = (String) i.next();
			String thisPrefix = (String) namespaceMappings.get(uri);
			if (prefix.equals(thisPrefix)) {
				namespaceMappings.remove(uri);
				break;
			}
		}
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
	}

	@Override
	// Prints the starting of Documents
	public void startDocument() throws SAXException {
		System.out.println("Document Starting");
		stack = new Stack<>();
	}

	@Override
	// Invoked when Elements starts
	// Compares TYPE2,TYPE4 Query Terms with Elements(Labels)
	// Compares TYPE1,TYPE2,TYPE3,TYPE4 with Attributes Names and Values
	public void startElement(String namespaceUri, String localName,
			String qName, Attributes atts) throws SAXException {
		if (namespaceUri.length() > 0) {
			String prefix = (String) namespaceMappings.get(namespaceUri);
			if (prefix == null) {
				prefix = "[NONE]";
			}
		}

		ProcessingNode node = new ProcessingNode(localName);
		for (int i = 0; i < Query.getQueryCount(); i++) {
			if ((array[i].getQueryTermType() == TYPE2)
					|| (array[i].getQueryTermType() == TYPE4)) {
				if (localName.equalsIgnoreCase(array[i].getLabel())) {
					node.setBitMap(i);
				}
			}
		}
		stack.push(node);

		for (int i = 0; i < atts.getLength(); i++) {
			String attsUri = atts.getURI(i);
			if (attsUri.length() > 0) {
				String attsPrefix = (String) namespaceMappings.get(attsUri);
				if (attsPrefix == null) {
					attsPrefix = "[NONE]";
				}
			}
		}

		for (int i = 0; i < atts.getLength(); i++) {
			for (int j = 0; j < Query.getQueryCount(); j++) {
				if ((array[j].getQueryTermType() == TYPE3)
						|| (array[j].getQueryTermType() == TYPE4)) {
					if ((atts.getValue(i).toLowerCase()).contains(array[j]
							.getToken())) {
						(stack.peek()).setBitMap(j);
					}
				}
				if (array[j].getQueryTermType() == TYPE2) {
					if (atts.getLocalName(i).equalsIgnoreCase(
							array[j].getLabel())) {
						(stack.peek()).setBitMap(j);
					}
				}
				if (array[j].getQueryTermType() == TYPE1) {
					if (atts.getLocalName(i).equalsIgnoreCase(
							array[j].getLabel())
							&& (atts.getValue(i).toLowerCase())
									.contains(array[j].getToken())) {
						(stack.peek()).setBitMap(j);
					}
				}
			}
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		namespaceMappings.put(prefix, uri);
	}

	// Prints the Result Vector
	public void printResultSet() {
		System.out.println("Result :");
		for (int i = 0; i < resultSet.size(); i++) {
			System.out.println("Node :" + (resultSet.get(i).getLocalName()));
		}

	}

	@Override
	// Invoked when error occurs
	// These Error will not stop parsing process
	// To do so Remove "throw new SAXExceptio(Message);"
	public void error(SAXParseException exception) throws SAXException {
		int columnNo = exception.getColumnNumber();
		int lineNo = exception.getLineNumber();
		String publicID = "Public ID :" + exception.getPublicId();
		String systemID = "System ID :" + exception.getSystemId();
		String errorMessage = "Message :" + exception.getMessage();
		String heading = "Error in Parsing the Document";
		String Message = (heading + "\nColumn No :" + columnNo + "\nLine No :"
				+ lineNo + "\n" + publicID + "\n" + systemID + "\n" + errorMessage);
		throw new SAXException(Message);
	}

	@Override
	// Invoked when Fatal Error Occurs
	// These Errors will stop Parsing Process
	public void fatalError(SAXParseException exception) throws SAXException {
		int columnNo = exception.getColumnNumber();
		int lineNo = exception.getLineNumber();
		String publicID = "Public ID :" + exception.getPublicId();
		String systemID = "System ID :" + exception.getSystemId();
		String fatalErrorMessage = "Message :" + exception.getMessage();
		String heading = "Fatal Error in Parsing the Document";
		String Message = (heading + "\nColumn No :" + columnNo + "\nLine No :"
				+ lineNo + "\n" + publicID + "\n" + systemID + "\n" + fatalErrorMessage);
		throw new SAXException(Message);
	}

	@Override
	// Invoked when warning arises
	// These Warning will not stop parsing process
	// To do so Remove "throw new SAXExceptio(Message);"
	public void warning(SAXParseException exception) throws SAXException {
		int columnNo = exception.getColumnNumber();
		int lineNo = exception.getLineNumber();
		String publicID = "Public ID :" + exception.getPublicId();
		String systemID = "System ID :" + exception.getSystemId();
		String warningMessage = "Message :" + exception.getMessage();
		String heading = "Warning in Parsing the Document";
		String Message = (heading + "\nColumn No :" + columnNo + "\nLine No :"
				+ lineNo + "\n" + publicID + "\n" + systemID + "\n" + warningMessage);
		throw new SAXException(Message);
	}
}
