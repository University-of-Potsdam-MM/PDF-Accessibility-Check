package de.unipotsdam.cs.mm.pdfac.json;

/**
 * This class represents the relation between the database name and the localized name.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class CheckCriterionDescriptionName {
	private String dbName; //database name
	private String name; //localized name
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
