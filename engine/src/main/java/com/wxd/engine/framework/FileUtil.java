package com.wxd.engine.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxd.engine.db.DbMetadata;
import com.wxd.engine.db.TableMetaData;
import com.wxd.engine.velocity.TemplateInfoEnum;

public class FileUtil {
	public static final String ACTION = "action";
	public static final String DB = "db";
	public static final String DAO = "dao";
	public static final String DAO_IMPL = "dao" + File.separator + "impl";
	public static final String DOMAIN = "domain";
	public static final String INTERCEPTO = "interceptor";
	public static final String SERVICE = "service";
	public static final String UTILS = "utils";
	
	
	/**
	 * 生成数据库实体和对应的domain/action/service/dao和页面vm的各种文件
	 */
	public static void makeBeanFiles(Map<String,Object> rootContext) {
		DbMetadata dbMetadata = (DbMetadata)rootContext.get("dbMetadata");
		List<TableMetaData> tableMetaDatas = dbMetadata.getTableMetaDatas();
		
		Map<String,Object> context = new HashMap<String,Object>(rootContext);
		for (TableMetaData tableMetaData : tableMetaDatas) {
			String beanName = tableMetaData.getClassName();
			String tableName = tableMetaData.getTableName();
			context.put("beanName", beanName);
			context.put("tableName", tableName);
			context.put("tableMetaData", tableMetaData);
			
			new MapBean(TemplateInfoEnum.TABLE_HTML,tableName + ".html",context).produceFile();
			new MapBean(TemplateInfoEnum.TABLE_EDIT,tableName + "_edit.html",context).produceFile();
			new MapBean(TemplateInfoEnum.TABLE_JAVA,beanName + ".java",context).produceFile();
			new MapBean(TemplateInfoEnum.TABLE_ACTION,beanName + "Action.java",context).produceFile();
			new MapBean(TemplateInfoEnum.TABLE_SERVICE,beanName + "Service.java",context).produceFile();
			new MapBean(TemplateInfoEnum.TABLE_DAO,beanName + "Dao.java",context).produceFile();
			new MapBean(TemplateInfoEnum.TABLE_DAO_IMPL,beanName + "DaoImpl.java",context).produceFile();
		}
	}
	
	
	public static void makeResourcesFiles(Map<String,Object> rootContext) {
		new MapBean(TemplateInfoEnum.APPLICATION_CONTEXT,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.COMMON_LOGGING,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.JDBC,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.LOG4J,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.SPRING_MVC,null,rootContext).produceFile();
	}
	
	/**
	 * 生成其他文件
	 */
	public static void makeOtherFiles(Map<String,Object> rootContext) {
		new MapBean(TemplateInfoEnum.POM,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.WEBXML,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.TOOLBOX,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.GEN_STR,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.PAGE_BEAN,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.INDEXA_CTION,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.INDEX,null,rootContext).produceFile();
		new MapBean(TemplateInfoEnum.INTERCEPTOR,null,rootContext).produceFile();
	}
	
	
	public static void copyStaticFiles() {
		try {
			URL url = FileUtil.class.getClassLoader().getResource("static");
			copyFolder(new File(url.toURI()),new File(TemplateInfoEnum.getProjectDir(),FolerUtil.getWebAppFoler()));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("静态文件拷贝异常");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.err.println("资源路径静态资源获取错误");
		}
	}
	
	/** 
	 * 复制一个目录及其子目录、文件到另外一个目录 
	 * @param src 
	 * @param dest 
	 * @throws IOException 
	 */  
	private static void copyFolder(File src, File dest) throws IOException {  
	    if (src.isDirectory()) {  
	        if (!dest.exists()) {  
	            dest.mkdir();  
	        }  
	        String files[] = src.list();  
	        for (String file : files) {  
	            File srcFile = new File(src, file);  
	            File destFile = new File(dest, file);
	            // 递归复制  
	            copyFolder(srcFile, destFile);  
	        }
	    } else {
	        InputStream in = new FileInputStream(src);  
	        OutputStream out = new FileOutputStream(dest);  
	  
	        byte[] buffer = new byte[1024];  
	  
	        int length;  
	          
	        while ((length = in.read(buffer)) > 0) {  
	            out.write(buffer, 0, length);  
	        }  
	        in.close();  
	        out.close();  
	    }  
	}  
}
