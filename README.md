# PDF-Accessibility-Check

This REST webservice checks local pdf files for its accessibility.
To use this webservice the following things are required:
	- tomcat
	- Java

After deploying the .war the functionality can be checked by entering this url in a browser (on the same system): http://localhost:8080/PDF-Accessibility-Check/rest/helloJson

The thirdparty lib for PDFBox is NOT contained in this git. Please download it yourself from https://pdfbox.apache.org/download.cgi and put it in the directory /WebContent/WEB-INF/lib. Tested with pdfbox-app-1.8.6.jar.

Also see "README_Adding_Criterions.txt" for adding new criterions to the server.