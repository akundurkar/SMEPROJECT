package in.adcast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public final class MediaNotAprovedException extends CustomGenericException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5969059934447225115L;

	public MediaNotAprovedException(final String message) {
		super("MEDIA_NOT_APROVED", message, HttpStatus.EXPECTATION_FAILED);
	}
	
	public MediaNotAprovedException(String code,String message) {
		super(code,message,HttpStatus.EXPECTATION_FAILED);
	}

	
}
