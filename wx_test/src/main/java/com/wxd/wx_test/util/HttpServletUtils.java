package com.wxd.wx_test.util;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpServletUtils {
	/**
	 * 打印所有请求参数
	 * @param request
	 */
	public static void printRequestParams(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = params.entrySet();
		for (Entry<String,String[]> entry:entrySet) {
			String key = entry.getKey();
			String[] value = entry.getValue();
			System.out.println(key +"=" + StringUtils.join(value));
		}
		System.out.println("-------------");
	}
}
