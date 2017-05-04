package com.wxd.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class GenDbMetadataDoc {
	private static final String NULL_CELL_PREFIX = "_null";
	private static final String TABLE_INFO_SPLIT = "：";
	private static final String FILE_SUFFIX = ".docx";
	
	
	public static void createDoc(String fileName,String path) throws SQLException, IOException {
		XWPFDocument doc = new XWPFDocument();
		OutputStream os = new FileOutputStream(new File(path, fileName + FILE_SUFFIX));
		
		DbMetadata sMetadata = DB.getDbMetadata();
		
		XWPFParagraph paragraph01 = doc.createParagraph();
		XWPFRun r01 = paragraph01.createRun();
		r01.setText(sMetadata.getProductName() + fileName);
		r01.addBreak();
		r01.setText(sMetadata.getVersion());
		
		List<TableMetaData> tableMetaDatas = sMetadata.getTableMetaDatas();
		if (tableMetaDatas != null && tableMetaDatas.size() > 0) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("columnName", "字段");
			map.put("typeName", "类型");
			map.put("remarks", "说明");
			map.put(NULL_CELL_PREFIX, "注释");
			for (TableMetaData tableMetaData : tableMetaDatas) {
				XWPFParagraph p2 = doc.createParagraph();
				XWPFRun r2 = p2.createRun();
				r2.addBreak();
				r2.addBreak();
				r2.setText(tableMetaData.getTableName() + TABLE_INFO_SPLIT + tableMetaData.getRemarks());
				createTable(doc,map,tableMetaData.getColumnMetaDatas());
			}
		}
		// 把doc输出到输出流中
		doc.write(os);
		os.close();
	}
	
	
	public static XWPFTable createTable(XWPFDocument doc,LinkedHashMap<String, String> mapDesc,List<ColumnMetaData> columnMetaDatas) {
		XWPFTable table = doc.createTable(1, mapDesc.size());
		
		Collection<String> values = mapDesc.values();
		int i = 0;
		XWPFTableRow headRow = table.getRow(0);
		for (String headStr:values ) {
			XWPFTableCell cell = headRow.getCell(i++);
			cell.setText(headStr);
		}
		
		for (ColumnMetaData columnMetaData:columnMetaDatas) {
			XWPFTableRow contentRow = table.createRow();
			i = 0; 
			Set<String> keySet = mapDesc.keySet();
			for (String key : keySet) {
				try {
					if (!NULL_CELL_PREFIX.equalsIgnoreCase(key)) {
						String property = BeanUtils.getProperty(columnMetaData, key);
						XWPFTableCell cell = contentRow.getCell(i++);
						cell.setText(property);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return table;
	}
}
