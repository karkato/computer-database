package com.excilys.cdb.exceptions;

public class DateException extends DataException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4283778052586656551L;

	public DateException() {
		super("Les dates sont incohérentes");
	}

}
