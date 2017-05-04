package com.wxd.db;

import java.util.ArrayList;
import java.util.List;

public class DbMetadata {
	
	private String productName;
	private String version;
	private List<TableMetaData> tableMetaDatas;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public List<TableMetaData> getTableMetaDatas() {
		return tableMetaDatas;
	}

	public void setTableMetaDatas(List<TableMetaData> tableMetaDatas) {
		this.tableMetaDatas = tableMetaDatas;
	}

	public void addTableMetaData(TableMetaData tableMetaData) {
		if (this.tableMetaDatas == null ) {
			this.tableMetaDatas = new ArrayList<TableMetaData>();
		}
		this.tableMetaDatas.add(tableMetaData);
	}


}
