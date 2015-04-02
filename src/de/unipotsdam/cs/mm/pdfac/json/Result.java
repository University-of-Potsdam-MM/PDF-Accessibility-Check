package de.unipotsdam.cs.mm.pdfac.json;

import de.unipotsdam.cs.mm.pdfac.CriterionDB;

/**
 * This class represents the result of a check criterion.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class Result {
	private CriterionDB dbName;
	private boolean useCount;
	private boolean success;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	public CriterionDB getDbName() {
		return dbName;
	}
	public void setDbName(CriterionDB dbName) {
		this.dbName = dbName;
	}
	public boolean isUseCount() {
		return useCount;
	}
	public void setUseCount(boolean useCount) {
		this.useCount = useCount;
	}
}
