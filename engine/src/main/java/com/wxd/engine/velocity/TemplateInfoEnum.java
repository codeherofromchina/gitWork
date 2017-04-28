package com.wxd.engine.velocity;

import java.io.File;

import com.wxd.engine.config.Constant;
import com.wxd.engine.framework.FileUtil;
import com.wxd.engine.framework.FolerUtil;

/**
 * 模板和要生成文件位置的枚举
 * @author wangxiaodan
 *
 */
public enum TemplateInfoEnum {
	APPLICATION_CONTEXT("applicationContext.xml",FolerUtil.getResourcesFoler()),
	COMMON_LOGGING("common-logging.properties",FolerUtil.getResourcesFoler()),
	GEN_STR("GenString.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.UTILS),
	INDEX("index.html",FolerUtil.getVelocityFoler()),
	INDEXA_CTION("IndexAction.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.ACTION),
	JDBC("jdbc.properties",FolerUtil.getResourcesFoler()),
	LOG4J("log4j.properties",FolerUtil.getResourcesFoler()),
	PAGE_BEAN("PageBean.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.UTILS),
	POM("pom.xml",""),
	SPRING_MVC("spring-mvc.xml",FolerUtil.getResourcesFoler()),
	INTERCEPTOR("SpringMVCInterceptor.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.INTERCEPTO),
	TABLE_EDIT("table_edit.html",FolerUtil.getVelocityFoler()),
	TABLE_HTML("table.html",FolerUtil.getVelocityFoler()),
	TABLE_JAVA("table.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.DOMAIN),
	TABLE_ACTION("tableAction.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.ACTION),
	TABLE_DAO("tableDao.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.DAO),
	TABLE_DAO_IMPL("tableDaoImpl.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.DAO_IMPL),
	TABLE_SERVICE("tableService.java",FolerUtil.getPackageFoler() + File.separator + Constant.PROJECT_PACKAGE_FOLER + File.separator + FileUtil.SERVICE),
	TOOLBOX("toolbox.xml",FolerUtil.getWebInfFoler()),
	WEBXML("web.xml",FolerUtil.getWebInfFoler());
	
	private String templateName;
	private String folerPath;
	
	private TemplateInfoEnum(String templateName,String folerPath) {
		this.templateName = templateName;
		this.folerPath = folerPath;
	}
	
	public String getFolerPath() {
		return getProjectDirPath() + File.separator + folerPath;
	}
	public String getTemplateName() {
		return templateName;
	}
	
	
	public static void setProjectDir(File file) {
		projectDir = file;
	}
	public static File getProjectDir() {
		return projectDir;
	}
	public static String getProjectDirPath() {
		return projectDir.getPath();
	}
	private static File projectDir = null;
}
