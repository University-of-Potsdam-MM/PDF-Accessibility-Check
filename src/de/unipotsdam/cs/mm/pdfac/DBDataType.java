package de.unipotsdam.cs.mm.pdfac;

/**
 * Enum which contains the datatype of a check criterion.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public enum DBDataType {
	BOOLEAN,
	INT,
	VARCHAR,
	DATE,
	ERROR;
	
	@Override
	public String toString() {
		switch(this) {
			case BOOLEAN: return "boolean";
			case DATE: return "date";
			case INT: return "int";
			case VARCHAR: return "varchar";
			case ERROR: return "ERROR";
			default: return "ERROR";
		}
	}
}
