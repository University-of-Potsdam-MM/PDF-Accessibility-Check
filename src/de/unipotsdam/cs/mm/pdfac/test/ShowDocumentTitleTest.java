package de.unipotsdam.cs.mm.pdfac.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unipotsdam.cs.mm.pdfac.check.Check;

/**
 * JUnit test for check criterion "is document tile shown".
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class ShowDocumentTitleTest {
	private String pathToPDFs = "src"+File.separator+"de"+File.separator+"unipotsdam"+File.separator+"cs"+File.separator+"mm"+File.separator+"pdfac"+File.separator+"test"+File.separator+"pdfs"+File.separator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testShowDocumentTitle_FalseWord(){
		String pdfFile = pathToPDFs+"Word_ueberschriften_Acrobat.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = Check.isDocumentTitleShown(document);
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testShowDocumentTitle_TrueLatex(){
		String pdfFile = pathToPDFs+"BspLatex2.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = Check.isDocumentTitleShown(document);
			document.close();
			assertEquals(true, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testShowDocumentTitle_FalseLatex(){
		String pdfFile = pathToPDFs+"BspLatex.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = Check.isDocumentTitleShown(document);
			document.close();
			assertEquals(false, actual);
		} catch (IOException e) {
			fail("File not found! "+pdfFile);
		}
	}
}
