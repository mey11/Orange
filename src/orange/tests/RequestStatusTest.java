package orange.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Optional;

import orange.RequestStatus;
import orange.RequestStatus.StatusCode;

import org.junit.Test;

public class RequestStatusTest {

	@Test
	public void testConstructor() {
		
		RequestStatus test = new RequestStatus();
		assertEquals(Optional.empty(), test.getResult());
		assertEquals(StatusCode.UNKNOWN, test.getStatusCode());
	}
	
	@Test
	public void testGetSet() {
		
		RequestStatus test = new RequestStatus();
		Optional<BigInteger> resultTest = Optional.of(BigInteger.valueOf(10));
		test.setResult(resultTest);
		assertEquals(resultTest, test.getResult());
		
		test.setStatusCode(StatusCode.FAIL);
		assertEquals(StatusCode.FAIL, test.getStatusCode());
		
		test.setStatusCode(StatusCode.OK);
		assertEquals(StatusCode.OK, test.getStatusCode());
	}
}
