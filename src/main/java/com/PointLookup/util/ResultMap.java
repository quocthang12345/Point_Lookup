package com.PointLookup.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

public class ResultMap {
	
	private static final String MESSAGE = "message";
	private static final String SUCCESS = "success";
	private static final String DATA = "data";

	public static Map<String, Object> createResultMap(String status, Object data, String message) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(SUCCESS, status.equalsIgnoreCase("true"));
		resultMap.put(DATA, data);
		resultMap.put(MESSAGE, message);
		return resultMap;
	}
}
