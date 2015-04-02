/**
 * 
 */
package de.unipotsdam.cs.mm.pdfac.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unipotsdam.cs.mm.pdfac.check.PermissionCheck;

/**
 * JUnit test for check criterion "accessible permissions".
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class PermissionCheckTest {
	public String pathToPDFs = "src"+File.separator+"de"+File.separator+"unipotsdam"+File.separator+"cs"+File.separator+"mm"+File.separator+"pdfac"+File.separator+"test"+File.separator+"pdfs"+File.separator;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testPermissionsCopyFalse_LibreOffice(){
		String pdfFile = pathToPDFs+"nichtKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isCopyingAllowed_workaround(document); 
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsCopyTrue_Word(){
		String pdfFile = pathToPDFs+"Word_ueberschriften_Acrobat.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isCopyingAllowed_workaround(document); 
			document.close();
			assertEquals(true, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsCopyFalse2_LibreOffice(){
		String pdfFile = pathToPDFs+"barrierefreiesKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isCopyingAllowed_workaround(document);
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsAccessibilityCopyFalse2_LibreOffice(){
		String pdfFile = pathToPDFs+"barrierefreiesKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isAccessibilityCopyingAllowed_workaround(document);
			document.close();
			assertEquals(true, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsPrintFalse_LibreOffice(){
		String pdfFile = pathToPDFs+"nichtKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isPrintingAllowed_workaround(document);
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}

	@Test
	public void testPermissionsPrintDegradedFalse_LibreOffice(){
		String pdfFile = pathToPDFs+"nichtKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isHighQualityPrintingAllowed_workaround(document);
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsPrintDegradedFalse2_LibreOffice(){
		String pdfFile = pathToPDFs+"barrierefreiesKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isHighQualityPrintingAllowed_workaround(document);
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsPrintTrue_LibreOffice(){
		String pdfFile = pathToPDFs+"barrierefreiesKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isPrintingAllowed_workaround(document);
			document.close();
			assertEquals(true, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsAnnotaionTrue_LibreOffice(){
		String pdfFile = pathToPDFs+"barrierefreiesKopieren.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isAnnontatingAllowed_workaround(document);
			document.close();
			assertEquals(true, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testPermissionsAnnotaionFalse_LibreOffice(){
		String pdfFile = pathToPDFs+"nichtAnnotierbar.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = PermissionCheck.isAnnontatingAllowed_workaround(document);
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
}
