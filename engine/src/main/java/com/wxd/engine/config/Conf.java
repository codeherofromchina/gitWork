package com.wxd.engine.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 利用java.util.Properties构造的最简单全局配置实现方式：将配置文件config.properties放到classpath即可。
 *
 */
class Conf {
	private static Properties props = new Properties();
	
	static {
		try {
			props.load(Conf.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static String get(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
	protected static String get(String key) {
		return props.getProperty(key);
	}
	
	protected static int getInt(String key, int defaultValue) {
		String value = props.getProperty(key);
		if(value != null) return Integer.parseInt(value);
		return defaultValue;
	}
	
	protected static boolean getBoolean(String key, boolean defaultValue) {
		String value = props.getProperty(key);
		if(value != null) return Boolean.parseBoolean(value);
		return defaultValue;
	}
}
