package de.unipotsdam.cs.mm.pdfac;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import de.unipotsdam.cs.mm.pdfac.json.CheckCriterion;
import de.unipotsdam.cs.mm.pdfac.json.CheckCriterionDescriptionName;
import de.unipotsdam.cs.mm.pdfac.json.CheckCriterionDescriptionType;
import de.unipotsdam.cs.mm.pdfac.json.CheckCriterionType;

/**
 * Utilityclass containing methods to get criterion names and the corresponding datatypes.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class CriterionInfos {
		
	/**
	 * Returns all check criterion names.
	 * @param locale a {@link java.util.Locale} object which contains a language identifier
	 * @return a {@link CheckCriterion} object containing the names in the chosen language
	 */
	public CheckCriterion getCheckCriterionNames(Locale locale){
		ResourceBundle labels = ResourceBundle.getBundle("de.unipotsdam.cs.mm.pdfac.i18n.criterionNames", locale);
		CheckCriterion cc = new CheckCriterion();
		List<CheckCriterionDescriptionName> criterionDescriptionName = new ArrayList<CheckCriterionDescriptionName>();
		for (CriterionDB criterionDbValue : CriterionDB.values()) {
			CheckCriterionDescriptionName criterion = new CheckCriterionDescriptionName();
			criterion.setDbName(criterionDbValue.toString());
			criterion.setName(labels.getString(criterionDbValue.toString()));
			criterionDescriptionName.add(criterion);
		}
		cc.setCriterion(criterionDescriptionName);
		return cc;
	}
	
	/**
	 * Returns names and datatypes of all check criterions.
	 * @return a {@link CheckCriterionType} object
	 */
	public CheckCriterionType getCheckCriterionNamesAndType(){
		CheckCriterionType cc = new CheckCriterionType();
		List<CheckCriterionDescriptionType> criterionDescriptionType = new ArrayList<CheckCriterionDescriptionType>();
		for (CriterionDB criterionDbValue : CriterionDB.values()) {
			CheckCriterionDescriptionType criterion = new CheckCriterionDescriptionType();
			criterion.setDbName(criterionDbValue.toString());
			criterion.setType(CriterionDB.getDbType(criterionDbValue));
			criterionDescriptionType.add(criterion);
		}
		cc.setCriterion(criterionDescriptionType);
		return cc;
	}
}
