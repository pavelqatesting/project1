package com.qa.api.imgur.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.restclient.RestClient;
import com.qa.api.util.Token;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetImgurAPITest {
	
	Map<Object, Object> tokenMap;
	String token;
	String username;
	
	@BeforeMethod
	public void setUp() {
		
		tokenMap = Token.getAccessToken();
		token = tokenMap.get("access_token").toString();
		username = tokenMap.get("account_username").toString();
		
	}
	

	@Test
	public void getAccountBlockStatus() {
		//https://api.imgur.com/account/v1/{{username}}/block
		
		String baseURI = "https://api.imgur.com";
		String basePath = "/account/v1/"+username+"/block";
		
		Map<String, String> accessToken = Token.getAuthtoken();
		
		Response response = RestClient.doGet(null, baseURI, basePath, accessToken, null, true);
		System.err.println(response.getStatusCode());
		System.err.println(response.prettyPrint());
		
		JsonPath jsPath = response.jsonPath();
		boolean flag = jsPath.get("data.blocked");
		System.out.println("Assertion passed");
		Assert.assertFalse(true);
	}
	
	@Test
	public void getAccountBase() {
		//https://api.imgur.com/3/account/{{username}}
		String baseURI = "https://api.imgur.com";
		String basePath = "/3/account/"+username;
		
		Map<String, String> accessToken = Token.getAuthtoken();
		
		Response response = RestClient.doGet(null, baseURI, basePath, accessToken, null, true);
		System.err.println(response.getStatusCode());
		System.err.println(response.prettyPrint());
	}
	
	@Test
	public void uploadImagePostAPITest() {
		
		String baseURI = "https://api.imgur.com";
		String basePath = "/3/upload";
		Map<String, String> clientIdMap = Token.getClientId();
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "home-office setup");
		formMap.put("description", "home-setup dec");
		
		Response response =RestClient.doPost("multipart", baseURI, basePath, clientIdMap, null, true, formMap);
		
		System.err.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	 
	
}
