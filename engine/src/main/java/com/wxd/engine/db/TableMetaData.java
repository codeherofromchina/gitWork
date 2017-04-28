package com.wxd.engine.db;

import java.util.ArrayList;
import java.util.List;

import com.wxd.engine.util.StrUtils;

/**
 * 表的元数据
 * 
 * @date Nov 23, 2016
 * @author wangXiaodan
 */
public class TableMetaData {
	private String tableCat;  // 表类别（可为 null）
	private String tableSchem; // 表模式（可为 null） 
	private String tableName; // 
	private String tableType ; // 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。 
	private String remarks ; // 表的解释性注释
	private String typeCat ; // 类型的类别（可为 null） 
	private String typeSchem ; // 类型模式（可为 null）
	private String typeNameString ; // 类型名称（可为 null） 
	private String selfReferencingColName; // 有类型表的指定 "identifier" 列的名称（可为 null） 
	private String refGeneration; // 指定在 SELF_REFERENCING_COL_NAME 中创建值的方式。这些值为 "SYSTEM"、"USER" 和 "DERIVED"。（可能为 null） 
	
	private List<ColumnMetaData> columnMetaDatas; // 表中所有字段的元数据列表
	// 
	public String getTableCat() {
		return tableCat;
	}
	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}
	public String getTableSchem() {
		return tableSchem;
	}
	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}
	// 这里讲tableName字段按照驼峰命名来转换
	public String getClassName() {
		String camel = StrUtils.underlineToCamel(tableName);
		if (camel != null && camel.length() > 1) {
			camel = camel.substring(0, 1).toUpperCase() + camel.substring(1);
		}
		return camel;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTypeCat() {
		return typeCat;
	}
	public void setTypeCat(String typeCat) {
		this.typeCat = typeCat;
	}
	public String getTypeSchem() {
		return typeSchem;
	}
	public void setTypeSchem(String typeSchem) {
		this.typeSchem = typeSchem;
	}
	public String getTypeNameString() {
		return typeNameString;
	}
	public void setTypeNameString(String typeNameString) {
		this.typeNameString = typeNameString;
	}
	public String getSelfReferencingColName() {
		return selfReferencingColName;
	}
	public void setSelfReferencingColName(String selfReferencingColName) {
		this.selfReferencingColName = selfReferencingColName;
	}
	public String getRefGeneration() {
		return refGeneration;
	}
	public void setRefGeneration(String refGeneration) {
		this.refGeneration = refGeneration;
	}
	public List<ColumnMetaData> getColumnMetaDatas() {
		return columnMetaDatas;
	}
	public void setColumnMetaDatas(List<ColumnMetaData> columnMetaDatas) {
		this.columnMetaDatas = columnMetaDatas;
	}
	public void addColumnMetaData(ColumnMetaData columnMetaData) {
		 if ( this.columnMetaDatas == null ) {
			 this.columnMetaDatas = new ArrayList<ColumnMetaData>();
		 }
		 this.columnMetaDatas.add(columnMetaData);
	}
	@Override
	public String toString() {
		return "TableMetaData [tableCat=" + tableCat + ", tableSchem=" + tableSchem + ", tableName=" + tableName
				+ ", tableType=" + tableType + ", remarks=" + remarks + ", typeCat=" + typeCat + ", typeSchem="
				+ typeSchem + ", typeNameString=" + typeNameString + ", selfReferencingColName="
				+ selfReferencingColName + ", refGeneration=" + refGeneration + ", columnMetaDatas=" + columnMetaDatas
				+ "]";
	}
	
	
}
