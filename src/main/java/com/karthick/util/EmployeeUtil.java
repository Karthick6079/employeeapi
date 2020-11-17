package com.karthick.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployeeUtil {
	
	
	public Object convertObjectFromJSON(String jsonData, Class<?> typeClass) {
		
		ObjectMapper objectMapper;
		Object object = null;
		try {
			objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
			object = objectMapper.readValue(jsonData, typeClass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return object;
		
	}
	
	
	public boolean isUserAuthenticated(String inputString) {
		
		
		return false;
	}

}
