package de.unipotsdam.cs.mm.pdfac.json;

import java.util.List;

/**
 * A list of {@link CheckCriterionDescriptionType}.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class CheckCriterionType {
	private List<CheckCriterionDescriptionType> Criterion;

	public List<CheckCriterionDescriptionType> getCriterion() {
		return Criterion;
	}

	public void setCriterion(List<CheckCriterionDescriptionType> criterion) {
		Criterion = criterion;
	}
}
