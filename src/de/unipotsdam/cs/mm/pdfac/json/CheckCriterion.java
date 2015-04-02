package de.unipotsdam.cs.mm.pdfac.json;

import java.util.List;

/**
 * A list of {@link CheckCriterionDescriptionName}.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class CheckCriterion {
	private List<CheckCriterionDescriptionName> Criterion;

	public List<CheckCriterionDescriptionName> getCriterion() {
		return Criterion;
	}

	public void setCriterion(List<CheckCriterionDescriptionName> criterion) {
		Criterion = criterion;
	}	
}
