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

import de.unipotsdam.cs.mm.pdfac.check.Check;

/**
 * JUnit test for check criterion "page numbers".
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class PageNumberTest {
	private String pathToPDFs = "src"+File.separator+"de"+File.separator+"unipotsdam"+File.separator+"cs"+File.separator+"mm"+File.separator+"pdfac"+File.separator+"test"+File.separator+"pdfs"+File.separator;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testPageNumberOnePageWord(){
		String pdfFile = pathToPDFs+"Word_ueberschriften_Acrobat.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			int actual = Check.getNumberOfPages(document);
			document.close();
			assertEquals(1, actual);
		} catch (IOException e) {
			e.printStackTrace();
			fail("File not found!");
		}		
	}
	
	@Test
	public void testPageNumberMuchPageLatex(){
		String pdfFile = pathToPDFs+"BspLatex2.pdf";
		PDDocument document;
		try {
			document = PDDocument.load(pdfFile);
			int actual = Check.getNumberOfPages(document);
			document.close();
			assertEquals(4, actual);
		} catch (IOException e) {
			fail("File not found!");
		}		
	}
	
	

}
