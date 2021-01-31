package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("Get user gorest API feature implementation.....")
@Feature("GET user API feature")
public class GetUserTest {

	
	String baseURI = "https://gorest.co.in";
	//String basePath = "/public-api/posts";
	String token = "8dd2304ce2768a888d577c6a0eea4b0d41030091f1a0b33494656f3c0dab8e55";
	
	@Description("GET all user list api test will verify all the user list from get call.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getAllUserAPITest() {
		
		String basePath = "/public-api/users";
		System.out.println("Bearer "+token);
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer "+token);
		Response res = RestClient.doGet(null, baseURI, basePath, tokenMap, null, true);
		
		System.out.println(res.getStatusCode());
		System.out.println(res.prettyPrint());
	}
	
	@Description("GET all user list api test with query param...will verify all the user list from get call.....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void getAllUserWithQueryParamAPITest() {
		
		String basePath = "/public-api/posts";
		
		System.out.println("Bearer "+token);
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer "+token);
		
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("id", "6");
		
		Response res = RestClient.doGet(null, baseURI, basePath, tokenMap, queryParam, true);
		
		System.out.println(res.getStatusCode());
		//System.err.println(res.prettyPrint());
	}
	
	
	//1351
	@Description("GET a user with ID api test will verify a particular user .....")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void getUserWithIdAPITest() {
		
		String basePath = "/public-api/users/1351";
		
		System.out.println("Bearer "+token);
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer "+token);
		
		Response res = RestClient.doGet(null, baseURI, basePath, tokenMap, null, true);
		
		System.out.println(res.getStatusCode());
		System.out.println(res.prettyPrint());
	}
	
}
