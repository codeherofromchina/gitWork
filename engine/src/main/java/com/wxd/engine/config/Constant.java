package com.wxd.engine.config;

import java.io.File;

import org.apache.commons.lang.StringUtils;

/**
 * 项目配置字段类
 */
public class Constant {
	public static String PROJECT_GROUPID = Conf.get("project.groupId");
	public static String PROJECT_ARTIFACTID = Conf.get("project.artifactId","test");
	public static String PROJECT_VERSION = Conf.get("project.version");
	public static String PROJECT_COMPLIE_VERSION = Conf.get("project.complie.version");
	public static String PROJECT_PACKAGE = Conf.get("project.package");
	public static String PROJECT_OUTPUT_FOLER = null;
	public static String PROJECT_PACKAGE_FOLER = null;
	

	public static String DB_URL = Conf.get("db.url");
	public static String DB_DRIVER_CLASSNAME = Conf.get("db.driverClassName");
	public static String DB_USERNAME = Conf.get("db.username");
	public static String DB_PASSWORD = Conf.get("db.password");
	public static String HIBERNATE_DIALECT = Conf.get("hibernate.dialect");

	static {
		PROJECT_OUTPUT_FOLER = Conf.get("project.output.foler");
		if (Constant.PROJECT_OUTPUT_FOLER == null) {
			Constant.PROJECT_OUTPUT_FOLER = File.separator;
		} else if (!Constant.PROJECT_OUTPUT_FOLER.endsWith("/")) {
			Constant.PROJECT_OUTPUT_FOLER += File.separator;
		}
		
		if (StringUtils.isBlank(PROJECT_PACKAGE)) {
			PROJECT_PACKAGE = Conf.get("project.groupId") + "." + Conf.get("project.artifactId");
		}
		PROJECT_PACKAGE_FOLER = PROJECT_PACKAGE.replaceAll("\\.", File.separator);
	}
}
