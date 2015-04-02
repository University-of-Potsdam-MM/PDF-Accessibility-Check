package de.unipotsdam.cs.mm.pdfac.json;

/**
 * This class represents the relation between the database name and the type.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class CheckCriterionDescriptionType {
	private String dbName; //database name
	private String type; //type of the database entry
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
