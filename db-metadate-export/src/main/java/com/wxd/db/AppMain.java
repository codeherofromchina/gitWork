package com.wxd.db;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 根据数据库元数据生成word表格文档。
 * TODO - 现阶段样式比较臭
 * 
 * @date Nov 24, 2016
 * @author wangXiaodan
 */
public class AppMain {
	public static void main(String[] args) throws SQLException, IOException {
		
		long start = System.currentTimeMillis();
		GenDbMetadataDoc.createDoc("scDbInfo", "/Users/wangxiaodan/test");
		long end = System.currentTimeMillis();
		
		System.out.println("cost time:" + (end - start) + "ms");
	}
}
