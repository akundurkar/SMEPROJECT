package in.adcast.exception;

import org.springframework.http.HttpStatus;

public class CustomGenericException extends Exception {
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

	public CustomGenericException(String errCode, String errMsg,HttpStatus httpStatus) {
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
