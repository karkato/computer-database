package exceptions;

public class NoPreviousPageException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoPreviousPageException() {
		super("Il n'y a pas de pages précédentes !");
	}
}
