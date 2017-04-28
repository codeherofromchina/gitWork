package com.wxd.engine.framework;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.wxd.engine.config.Constant;
import com.wxd.engine.db.DB;
import com.wxd.engine.db.DbMetadata;
import com.wxd.engine.velocity.TemplateInfoEnum;

public class FrameWork {
	
	public FrameWork () throws Exception {
		// 检查数据库信息配置是否正确
		checkDbInfo();
	}
	
	/**
	 * 检查数据库配置信息是否正确
	 * @throws Exception
	 */
	private void checkDbInfo() throws Exception{
		Connection conn = DB.getConnection();
		if (conn == null) {
			throw new Exception("数据库连接错误");
		}
		conn.close();
	}
	
	/**
	 * 生成整个项目
	 * @throws SQLException 
	 */
	public void genProject() throws SQLException{
		initFoler();
		DbMetadata dbMetadata = DB.getDbMetadata();
		handleFiles(dbMetadata);
	}
	
	
	private void initFoler() {
		File outputDir = FolerUtil.mkdir(Constant.PROJECT_OUTPUT_FOLER);
		File projectDir = FolerUtil.mkdir(outputDir, Constant.PROJECT_ARTIFACTID);
		String[] folers = FolerUtil.getFolers();
		for (String foler:folers) {
			FolerUtil.mkdir(projectDir, foler);
		}
		TemplateInfoEnum.setProjectDir(projectDir);
	}
	
	/**
	 * 生成文件
	 * @param dbMetadata
	 */
	private void handleFiles(DbMetadata dbMetadata) {
		Map<String,Object> rootContext = new HashMap<String,Object>();
		rootContext.put("projectGroupId", Constant.PROJECT_GROUPID);
		rootContext.put("projectArtifactId", Constant.PROJECT_ARTIFACTID);
		rootContext.put("projectVersion", Constant.PROJECT_VERSION);
		rootContext.put("projectPackage", Constant.PROJECT_PACKAGE);
		rootContext.put("projectComplieVersion", Constant.PROJECT_COMPLIE_VERSION);
		rootContext.put("dbUrl", Constant.DB_URL);
		rootContext.put("dbDriverClassName", Constant.DB_DRIVER_CLASSNAME);
		rootContext.put("dbUserName", Constant.DB_USERNAME);
		rootContext.put("dbPassword", Constant.DB_PASSWORD);
		rootContext.put("hibernateDialect", Constant.HIBERNATE_DIALECT);
		rootContext.put("dbMetadata", dbMetadata);
		rootContext.put("_dollar", "$");
		rootContext.put("_plus_", "#");
		
		FileUtil.makeBeanFiles(rootContext);
		FileUtil.makeResourcesFiles(rootContext);
		FileUtil.makeOtherFiles(rootContext);
		FileUtil.copyStaticFiles();
	}
}
