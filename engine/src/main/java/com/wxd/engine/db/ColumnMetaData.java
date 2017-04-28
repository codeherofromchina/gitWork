package com.wxd.engine.db;

import com.wxd.engine.util.StrUtils;

/**
 * 表字段的元数据
 * 
 * @date Nov 23, 2016
 * @author wangXiaodan
 */
public class ColumnMetaData {

	private String tableCat;// String => 表类别（可为 null）
	private String tableSchem; // String => 表模式（可为 null）
	private String tableName; // String => 表名称
	private String columnName; // String => 列名称
	private Integer dataType; // int => 来自 java.sql.Types 的 SQL 类型
	private String typeName; // String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
	private Integer columnSize; // int => 列的大小。
	private String bufferLength; // 未被使用。
	/**
	 * 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。 
	 */
	private Integer decimalDigits; 
	private Integer numPrecRadix; // int => 基数（通常为 10 或 2）
	/**
	 * int => 是否允许使用 NULL。 
	 * columnNoNulls - 可能不允许使用NULL 值 
	 * columnNullable - 明确允许使用 NULL 值 
	 * columnNullableUnknown - 不知道是否可使用 null
	 */
	private Integer nullAble; 
	private String remarks; // String => 描述列的注释（可为 null）
	private String columnDef; // String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
	private Integer sqlDataType; // int => 未使用
	private Integer sqlDatetimeSub; // int => 未使用
	private Integer charOctetLength; // int => 对于 char 类型，该长度是列中的最大字节数
	private Integer ordinalPosition; // int => 表中的列的索引（从 1 开始）
	/**
	 * String => ISO 规则用于确定列是否包括 null。 
	 * YES ---  如果参数可以包括 NULL 
	 * NO --- 如果参数不可以包括 NULL 
	 * 空字符串 --- 如果不知道参数是否可以包括 null
	 */
	private String isNullAble; 
	/**
	 * 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是  REF，则为 null）
	 */
	private String scopeCatlog; // String => 
	/**
	 * String => 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
	 */
	private String scopeSchema; // 
	/**
	 * String => 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
	 */
	private String scopeTable; // 
	/**
	 * 不同类型或用户生成 Ref 类型、来自 java.sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是 DISTINCT 或用户生成的 REF，则为 null）
	 */
	private Short sourceDataType; // 
	/**
	 * String => 指示此列是否自动增加
	 *  YES --- 如果该列自动增加 
	 *  NO --- 如果该列不自动增加 
	 *  空字符串 ---  如果不能确定该列是否是自动增加参数
	 */
	private String isAutoincrement;
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
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFieldName() {
		return StrUtils.underlineToCamel(columnName);
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}
	
	public String getBufferLength() {
		return bufferLength;
	}
	public void setBufferLength(String bufferLength) {
		this.bufferLength = bufferLength;
	}
	public Integer getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(Integer decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	public Integer getNumPrecRadix() {
		return numPrecRadix;
	}
	public void setNumPrecRadix(Integer numPrecRadix) {
		this.numPrecRadix = numPrecRadix;
	}
	public Integer getNullAble() {
		return nullAble;
	}
	public void setNullAble(Integer nullAble) {
		this.nullAble = nullAble;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getColumnDef() {
		return columnDef;
	}
	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}
	public Integer getSqlDataType() {
		return sqlDataType;
	}
	public void setSqlDataType(Integer sqlDataType) {
		this.sqlDataType = sqlDataType;
	}
	public Integer getSqlDatetimeSub() {
		return sqlDatetimeSub;
	}
	public void setSqlDatetimeSub(Integer sqlDatetimeSub) {
		this.sqlDatetimeSub = sqlDatetimeSub;
	}
	public Integer getCharOctetLength() {
		return charOctetLength;
	}
	public void setCharOctetLength(Integer charOctetLength) {
		this.charOctetLength = charOctetLength;
	}
	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}
	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
	public String getIsNullAble() {
		return isNullAble;
	}
	public void setIsNullAble(String isNullAble) {
		this.isNullAble = isNullAble;
	}
	public String getScopeCatlog() {
		return scopeCatlog;
	}
	public void setScopeCatlog(String scopeCatlog) {
		this.scopeCatlog = scopeCatlog;
	}
	public String getScopeSchema() {
		return scopeSchema;
	}
	public void setScopeSchema(String scopeSchema) {
		this.scopeSchema = scopeSchema;
	}
	public String getScopeTable() {
		return scopeTable;
	}
	public void setScopeTable(String scopeTable) {
		this.scopeTable = scopeTable;
	}
	public Short getSourceDataType() {
		return sourceDataType;
	}
	public void setSourceDataType(Short sourceDataType) {
		this.sourceDataType = sourceDataType;
	}
	public String getIsAutoincrement() {
		return isAutoincrement;
	}
	public void setIsAutoincrement(String isAutoincrement) {
		this.isAutoincrement = isAutoincrement;
	}
	@Override
	public String toString() {
		return "ColumnMetaData [tableCat=" + tableCat + ", tableSchem=" + tableSchem + ", tableName=" + tableName
				+ ", columnName=" + columnName + ", dataType=" + dataType + ", typeName=" + typeName + ", columnSize="
				+ columnSize + ", bufferLength=" + bufferLength + ", decimalDigits=" + decimalDigits + ", numPrecRadix="
				+ numPrecRadix + ", nullAble=" + nullAble + ", remarks=" + remarks + ", columnDef=" + columnDef
				+ ", sqlDataType=" + sqlDataType + ", sqlDatetimeSub=" + sqlDatetimeSub + ", charOctetLength="
				+ charOctetLength + ", ordinalPosition=" + ordinalPosition + ", isNullAble=" + isNullAble
				+ ", scopeCatlog=" + scopeCatlog + ", scopeSchema=" + scopeSchema + ", scopeTable=" + scopeTable
				+ ", sourceDataType=" + sourceDataType + ", isAutoincrement=" + isAutoincrement + "]";
	}
	
	
	
}
