package orange;

public class RequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	
	public enum ErrorCode {
		INVALID_RMA, PROCESSING_ERROR;
	}
	
	public RequestException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
}
