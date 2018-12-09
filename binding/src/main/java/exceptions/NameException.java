package exceptions;

public class NameException extends DataException {

	private static final long serialVersionUID = 1L;

	public NameException() {
		super("Le nom est requis");
	}
}
