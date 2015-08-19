# PDF-Accessibility-Check

This REST webservice checks local PDF files for their accessibility.
To use this webservice the following things are required:
* [Apache Tomcat](https://tomcat.apache.org/),
* Java (both [OpenJDK](http://openjdk.java.net/) and [Oracle Java SE](http://www.oracle.com/technetwork/java/javase/index.html) should work).

After deploying the `.war` the functionality can be checked by entering this url in a browser (on the same system): http://localhost:8080/PDF-Accessibility-Check/rest/helloJson

The third-party library for PDFBox is NOT contained in this git. Please download it yourself from the [Apache PDFBox site](https://pdfbox.apache.org/download.cgi) and put it in the directory `/WebContent/WEB-INF/lib`. Tested with `pdfbox-app-1.8.6.jar`.

Also see "[README_Adding_Criterions.txt](README_Adding_Criterions.txt)" for adding new criteria to the server.

This server was developed for the Moodle Plugin "MoodlePlugin_PDFChecker". More information about this can be found in the [repository MoodlePlugin_PDFChecker](https://github.com/University-of-Potsdam-MM/MoodlePlugin_PDFChecker). 
