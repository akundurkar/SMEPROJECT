package in.adcast.exception;

import org.springframework.http.HttpStatus;

public class TechnicalException  extends CustomRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5969059934447225115L;

	public TechnicalException(String message) {
		super("TECHNICAL_ERROR", message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
