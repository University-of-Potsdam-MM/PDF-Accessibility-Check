package de.unipotsdam.cs.mm.pdfac.check;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;

import de.unipotsdam.cs.mm.pdfac.CriterionDB;
import de.unipotsdam.cs.mm.pdfac.json.Result;
import de.unipotsdam.cs.mm.pdfac.json.TestResult;

/**
 * Checks metadata information in a PDF-file for accessibility.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class Check {
	
	/**
	 * Check whether a PDF-file is marked.
	 * @param document the PDDocument of the file
	 * @return  <code>true</code> if the file is marked; <code>false</code>, otherwise
	 */
	public static boolean isMarked(PDDocument document){
		boolean marked = false;

		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null && pddc.getMarkInfo() != null){
			if(pddc.getMarkInfo().isMarked()){
				marked = true;
			}
		}

		return marked;
	}
	
	/**
	 * Check whether the PDF-file has outlines.
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if the file has outlines; <code>false</code>, otherwise
	 */
	public static boolean hasOutlines(PDDocument document){
		boolean outline = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			PDDocumentOutline pddo = pddc.getDocumentOutline();
			if(pddo != null){
				outline = true;
			}
		}
		return outline;
	}
	
	/**
	 * Check whether the title of the document is shown.
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if the title is shown; <code>false</code>, otherwise
	 */
	public static boolean isDocumentTitleShown(PDDocument document){
		boolean docTitle = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			PDViewerPreferences pdvp = pddc.getViewerPreferences();
			if(pdvp != null){
				docTitle = pdvp.displayDocTitle();
			}
		}
		return docTitle;
	}
	
	/**
	 * Check whether the language of the PDF-file is set.
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if the language set; <code>false</code>, otherwise
	 */
	public static boolean isDocumentLanguageSet(PDDocument document){
		boolean isDocLanguage = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			//RFC 3066 - http://www.i18nguy.com/unicode/language-identifiers.html; http://www.iana.org/assignments/language-tags/language-tags.xhtml; http://xml.coverpages.org/TexinUsingLangID.html
			String lang = pddc.getLanguage();
			if(lang != null && lang.length()>0){
				isDocLanguage = true;
			}
		}
		return isDocLanguage;
	}
	
	/**
	 * Returns the number of images in the PDF-file.
	 * @param document the PDDocument of the file
	 * @return number of images
	 */
	public static int getImageCount(PDDocument document) {
		int counter = 0;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if (pddc != null) {
			for (Object page : pddc.getAllPages()) {
				if (page instanceof PDPage) {
					PDPage p = (PDPage) page;
					Map<String, PDXObject> xObjects = p.getResources().getXObjects();
					for (String key : xObjects.keySet()) {
						PDXObject pdx = xObjects.get(key);
						if (pdx instanceof PDXObjectImage) {
							counter++;
						}
					}
				}
			}
		}		
		return counter;
	}
	
	/**
	 * Returns the number of pages of the PDF-file.
	 * @param document the PDDocument of the file
	 * @return number of pages
	 */
	public static int getNumberOfPages(PDDocument document){
		return document.getNumberOfPages();
	}
	
	/**
	 * Returns the accessibility test results of a PDF-file.
	 * @param pdfFile a file handle
	 * @return a {@link TestResult} object
	 */
	public static TestResult getTestResult(File pdfFile){
		TestResult tr = new TestResult();
		List<Result> testResults = new ArrayList<Result>();
		
		if(!pdfFile.exists() || !pdfFile.canRead()){
			System.out.println("exist: "+ pdfFile.exists());
			System.out.println("read: "+pdfFile.canRead());
			//TODO: set Error
			return tr;
		}
		
		try {
			PDDocument document = PDDocument.load(pdfFile);
			
			Result result = new Result();
			result.setDbName(CriterionDB.marked);
			result.setSuccess(isMarked(document));
			result.setUseCount(false);
			testResults.add(result );
			
			result = new Result();
			result.setDbName(CriterionDB.accessibility_copy);
			result.setSuccess(PermissionCheck.isAccessibilityCopyingAllowed_workaround(document));
			result.setUseCount(false);
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.copy);
			result.setSuccess(PermissionCheck.isCopyingAllowed_workaround(document));
			result.setUseCount(false);
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.annotation);
			result.setSuccess(PermissionCheck.isAnnontatingAllowed_workaround(document));
			result.setUseCount(false);
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.print_document);
			result.setSuccess(PermissionCheck.isPrintingAllowed_workaround(document));
			result.setUseCount(false);
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.print_document_hq);
			result.setSuccess(PermissionCheck.isHighQualityPrintingAllowed_workaround(document));
			result.setUseCount(false);
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.outlines);
			result.setSuccess(hasOutlines(document));
			result.setUseCount(false);
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.show_document_title);
			result.setSuccess(isDocumentTitleShown(document));
			result.setUseCount(false);
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.image_count);
			result.setUseCount(true);
			result.setCount(getImageCount(document));
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.consistent_headlines);
			result.setUseCount(true);
			Headlines hl = new Headlines();
			result.setCount(hl.countHeadlineConsistenceError(document));
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.page_number);
			result.setUseCount(true);
			result.setCount(getNumberOfPages(document));
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.accessible_fonts);
			result.setUseCount(false);
			result.setSuccess(Font.checkFonts(document));
			testResults.add(result);
			
			result = new Result();
			result.setDbName(CriterionDB.document_language_set);
			result.setUseCount(false);
			result.setSuccess(isDocumentLanguageSet(document));
			testResults.add(result);
			
			document.close();
			tr.setTestResults(testResults );
			
			return tr;
			
		} catch (IOException e) {
			// TODO set Error
			e.printStackTrace();
			return tr;
		}
	}
}
