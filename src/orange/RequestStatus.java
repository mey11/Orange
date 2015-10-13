package orange;

import java.math.BigInteger;
import java.util.Optional;

//Contains information on status codes and results
public class RequestStatus {
	
	private StatusCode statusCode;

	private Optional<BigInteger> result;
	
	//Enum for three kinds of status codes
	public enum StatusCode {
		UNKNOWN, OK, FAIL;
	}
	
	//Sets status to unknown and result empty
	public RequestStatus() {
		statusCode = StatusCode.UNKNOWN;
		result = Optional.empty();
	}
	
	//Getter-Setters
	
	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}
	
	public StatusCode getStatusCode() {
		return statusCode;
	}
	
	public void setResult(Optional<BigInteger> result) {
		this.result = result;
	}
	
	public Optional<BigInteger> getResult() {
		return result;
	}
	
}
