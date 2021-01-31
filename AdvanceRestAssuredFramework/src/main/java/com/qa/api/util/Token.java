package com.qa.api.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Token {
	
	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	public static String clientId = "12b786f2f8a0fc5";
	
	public static Map<Object, Object> getAccessToken() {
		
		Map<String, String> formParamBody = new HashMap<String, String>();
		formParamBody.put("refresh_token", "194dd0ece88cd1357dea54f0c3ec9e5f9151ce7e");
		formParamBody.put("client_id", "12b786f2f8a0fc5");
		formParamBody.put("client_secret", "efe67a48ab5d7ffe70d74ee856b5402076796d84");
		formParamBody.put("grant_type", "refresh_token");
		
		JsonPath jsPath =
		given()//.log().all()
		     .formParams(formParamBody)
		.when()
		     .post("https://api.imgur.com/oauth2/token")
		.then()
		     .extract().jsonPath();
		
		System.out.println(jsPath.prettyPrint());
		
		appTokenMap = jsPath.getMap("");

		return appTokenMap;
	}
	
	/**
	 * 
	 * @return this methods will return the maps of authToken 
	 */
	public static Map<String, String> getAuthtoken() {
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Auth Token ==========>>>>" +"Bearer "+authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		return tokenMap;
	}
	
	public static Map<String, String> getClientId() {
		System.out.println("Client Id ==========>>>>" +"Bearer "+clientId);
		tokenMap.put("Authorization", "Client-ID "+clientId);
		return tokenMap;
	}
	
	public static Map<String, String> getUsername() {
		String accountUsername = appTokenMap.get("account_username").toString();
		System.out.println("Username ==========>>>>" +"Bearer "+accountUsername);
		tokenMap.put("Authorization", "Bearer  "+accountUsername);
		return tokenMap;
	}
	
}

