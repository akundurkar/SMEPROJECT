package in.adcast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;
	private HttpStatus httpStatus;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public CustomRuntimeException(String errCode, String errMsg,HttpStatus httpStatus) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.httpStatus=httpStatus;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	
	
	
	
}
