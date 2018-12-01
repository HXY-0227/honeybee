package com.honeybee.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * json转化工具
 */
public class JsonUtil {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 将对象转化成字符串
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		
		try {
			String json = MAPPER.writeValueAsString(data);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 将json结果集转化为对象
	 * @param json json数据
	 * @param object 对象中的Object类型
	 * @return
	 */
	public static<T> T jsonToPojo(String json, Class<T> object) {
		
		try {
			T t = MAPPER.readValue(json, object);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 将json数据转化为pojo对象list
	 * @param json
	 * @param object
	 * @return
	 */
	public static<T> List<T> jsonToList(String json, Class<T> object) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, object);
		try {
			List<T> list = MAPPER.readValue(json, javaType);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
