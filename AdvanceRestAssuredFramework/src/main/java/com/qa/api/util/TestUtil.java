package com.qa.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	/**
	 * This methods will convert POJO Object to a JSON String
	 * @param obj
	 * @return This methods will return the jsonString
	 */
	public static String getSerializedPOJOJSON(Object obj) {
		String jsonString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			jsonString = mapper.writeValueAsString(obj);
			System.out.println("JSON BODY PAYLOAD ===>>>>"+jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
}
