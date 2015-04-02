/**
 * 
 */
package de.unipotsdam.cs.mm.pdfac.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unipotsdam.cs.mm.pdfac.check.Headlines;

/**
 * JUnit test for check criterion "accessible headline structure".
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class HeadlineTest {
	private String pathToPDFs = "src"+File.separator+"de"+File.separator+"unipotsdam"+File.separator+"cs"+File.separator+"mm"+File.separator+"pdfac"+File.separator+"test"+File.separator+"pdfs"+File.separator;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testHeadline_0ErrorWord(){
		String pdfFile = pathToPDFs+"Word_ueberschriften_Acrobat.pdf";	
		Headlines hl = new Headlines();
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			int actual = hl.countHeadlineConsistenceError(document);
			document.close();
			assertEquals(0, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testHeadline_1ErrorWordAccrobat(){
		String pdfFile = pathToPDFs+"Word_ueberschriftenMitDritter_Acrobat.pdf";
		Headlines hl = new Headlines();
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			int actual = hl.countHeadlineConsistenceError(document);
			document.close();
			assertEquals(1, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testHeadline_1ErrorWordExport(){
		String pdfFile = pathToPDFs+"Word_ueberschriftenMitDritter_exportPDF.pdf";	
		Headlines hl = new Headlines();
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			int actual = hl.countHeadlineConsistenceError(document);
			document.close();
			assertEquals(1, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testHeadline_1ErrorLatex(){
		String pdfFile = pathToPDFs+"BspLatex.pdf";		
		Headlines hl = new Headlines();
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			int actual = hl.countHeadlineConsistenceError(document);
			document.close();
			assertEquals(-1, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}

}
