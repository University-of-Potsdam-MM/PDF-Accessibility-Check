package de.unipotsdam.cs.mm.pdfac;

/**
 * Enum which contains all checkcriterions. The names are the same as the names in the moodle database.
 * @author Fritz Rose and Marlene Karlapp
 * 
 */
public enum CriterionDB {
	marked,
	print_document, 
	print_document_hq,
	copy,
	annotation,
	accessibility_copy,
	outlines,
	image_count,
	show_document_title,
	consistent_headlines,
	page_number,
	accessible_fonts,
	document_language_set;
	
	/**
	 * Returns the name of the datatype as a string of a given criterion
	 * @param c the criterion
	 * @return the type of the criterion
	 */
	public static String getDbType(CriterionDB c){
		switch(c) {
		case marked:
			return DBDataType.BOOLEAN.toString();
		case print_document:
			return DBDataType.BOOLEAN.toString();
		case print_document_hq:
			return DBDataType.BOOLEAN.toString();
		case copy:
			return DBDataType.BOOLEAN.toString();
		case annotation:
			return DBDataType.BOOLEAN.toString();
		case accessibility_copy:
			return DBDataType.BOOLEAN.toString();
		case outlines:
			return DBDataType.BOOLEAN.toString();
		case image_count:
			return DBDataType.INT.toString();
		case show_document_title:
			return DBDataType.BOOLEAN.toString();
		case consistent_headlines:
			return DBDataType.INT.toString();
		case page_number:
			return DBDataType.INT.toString();
		case accessible_fonts:
			return DBDataType.BOOLEAN.toString();
		case document_language_set:
			return DBDataType.BOOLEAN.toString();
		default:
			return DBDataType.ERROR.toString();
		}
	}
}
