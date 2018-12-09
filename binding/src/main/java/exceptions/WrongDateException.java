package exceptions;

public class WrongDateException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongDateException() {
		super("La date de retrait du pc est plus ancienne que la date d'introduction ! ");
	}
}
