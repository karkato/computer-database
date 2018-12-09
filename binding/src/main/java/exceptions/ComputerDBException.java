package exceptions;

public class ComputerDBException  extends RuntimeException{


	private static final long serialVersionUID = 2224397985096682193L;


	public ComputerDBException() {
		super();
	}


	public ComputerDBException(String message) {
		super(message);
	}

	public ComputerDBException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComputerDBException(Throwable cause) {
		super(cause);
	}

}
