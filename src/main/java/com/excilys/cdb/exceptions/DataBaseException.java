package com.excilys.cdb.exceptions;

public class DataBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataBaseException() {
		super("Erreur interne dû à la base de données");
	}

}
