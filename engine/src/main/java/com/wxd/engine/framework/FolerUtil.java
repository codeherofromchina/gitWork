package com.wxd.engine.framework;

import java.io.File;

public class FolerUtil {
	private static final String PACKAGE_FOLER = "src/main/java";
	private static final String RESOURCES_FOLER = "src/main/resources";
	private static final String WEB_APP_FOLER = "src/main/webapp";
	private static final String WEB_INF_FOLER = "src/main/webapp/WEB-INF";
	private static final String VELOCITY_FOLER = "src/main/webapp/WEB-INF/velocity";
	

	/**
	 * 获取所有目录
	 * 
	 * @return
	 */
	public static String[] getFolers() {
		return new String[] { PACKAGE_FOLER, RESOURCES_FOLER, WEB_APP_FOLER, WEB_INF_FOLER, VELOCITY_FOLER };
	}

	public static String getPackageFoler() {
		return PACKAGE_FOLER;
	}

	public static String getResourcesFoler() {
		return RESOURCES_FOLER;
	}

	public static String getWebAppFoler() {
		return WEB_APP_FOLER;
	}

	public static String getWebInfFoler() {
		return WEB_INF_FOLER;
	}

	public static String getVelocityFoler() {
		return VELOCITY_FOLER;
	}
	
	
	public static File mkdir(String dirPath) {
		File dirFile = new File(dirPath);
		return mkdir(dirFile);
	}
	
	
	public static File mkdir(File parent,String dirPath) {
		File dirFile = new File(parent,dirPath);
		return mkdir(dirFile);
	}
	
	public static File mkdir(File dir) {
		if (dir == null) {
			return null;
		}
		if (!dir.exists() || dir.isFile()) {
			dir.mkdirs();
		}
		return dir;
	}
	
}
