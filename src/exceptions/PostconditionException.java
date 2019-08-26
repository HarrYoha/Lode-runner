package exceptions;


public class PostconditionException extends RuntimeException {
	private static final long serialVersionUID = 22352411315479861L;
	
	public PostconditionException(String message) {
		super(message);
	}
}
