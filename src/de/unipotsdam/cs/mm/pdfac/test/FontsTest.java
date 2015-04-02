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

import de.unipotsdam.cs.mm.pdfac.check.Font;

/**
 * JUnit test for check criterion "accessible fonts".
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class FontsTest {
	private String pathToPDFs = "src"+File.separator+"de"+File.separator+"unipotsdam"+File.separator+"cs"+File.separator+"mm"+File.separator+"pdfac"+File.separator+"test"+File.separator+"pdfs"+File.separator;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testFont_Word(){
		String pdfFile = pathToPDFs+"Word_ueberschriften_Acrobat.pdf";	
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = Font.checkFonts(document);
			document.close();
			assertEquals(true, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	@Test
	public void testFont_Latex(){
		// ATTENTION: the check for fonts doesn't support all kinds of fonts
		String pdfFile = pathToPDFs+"BspLatex2.pdf";	
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			boolean actual = Font.checkFonts(document);
			document.close();
			assertEquals(true, actual);
		} catch (IOException e) {
			fail("File not found!");
		}
	}

}
