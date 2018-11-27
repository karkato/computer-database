package exceptions;

public class PageNumberException extends Exception {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public PageNumberException( ) {
			super("Problème avec la page demandée");
		}
}
