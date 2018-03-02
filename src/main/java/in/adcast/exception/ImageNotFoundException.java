package in.adcast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class ImageNotFoundException extends CustomRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5969059934447225115L;

	public ImageNotFoundException() {
		super("IMAGE_NOT_FOUND", "Image does not exist",HttpStatus.NOT_FOUND);
	}
	public ImageNotFoundException(String code,String message) {
		super(code,message,HttpStatus.NOT_FOUND);
	}

	
}
