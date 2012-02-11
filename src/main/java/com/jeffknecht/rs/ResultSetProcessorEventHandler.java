package com.jeffknecht.rs;

import java.sql.SQLException;

public interface ResultSetProcessorEventHandler {

	/**
	 * Event raised at the start of result set processing.
	 *  
	 * @param columnCount
	 */
	void startResultSet(int columnCount);

	/**
	 * Event raised at the start of a record.
	 * Record numbers start at 1.
	 * 
	 * @param recordNumber
	 */
	void startRecord(long recordNumber);

	/**
	 * Event raised after a column is read.
	 * 
	 * @param column
	 * @param value
	 */
	void column(Column column, Object value);

	/**
	 * Event raised at the end of a record.
	 * Record numbers start at 1.
	 * 
	 * @param recordNumber
	 */
	void endRecord(long recordNumber);

	/**
	 * Event raised at the end of the result set.
	 */
	void endResultSet(long recordCount);

	/**
	 * Event raised when a SQLException is encountered.
	 * Note that the SQLException will also be rethrown
	 * by the ResultSetProcessor after the event handler
	 * completes its work.
	 * 
	 * @param e
	 */
	void sqlException(SQLException e);

}
