package com.qa.api.gorest.tests;



import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.pojo.CreateUserPojo;
import com.qa.api.restclient.RestClient;
import com.qa.api.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

/**
 * 
 * @author pavel
 *
 */

@Epic("Create a user gorest API feature implementation.....")
@Feature("Create user API feature")
public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String token = "8dd2304ce2768a888d577c6a0eea4b0d41030091f1a0b33494656f3c0dab8e55";
	
	@Description("Create a mutiiple user with help of dataprovider then verify create user post call.....")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test (dataProvider = "getUserData" )
	public void createUserAPIPostTest(String name, String email, String gender, String status) {
		String basePath = "/public-api/users";
		System.out.println("Bearer "+token);
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer "+token);
		
		CreateUserPojo createUser = new CreateUserPojo();
	
		createUser.setName(name);
		createUser.setEmail(email);
		createUser.setGender(gender);
		createUser.setStatus(status);
		
		
		Response response = RestClient.doPost("JSON", baseURI, basePath, tokenMap, null, true, createUser);
		System.out.println(response.getStatusCode());
		System.err.println(response.prettyPrint());
		System.out.println("===========================>>>>>>>>>>>>>>>>>>>");
	}
	
	@DataProvider
	public Object[][] getUserData() {
		Object userdata [][] = ExcelUtil.getTestData("Sheet1");
		return userdata;
	}
	
}
