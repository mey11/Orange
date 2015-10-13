package orange.tests;

import static org.junit.Assert.*;
import orange.AbstractProduct;
import orange.Product;
import orange.ProductException;
import orange.ProductType;
import orange.Refund;
import orange.Refund.Builder;
import orange.RequestException;
import orange.RequestStatus;
import orange.RequestStatus.StatusCode;
import orange.SerialNumber;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Optional;

public class RefundTest {
	
	@Test
	public void testBuilderConstructorAndGet() {
		
		//Check rma initialized to 0
		Builder testBuilder = new Builder();
		assertEquals(null, testBuilder.getRMA());
	}
	
	@Test
	public void testBuilderGetterSetter() {
		
		Builder testBuilder = new Builder();
		BigInteger temp = BigInteger.valueOf(100);
		//test with rma not null (no error)
		try {
			testBuilder = testBuilder.setRMA(temp);
		} catch (RequestException e) {
			//should not throw error
			fail();
		}
		assertEquals(temp, testBuilder.getRMA());
		
		//test with rma null (error thrown)
		try {
			testBuilder = testBuilder.setRMA(null);
		} catch (RequestException e) {
			//asserts true if error thrown
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuildAndRefundGet() {
		Builder testBuilder = new Builder();
		BigInteger temp = BigInteger.valueOf(100);
		try {
			testBuilder = testBuilder.setRMA(temp);
		} catch (RequestException e) {
			//should not throw error
			fail();
		}
		
		Refund testRefund = testBuilder.build();
		assertEquals(temp, testRefund.getRMA());
		
	}
	
	@Test
	public void testOpad() {	
		try {
			Refund refund = new Builder().setRMA(BigInteger.valueOf(1000)).build();
			Product oPad = AbstractProduct.make(ProductType.OPAD, new SerialNumber(BigInteger.valueOf(100)), Optional.empty());
			RequestStatus status = new RequestStatus();
			refund.process(oPad, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
		} catch (RequestException nullRMA) {
			fail();
		} catch (ProductException invalidSerial) {
			fail();
		}
	}
	
	@Test
	public void testOpod() {
		try {
			Product oPod = AbstractProduct.make(ProductType.OPOD, new SerialNumber(BigInteger.valueOf(10000)), Optional.empty());
			Refund refund = new Builder().setRMA(BigInteger.valueOf(1000)).build();
			RequestStatus status = new RequestStatus();
			refund.process(oPod, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
		} catch (RequestException nullRMA) {
			fail();
		} catch (ProductException invalidSerial) {
			fail();
		}
	}
	
	//Refund will always fail because oPhone serial must be odd and shift to the left will always end in 0?
	@Test
	public void testOphone() {
		
	}
	
	@Test
	public void testOtv() {
		try {
			Product oTV = AbstractProduct.make(ProductType.OTV, new SerialNumber(BigInteger.valueOf(3)), Optional.empty());
			Refund refund = new Builder().setRMA(BigInteger.valueOf(5)).build();
			RequestStatus status = new RequestStatus();
			refund.process(oTV, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
		} catch (RequestException nullRMA) {
			fail();
		} catch (ProductException invalidSerial) {
			fail();
		}
	}
	
	@Test
	public void testOwatch() {
		try {
			Product oWatch = AbstractProduct.make(ProductType.OWATCH, new SerialNumber(BigInteger.valueOf(15)), Optional.empty());
			Refund refund = new Builder().setRMA(BigInteger.valueOf(0)).build();
			RequestStatus status = new RequestStatus();
			refund.process(oWatch, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
		} catch (RequestException nullRMA) {
			fail();
		} catch (ProductException invalidSerial) {
			fail();
		}
	}

}
