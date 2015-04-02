package de.unipotsdam.cs.mm.pdfac.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureTreeRoot;

/**
 * Checks the accessibility of headlines.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class Headlines {
	
	/*
	 * The RoleMap should be checked - things like headlines (H1, H2, ...) may have other names and be only derived from them.
	 */
	private boolean debugMessages = false;
	private List<String> elements;
	private HashMap<String,Integer> structureTypes;
	
	/**
	 * Returns the number of inconsistent headlines.
	 * @param document the PDDocument of the file
	 * @return number of inconsistencies; <code>-1</code>, otherwise
	 */
	public int countHeadlineConsistenceError(PDDocument document) {
		PDDocumentCatalog docCatalog = document.getDocumentCatalog();
		if (docCatalog==null) {
			return -1;
		}
		PDStructureTreeRoot treeRoot = docCatalog.getStructureTreeRoot();
		if (treeRoot==null) {
			return -1;
		}
		if (structureTypes==null) {
			initStructureTypes();
		}
		elements = new ArrayList<String>();
		flatten(treeRoot);
		if (debugMessages) printElements();

		return countConsistenceError();
	}

	private void printElements() {
		for (String element : elements) {
			System.out.println(element);
		}
	}
	
	private int countConsistenceError() {
		int errors=0;
		int level=0;
		for (String element : elements) {
			if(level+1<structureTypes.get(element)) {
				errors++;
			}
			level = structureTypes.get(element);
		}
		return errors;
	}

	private void flatten(PDStructureTreeRoot treeRoot) {
		for (Object kid : treeRoot.getKids()) {
        	if (kid instanceof PDStructureElement) {
        		flatten((PDStructureElement)kid);
        	}
        }
	}

	private void flatten(PDStructureElement se) {
		String type = se.getStandardStructureType();
		if (structureTypes.containsKey(type)) {
			elements.add(type);
		}
		for (Object kid : se.getKids()) {
        	if (kid instanceof PDStructureElement) {
        		flatten((PDStructureElement)kid);
        	}
        }
	}
	
	private void initStructureTypes() {
		// taken from pdf-reference 1.7 pp. 899
		structureTypes = new HashMap<String, Integer>();
		structureTypes.put("Sect", 0);
		structureTypes.put("Document", 0);
		structureTypes.put("H1", 1);
		structureTypes.put("H2", 2);
		structureTypes.put("H3", 3);
		structureTypes.put("H4", 4);
		structureTypes.put("H5", 5);
		structureTypes.put("H6", 6);
	}
}
