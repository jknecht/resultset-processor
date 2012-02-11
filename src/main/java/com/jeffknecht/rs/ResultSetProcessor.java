package com.jeffknecht.rs;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetProcessor {
	private ResultSet rs;
	private ResultSetProcessorEventHandler handler;
	
	public ResultSetProcessor(ResultSet rs, ResultSetProcessorEventHandler handler) {
		this.rs = rs;
		this.handler = handler;
	}
	
	public void process() throws SQLException {
		try {
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			Column[] columns = new Column[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columns[i - 1] = new ColumnImpl(metadata, i);
			}
			
			handler.startResultSet(columnCount);
			long recordNumber = 0;
			while(rs.next()) {
				recordNumber++;
				
				handler.startRecord(recordNumber);
				for (int i = 1; i <= columnCount; i++) {
					Object value = rs.getObject(i);
					if (rs.wasNull()) {
						value = null;
					}
					handler.column(columns[i - 1], value);
				}
				handler.endRecord(recordNumber);
			}
			handler.endResultSet(recordNumber);
		} catch(SQLException e) {
			handler.sqlException(e);
			throw e;
		}
	}
	
	private static class ColumnImpl implements Column {
		int ordinalPosition;
		String catalogName;
		String columnClassName;
		int columnDisplaySize;
		String columnLabel;
		String columnName;
		int columnType;
		String columnTypeName;
		int precision;
		int scale;
		String schemaName;
		String tableName;
		boolean autoIncrement;
		boolean caseSensitive;
		boolean currency;
		boolean definitelyWritable;
		int nullable;
		boolean readOnly;
		boolean searchable;
		boolean signed;
		boolean writable;
		
		public ColumnImpl(ResultSetMetaData meta, int column) throws SQLException {
			this.catalogName = meta.getCatalogName(column);
			this.columnClassName = meta.getColumnClassName(column);
			this.columnDisplaySize = meta.getColumnDisplaySize(column);
			this.columnLabel = meta.getColumnLabel(column);
			this.columnName = meta.getColumnName(column);
			this.columnType = meta.getColumnType(column);
			this.columnTypeName = meta.getColumnTypeName(column);
			this.precision = meta.getPrecision(column);
			this.scale = meta.getScale(column);
			this.schemaName = meta.getSchemaName(column);
			this.tableName = meta.getTableName(column);
			this.autoIncrement = meta.isAutoIncrement(column);
			this.caseSensitive = meta.isCaseSensitive(column);
			this.currency = meta.isCurrency(column);
			this.definitelyWritable = meta.isDefinitelyWritable(column);
			this.nullable = meta.isNullable(column);
			this.readOnly = meta.isReadOnly(column);
			this.searchable = meta.isSearchable(column);
			this.signed = meta.isSigned(column);
			this.writable = meta.isWritable(column);
			this.ordinalPosition = column;
		}

		public int getOrdinalPosition() {
			return ordinalPosition;
		}

		public String getCatalogName() {
			return catalogName;
		}

		public String getColumnClassName() {
			return columnClassName;
		}

		public int getColumnDisplaySize() {
			return columnDisplaySize;
		}

		public String getColumnLabel() {
			return columnLabel;
		}

		public String getColumnName() {
			return columnName;
		}

		public int getColumnType() {
			return columnType;
		}

		public String getColumnTypeName() {
			return columnTypeName;
		}

		public int getPrecision() {
			return precision;
		}

		public int getScale() {
			return scale;
		}

		public String getSchemaName() {
			return schemaName;
		}

		public String getTableName() {
			return tableName;
		}

		public boolean isAutoIncrement() {
			return autoIncrement;
		}

		public boolean isCaseSensitive() {
			return caseSensitive;
		}

		public boolean isCurrency() {
			return currency;
		}

		public boolean isDefinitelyWritable() {
			return definitelyWritable;
		}

		public int isNullable() {
			return nullable;
		}

		public boolean isReadOnly() {
			return readOnly;
		}

		public boolean isSearchable() {
			return searchable;
		}

		public boolean isSigned() {
			return signed;
		}

		public boolean isWritable() {
			return writable;
		}
	}
	
}
