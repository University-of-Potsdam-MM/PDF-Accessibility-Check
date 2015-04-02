package de.unipotsdam.cs.mm.pdfac.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.fontbox.cmap.CMap;
import org.apache.pdfbox.encoding.Encoding;
import org.apache.pdfbox.encoding.MacRomanEncoding;
import org.apache.pdfbox.encoding.WinAnsiEncoding;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.font.PDSimpleFont;

/**
 * Check the accessibility of fonts in a PDF-file.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class Font {
	private static Boolean debug = false;
	
	/**
	 * Check whether fonts of a PDF-file are mapped to unicode. 
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if fonts are accessible; <code>false</code>, otherwise
	 */
	public static boolean checkFonts(PDDocument document) {
		PDDocumentCatalog catalog = document.getDocumentCatalog();
		Map<String, PDFont> fonts = new HashMap<String, PDFont>();
		List<PDPage> pages = catalog.getAllPages();
		for (PDPage page : pages) {
			fonts.putAll(page.getResources().getFonts());
		}
		if (debug) printFonts(fonts);
		for (PDFont font : fonts.values()) {
			if (!checkFont(font)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean checkFont(PDFont font) {
		if (font.getToUnicodeCMap() != null) {
			return true;
		}
		Encoding encoding = font.getFontEncoding();
		if (font instanceof PDSimpleFont) {//is simple font
			if (encoding instanceof WinAnsiEncoding || encoding instanceof MacRomanEncoding) {//or MacExpertEncoding
				return true;
			} else {
				//Here a check for a differences-Array might be needed --> since we don't check for now --> false
				return false;
			}
		}
		return false;
	}

	private static void printFonts(Map<String, PDFont> fonts) {
		String key;
		PDFont font;
		for (int i=0;i<fonts.size();i++) {
			key = (String) fonts.keySet().toArray()[i];
			font = fonts.get(key);
			System.out.println("Font:");
			System.out.println("  string: "+key);
			System.out.println("  font: "+font);
			printFontDescriptor(font);
			printFontEncoding(font);
			printUnicodeMap(font);
		}
	}
	
	private static void printFontDescriptor(PDFont font) {
		PDFontDescriptor desc = font.getFontDescriptor();
		if (desc == null) {
			System.out.println("  No FontDescriptor!");
		} else {
			System.out.println("  FontDescriptor:");
			System.out.println("    CharSet: "+desc.getCharSet());
			System.out.println("    FontFamily: "+desc.getFontFamily());
			System.out.println("    FontName: "+desc.getFontName());
		}
	}
	
	private static void printFontEncoding(PDFont font) {
		Encoding enc = font.getFontEncoding();
		if (enc == null) {
			System.out.println("  No FontEncoding!");
		} else {
			System.out.println("  FontEncoding:");
			System.out.println("    toString: "+enc.toString());
			System.out.println("    CodeToNameMap: "+enc.getCodeToNameMap());
		}
	}
	
	private static void printUnicodeMap(PDFont font) {
		CMap toUnicode = font.getToUnicodeCMap();
		if (toUnicode == null) {
			System.out.println("  No CMap!");
		} else {
			System.out.println("  CMap:");
			System.out.println("    name: "+toUnicode.getName());
			System.out.println("    ordering: "+toUnicode.getOrdering());
			System.out.println("    registry: "+toUnicode.getRegistry());
			System.out.println("    type: "+toUnicode.getType());
			System.out.println("    wmode: "+toUnicode.getWMode());
		}
	}
}
