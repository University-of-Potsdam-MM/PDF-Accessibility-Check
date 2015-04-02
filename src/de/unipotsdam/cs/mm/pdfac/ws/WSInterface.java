package de.unipotsdam.cs.mm.pdfac.ws;

import java.io.File;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.unipotsdam.cs.mm.pdfac.CriterionInfos;
import de.unipotsdam.cs.mm.pdfac.check.Check;
import de.unipotsdam.cs.mm.pdfac.json.CheckCriterion;
import de.unipotsdam.cs.mm.pdfac.json.CheckCriterionType;
import de.unipotsdam.cs.mm.pdfac.json.TestResult;

//http://localhost:8080/PDF-Accessibility-Check/rest/helloJson
/**
 * This class is the interface for the REST webservice.
 * @author Fritz Rose and Marlene Karlapp
 *
 */
@Path("/")
public class WSInterface{
	
	/**
	 * A dummy interface to test the webservice.
	 * @return JSON-String of a fixed {@link TestResult}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/helloJson")
	public String printDummyData(){
		String jsonstring = "{\"outlines\": true, \"marked\" : false }";
		return jsonstring;
	}
	
	/**
	 * Returns the accessibility test results of a PDF-file.
	 * @param path the path to the PDF-File
	 * @return a JSON-String of {@link TestResult}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/checkPdfFile")	
	public String checkPdfFile(@QueryParam("path") String path ){
		File pdfFile = new File(path);
		TestResult tr = Check.getTestResult(pdfFile);
		Gson gson = new Gson();
		String jsonstring = gson.toJson(tr);
		return jsonstring;
	}
	
	
	/**
	 * Returns all check criterion names.
	 * @param locale a java.util.Locale object which contains a language identifier
	 * @return a JSON-String of {@link CheckCriterion}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCheckCriterionNames")
	public String getCheckCriterionNames(@QueryParam("locale") Locale locale){
		CriterionInfos ci = new CriterionInfos();
		CheckCriterion cc = new CheckCriterion();
		cc = ci.getCheckCriterionNames(locale);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(cc);
		return jsonString;
	}
	
	/**
	 * Returns names and types of all check criterions.
	 * @return a JSON-String of {@link CheckCriterionType}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCheckCriterionNamesAndType")
	public String getCheckCriterionNamesAndType(){
		CriterionInfos ci = new CriterionInfos();
		CheckCriterionType cct=  ci.getCheckCriterionNamesAndType();
		Gson gson = new Gson();
		String jsonString = gson.toJson(cct);
		return jsonString;
	}
		
}
