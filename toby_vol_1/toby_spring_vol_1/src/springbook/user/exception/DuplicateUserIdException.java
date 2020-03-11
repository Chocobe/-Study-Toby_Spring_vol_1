package springbook.user.exception;

public class DuplicateUserIdException extends RuntimeException {
	private static final long serialVersionUID = 4944584195492701271L;

	public DuplicateUserIdException(Throwable cause) {
		super(cause);
	}
}
