package com.qa.api.restclient;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Map;

import com.qa.api.util.TestUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is heaving all HTTP methods which will call the APIs and heaving
 * generic methods for getting the response and fetch the values from response
 * 
 * @author Pavel
 *
 */
public class RestClient {

	// HTTP methods: GET, POST, PUT, DELETE

	/**
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramMap
	 * @param log
	 * @return This methods will return the Response from the GET request 
	 */
	@Step("GET call with {1} , {2}, {3}, {4}, {5}, {6} ")
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramMap, boolean log) {

		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramMap, log );
			return getResponse("GET", request, basePath);
		}
		return null;
		
	}
	
	/**
	 * This methods will be used to call the POST APIs
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramMap
	 * @param log
	 * @param obj
	 * @return this methods is returning response from the POST call 
	 */

	public static Response doPost(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramMap, boolean log, Object obj) {

		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramMap, log );
			addRequestPayload(request, obj);
			return getResponse("POST", request, basePath);
		}
		return null;	
	}

	
	private static void addRequestPayload (RequestSpecification request, Object obj) {
		if(obj instanceof Map) {
			request.formParams((Map<String, String>)obj);
		} else {
			String jsonPayload = TestUtil.getSerializedPOJOJSON(obj);
			request.body(jsonPayload);
		}		
	}

	/**
	 * This method will set up the base URI
	 * 
	 * @param baseURI
	 * @return 
	 */
	private static boolean setBaseURI(String baseURI) {
		
		if(baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct the base URI....");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			System.out.println("Some exception got occured while assiging the URI....Either it's null or blank/empty....");
			return false;
		}
	}

	/**
	 * 
	 * @param contentType
	 * @param token
	 * @param paramMap
	 * @param log
	 * @return
	 */
	private static RequestSpecification createRequest(String contentType, Map<String, String> token, Map <String, String> paramMap, boolean log) {
		RequestSpecification request;
		if(log) {
			request = RestAssured.given().log().all();
		}
		else {
			request = RestAssured.given();
		}
		
		if(token.size() > 0) {
			//request.header("Authorization", "Bearer "+token);
			request.headers(token);
		}
		
		if(!(paramMap == null)) {
			request.queryParams(paramMap);
		}
		
		if(contentType != null) {
			if(contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}
			else if(contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			}
			else if(contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.TEXT);
			}
			else if(contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File("C:\\Users\\pavel\\OneDrive\\Documents\\Rest-Assured-Eclipse-workspace\\AdvanceRestAssuredFramework\\src\\test\\resources\\images\\Working-from-Home.jpg.jpg"));
				//request.multiPart(new File ())
			}
		}
		
		return request;
	}
	
	private static Response getResponse(String httpMethod, RequestSpecification request, String basepath) {
		return execuateAPI(httpMethod, request, basepath);
	}
	
	private static Response execuateAPI(String httpMethod, RequestSpecification request, String basepath) {
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basepath);
			break;
		case "POST":
			response = request.post(basepath);
			break;
		case "PUT":
			response = request.put(basepath);
			break;
		case "DELETE":
			response = request.delete(basepath);
			break;

		default:
			System.err.println("Please pass the correct HTTP methods.....");
			break;
		}
		return response;
	}
	
		
}
