package com.wxd.engine.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wxd.engine.config.Constant;

public class DB {

	static {
		try {
			Class.forName(Constant.DB_DRIVER_CLASSNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("驱动依赖不存在");
			System.exit(0);
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
		return conn;
	}

	/**
	 * 获取数据库元数据
	 * 
	 * @author wangXiaodan
	 * @return
	 * @throws SQLException
	 */
	public static DbMetadata getDbMetadata(String catalog, String schemaPattern, String tableNamePattern,
			String[] types) throws SQLException {
		Connection conn = getConnection();
		DbMetadata dbMetadata = null;
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			dbMetadata = new DbMetadata();
			dbMetadata.setProductName(metaData.getDatabaseProductName());
			dbMetadata.setVersion(metaData.getDatabaseProductVersion());

			ResultSet tablesRs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			while (tablesRs.next()) {
				TableMetaData tableMetaData = new TableMetaData();

				tableMetaData.setTableCat(tablesRs.getString(1));
				tableMetaData.setTableSchem(tablesRs.getString(2));
				tableMetaData.setTableName(tablesRs.getString(3));
				tableMetaData.setTableType(tablesRs.getString(4));
				tableMetaData.setRemarks(tablesRs.getString(5));
				tableMetaData.setTypeCat(tablesRs.getString(6));
				tableMetaData.setTypeSchem(tablesRs.getString(7));
				tableMetaData.setTypeNameString(tablesRs.getString(8));
				tableMetaData.setSelfReferencingColName(tablesRs.getString(9));
				tableMetaData.setRefGeneration(tablesRs.getString(10));

				ResultSet columnsRs = metaData.getColumns(catalog, schemaPattern, tableMetaData.getTableName(), null);
				while (columnsRs.next()) {
					ColumnMetaData columnMetaData = new ColumnMetaData();
					columnMetaData.setTableCat(columnsRs.getString(1));
					columnMetaData.setTableSchem(columnsRs.getString(2));
					columnMetaData.setTableName(columnsRs.getString(3));
					columnMetaData.setColumnName(columnsRs.getString(4));
					columnMetaData.setDataType(columnsRs.getInt(5));
					columnMetaData.setTypeName(columnsRs.getString(6));
					columnMetaData.setColumnSize(columnsRs.getInt(7));
					columnMetaData.setBufferLength(columnsRs.getString(8));
					columnMetaData.setDecimalDigits(columnsRs.getInt(9));
					columnMetaData.setNumPrecRadix(columnsRs.getInt(10));
					columnMetaData.setNullAble(columnsRs.getInt(11));
					columnMetaData.setRemarks(columnsRs.getString(12));
					columnMetaData.setColumnDef(columnsRs.getString(13));
					columnMetaData.setSqlDataType(columnsRs.getInt(14));
					columnMetaData.setSqlDatetimeSub(columnsRs.getInt(15));
					columnMetaData.setCharOctetLength(columnsRs.getInt(16));
					columnMetaData.setOrdinalPosition(columnsRs.getInt(17));
					columnMetaData.setIsNullAble(columnsRs.getString(18));
					columnMetaData.setScopeCatlog(columnsRs.getString(19));
					columnMetaData.setScopeSchema(columnsRs.getString(20));
					columnMetaData.setScopeTable(columnsRs.getString(21));
					columnMetaData.setSourceDataType(columnsRs.getShort(22));
					columnMetaData.setIsAutoincrement(columnsRs.getString(23));

					tableMetaData.addColumnMetaData(columnMetaData);
				}
				columnsRs.close();
				dbMetadata.addTableMetaData(tableMetaData);
			}
			tablesRs.close();
		} finally {
			conn.close();
		}
		return dbMetadata;
	}

	public static DbMetadata getDbMetadata(String[] types) throws SQLException {
		return getDbMetadata(null, null, null, types);
	}

	public static DbMetadata getDbMetadata() throws SQLException {
		return getDbMetadata(null, null, null, null);
	}

	/**
	 * 获取数据库所有类别名称
	 * 
	 * @author wangXiaodan
	 * @return
	 * @throws SQLException
	 */
	public static String[] getAllCatalogs() throws SQLException {
		Connection conn = DB.getConnection();
		String[] result = null;
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet catalogRs = metaData.getCatalogs();
			List<String> list = new ArrayList<String>();
			while (catalogRs.next()) {
				String tableSchema = catalogRs.getString(1);
				list.add(tableSchema);
			}
			catalogRs.close();
			result = new String[list.size()];
			list.toArray(result);
		} finally {
			conn.close();
		}
		return result;
	}

	/**
	 * 获取数据库所有模式名称
	 * 
	 * @author wangXiaodan
	 * @return
	 * @throws SQLException
	 */
	public static String[] getAllSchemas() throws SQLException {
		Connection conn = DB.getConnection();
		String[] result = null;
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet schemaRs = metaData.getSchemas();
			List<String> list = new ArrayList<String>();
			while (schemaRs.next()) {
				String tableSchema = schemaRs.getString(1);
				list.add(tableSchema);
			}
			schemaRs.close();
			result = new String[list.size()];
			list.toArray(result);
		} finally {
			conn.close();
		}
		return result;
	}

}
