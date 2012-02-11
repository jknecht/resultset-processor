package com.jeffknecht.rs;

import java.sql.SQLException;

public class ResultSetProcessorEventAdapter implements ResultSetProcessorEventHandler {

	public void startResultSet(int columnCount) {
	}

	public void startRecord(long recordNumber) {
	}

	public void column(Column column, Object value) {
	}

	public void endRecord(long recordNumber) {
	}

	public void endResultSet(long recordCount) {
	}

	public void sqlException(SQLException e) {
	}

	
}
