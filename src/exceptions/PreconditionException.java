package exceptions;


public class PreconditionException extends RuntimeException {
	private static final long serialVersionUID = 22352411315479861L;
	
	public PreconditionException(String message) {
		super(message);
	}
}
