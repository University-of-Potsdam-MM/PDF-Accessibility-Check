package de.unipotsdam.cs.mm.pdfac.check;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.PDEncryptionDictionary;

/**
 * Checks whether permissions are accessible.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
public class PermissionCheck {
	private static boolean isBitSet(int byteToCheck, int bitToCheck) {
		return (byteToCheck & (1 << (bitToCheck-1))) != 0;
	}
	
	/**
	 * Check whether printing the file is allowed. ATTENTION: This is just a workaround for PermissionCheck#isPrintingAllowed(PDDocument) because of a PDFBox bug (https://issues.apache.org/jira/browse/PDFBOX-1651).
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if printing is allowed; <code>false</code>, otherwise
	 * @throws IOException if there is an error determining which security handler to use
	 */
	public static boolean isPrintingAllowed_workaround(PDDocument document) throws IOException{
		boolean print = false;
		int printBit = 3;
		PDEncryptionDictionary encryptDictionary = document.getEncryptionDictionary();
		if(encryptDictionary == null){ //no encryptDictionary -> there are no security permissions => no restrictions
			print = true;
		}else{
			int permissionaByte = encryptDictionary.getPermissions(); //PermissionBit --> PDFReference 1.7 Table 3.20
			print = isBitSet(permissionaByte,printBit);
		}
		return print;
	}
	
	/**
	 * Check whether printing the file is allowed.
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if printing is allowed; <code>false</code>, otherwise
	 */
	public static boolean isPrintingAllowed(PDDocument document){
		boolean print = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			AccessPermission permissions = document.getCurrentAccessPermission();
			if(permissions != null){
				print = permissions.canPrint();
			}
		}
		return print;
	}
	
	/**
	 * Check whether high quality-printing is allowed. ATTENTION: This is just a workaround for PermissionCheck#isHighQualityPrintingAllowed(PDDocument) because of a PDFBox bug (https://issues.apache.org/jira/browse/PDFBOX-1651).
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if high quality-printing is allowed; <code>false</code>, otherwise
	 * @throws IOException if there is an error determining which security handler to use
	 */
	public static boolean isHighQualityPrintingAllowed_workaround(PDDocument document) throws IOException{
		boolean print = false;
		int printHQBit = 12; //Revision >= 3
		int printBit = 3; //Revision <3
		PDEncryptionDictionary encryptDictionary = document.getEncryptionDictionary();
		if(encryptDictionary == null){ //no encryptDictionary -> there are no security permissions => no restrictions
			print = true;
		}else{
			int permissionByte = encryptDictionary.getPermissions(); //PermissionBit --> PDFReference Table 3.20
			if(encryptDictionary.getRevision() < 3){
				print = isBitSet(permissionByte,printBit);
			}else{
				print = isBitSet(permissionByte,printHQBit) && isBitSet(permissionByte,printBit);
			}
		}
		return print;
	}
	
	/**
	 * Should test if high quality-printing is allowed. ATTENTION: behaves not like the reference for pdfs indicates!
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if high quality-printing is allowed; <code>false</code>, otherwise
	 */
	public static boolean isHighQualityPrintingAllowed(PDDocument document){
		boolean printDegraded = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			AccessPermission permissions = document.getCurrentAccessPermission();
			if(permissions != null){
				printDegraded = permissions.canPrintDegraded();
			}
		}
		return printDegraded;
	}
	
	/**
	 * Check whether copying text from the PDF is allowed. ATTENTION: This is just a workaround for PermissionCheck#isCopyingAllowed(PDDocument) because of a PDFBox bug (https://issues.apache.org/jira/browse/PDFBOX-1651).
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if copying text is allowed; <code>false</code>, otherwise
	 * @throws IOException if there is an error determining which security handler to use
	 */
	public static boolean isCopyingAllowed_workaround(PDDocument document) throws IOException{
		boolean copy = false;
		int copyBit = 5;
		int accessibilityCopyBit = 10;
		PDEncryptionDictionary encryptDictionary = document.getEncryptionDictionary();
		if(encryptDictionary == null){ //no encryptDictionary -> there are no security permissions => no restrictions
			copy = true;
		}else{
			int permissionByte = encryptDictionary.getPermissions(); //PermissionBit --> PDFReference 1.7 Table 3.20
			if (encryptDictionary.getRevision() < 3) {
				copy = isBitSet(permissionByte,copyBit);
			} else {
				copy = isBitSet(permissionByte,copyBit) && isBitSet(permissionByte,accessibilityCopyBit);
			}				
		}
		return copy;
	}
	
	/**
	 * Check whether copying text from the PDF is allowed. 
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if copying text is allowed; <code>false</code>, otherwise
	 */
	public static boolean isCopyingAllowed(PDDocument document){
		boolean copy = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			AccessPermission permissions = document.getCurrentAccessPermission();
			if(permissions != null){
				copy = permissions.canExtractContent();
			}
		}
		return copy;
	}
	
	/**
	 * Check whether annotations in the PDF-file are allowed. ATTENTION: This is just a workaround for PermissionCheck#isAnnontatingAllowed(PDDocument) because of a PDFBox bug (https://issues.apache.org/jira/browse/PDFBOX-1651).
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if annotating is allowed; <code>false</code>, otherwise
	 * @throws IOException if there is an error determining which security handler to use
	 */
	public static boolean isAnnontatingAllowed_workaround(PDDocument document) throws IOException{
		boolean annontation = false;
		int annontationBit = 6;
		PDEncryptionDictionary encryptDictionary = document.getEncryptionDictionary();
		if(encryptDictionary == null){ //no encryptDictionary -> there are no security permissions => no restrictions
			annontation = true;
		}else{
			int permissionByte = encryptDictionary.getPermissions(); //PermissionBit --> PDFReference 1.7 Table 3.20
			annontation = isBitSet(permissionByte,annontationBit);
		}
		return annontation;
	}
	
	/**
	 * Check whether annotations in the PDF-file are allowed.
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if annotating is allowed; <code>false</code>, otherwise
	 */
	public static boolean isAnnontatingAllowed(PDDocument document){
		boolean annontation = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			AccessPermission permissions = document.getCurrentAccessPermission();
			if(permissions != null){
				annontation = permissions.canModify();
			}
		}
		return annontation;
	}
	
	/**
	 * Check whether content copying for accessibility is allowed. ATTENTION: This is just a workaround for PermissionCheck#isAccessibilityCopyingAllowed(PDDocument) because of a PDFBox bug (https://issues.apache.org/jira/browse/PDFBOX-1651).
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if content copying for accessibility is allowed; <code>false</code>, otherwise
	 * @throws IOException if there is an error determining which security handler to use
	 */
	public static boolean isAccessibilityCopyingAllowed_workaround(PDDocument document) throws IOException{
		boolean copy = false;
		int accessibilityCopyBit = 10; //Revision >= 3
		int copyBit = 5; //Revision <3
		PDEncryptionDictionary encryptDictionary = document.getEncryptionDictionary();
		if(encryptDictionary == null){ //no encryptDictionary -> there are no security permissions => no restrictions
			copy = true;
		}else{
			int permissionByte = encryptDictionary.getPermissions(); //PermissionBit --> PDFReference 1.7 Table 3.20
			if(encryptDictionary.getRevision() < 3){
				copy = isBitSet(permissionByte,copyBit);
			}else{
				copy = isBitSet(permissionByte,accessibilityCopyBit);
			}
		}
		return copy;
	}
	
	/**
	 * Check whether content copying for accessibility is allowed. 
	 * @param document the PDDocument of the file
	 * @return <code>true</code> if content copying for accessibility is allowed; <code>false</code>, otherwise
	 */
	public static boolean isAccessibilityCopyingAllowed(PDDocument document){
		boolean accessibilityCopy = false;
		PDDocumentCatalog pddc = document.getDocumentCatalog();
		if(pddc != null){
			AccessPermission permissions = document.getCurrentAccessPermission();
			if(permissions != null){
				accessibilityCopy = permissions.canExtractForAccessibility();
			}
		}
		return accessibilityCopy;
	}
}
