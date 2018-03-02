package in.adcast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class AuthenticationFailedException extends CustomGenericException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5969059934447225115L;
	
	public AuthenticationFailedException(final String message) {
		super("INVALID_CREDENTIALS", message, HttpStatus.FORBIDDEN);
	}
	
	public AuthenticationFailedException(String code,String message) {
		super(code,message,HttpStatus.FORBIDDEN);
	}
	
	public AuthenticationFailedException(String code, String message,HttpStatus httpStatus) {
		super(code,message,httpStatus);
	}

	
}
