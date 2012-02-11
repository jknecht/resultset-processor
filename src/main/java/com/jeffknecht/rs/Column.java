package com.jeffknecht.rs;

public interface Column {
	int getOrdinalPosition();
	String getCatalogName();
	String getColumnClassName();
	int getColumnDisplaySize();
	String getColumnLabel();
	String getColumnName();
	int getColumnType();
	String getColumnTypeName();
	int getPrecision();
	int getScale();
	String getSchemaName();
	String getTableName();
	boolean isAutoIncrement();
	boolean isCaseSensitive();
	boolean isCurrency();
	boolean isDefinitelyWritable();
	int isNullable();
	boolean isReadOnly();
	boolean isSearchable();
	boolean isSigned();
	boolean isWritable();
}
