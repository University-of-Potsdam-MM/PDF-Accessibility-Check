Introduction to add new check criteria: 
=========================================
- in PDF-Accessibility-Check/src/de/unipotsdam/cs/mm/pdfac/CriterionDB.java:
	- at the end of the enum "CriterionDB" add the name of the new criterion
	- in the function "getDbType" add the data type of the new criterion
- in package de.unipotsdam.cs.mm.pdfac.i18n:
	- add the translation of the new criterion at least in English
- in package de.unipotsdam.cs.mm.pdfac.check:
		- add a new class to check this new criterion or add the check in the class "Check"
- in src/de/unipotsdam/cs/mm/pdfac/check/Check#getTestResult:
	- add a call to the new accessibility check
	- add the result to the "Result" object
	- add the data type to the result
	- add the "result" object to the "TestResult" object