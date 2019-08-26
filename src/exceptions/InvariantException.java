package exceptions;


public class InvariantException extends RuntimeException {
	private static final long serialVersionUID = 22352411315479861L;
	
	public InvariantException(String message) {
		super(message);
	}
}
