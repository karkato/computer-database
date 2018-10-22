package com.excilys.cdb.exceptions;

public class IdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IdException() {
		super("Cet identifiant n'existe pas dans nos données ! ");
	}

}
