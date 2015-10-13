package orange.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Optional;

import orange.AbstractProduct;
import orange.Exchange;
import orange.Product;
import orange.ProductException;
import orange.ProductType;
import orange.RequestException;
import orange.RequestStatus;
import orange.Exchange.Builder;
import orange.RequestStatus.StatusCode;
import orange.SerialNumber;

import org.junit.Test;

public class ExchangeTest {

	@Test
	public void testBuilder() {
		
		SerialNumber s1 = new SerialNumber(BigInteger.valueOf(10));
		SerialNumber s2 = new SerialNumber(BigInteger.valueOf(6));
		Builder testBuilder = new Builder();
		testBuilder = testBuilder.addCompatibleProducts(s1);
		testBuilder = testBuilder.addCompatibleProducts(s2);
		
		assertTrue(testBuilder.getCompatibleProducts().contains(s1));
		assertTrue(testBuilder.getCompatibleProducts().contains(s2));
	}

	
	@Test
	public void testImmutability() {
		
		//the compatible products' serial #'s
		SerialNumber s1 = new SerialNumber(BigInteger.valueOf(10));
		SerialNumber s2 = new SerialNumber(BigInteger.valueOf(6));
		//a serial # that should not be passed to instance of Exchange
		SerialNumber s3 = new SerialNumber(BigInteger.valueOf(5));
		Builder testBuilder = new Builder();
		testBuilder = testBuilder.addCompatibleProducts(s1);
		testBuilder = testBuilder.addCompatibleProducts(s2);
		
		Exchange testExchange = testBuilder.build();
		
		testBuilder = testBuilder.addCompatibleProducts(s3);
		
		assertTrue(testExchange.getCompatibleProducts().contains(s1));
		assertTrue(testExchange.getCompatibleProducts().contains(s2));
		assertFalse(testExchange.getCompatibleProducts().contains(s3));
		
	}

	//fails because 1048 is an invalid serial # for oPad
	@Test
	public void testOpad() {	
		try {
			new Builder().addCompatibleProducts(new SerialNumber(BigInteger.valueOf(1032))).addCompatibleProducts(new SerialNumber(BigInteger.valueOf(1244))).
				build().process(AbstractProduct.make(ProductType.OPAD, new SerialNumber(BigInteger.valueOf(1048)), Optional.empty()), new RequestStatus());
				//how to test though?
		} catch (RequestException e) {
			fail();
		} catch (ProductException invalidSerial) {
			fail();
		}
	}
	
	@Test
	public void testOpod() {
		try {
			SerialNumber firstComp = new SerialNumber(BigInteger.valueOf(14));
			SerialNumber secondComp = new SerialNumber(BigInteger.valueOf(15));
			Product oPod = AbstractProduct.make(ProductType.OPOD, new SerialNumber(BigInteger.valueOf(10000)), Optional.empty());
			Exchange exchange = new Builder().addCompatibleProducts(secondComp).addCompatibleProducts(firstComp).build();
			RequestStatus status = new RequestStatus();
			exchange.process(oPod, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
			assertEquals(firstComp.getSerialNumber(), status.getResult().get());
		} catch (RequestException e) {
			fail();
		} catch (ProductException invalidSerial) {
			fail();
		}
	}
	
	@Test
	public void testOphone() {
		try {
			SerialNumber willExchange = new SerialNumber(BigInteger.valueOf(70));
			Product oPhone = AbstractProduct.make(ProductType.OPHONE, new SerialNumber(BigInteger.valueOf(45)), Optional.empty());
			Exchange exchange = new Builder().addCompatibleProducts(new SerialNumber(BigInteger.valueOf(42))).addCompatibleProducts(new SerialNumber(BigInteger.valueOf(63))).addCompatibleProducts(willExchange).
					addCompatibleProducts(new SerialNumber(BigInteger.valueOf(105))).addCompatibleProducts(new SerialNumber(BigInteger.valueOf(90))).build();
			RequestStatus status = new RequestStatus();
			exchange.process(oPhone, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
			assertEquals(willExchange.getSerialNumber(), status.getResult().get());
		} catch (ProductException invalidSerial) {
			fail();
		} catch (RequestException e) {
			fail();
		}
	}
	
	@Test
	public void testOtv() {
		try {
			Product oTV = AbstractProduct.make(ProductType.OTV, new SerialNumber(BigInteger.valueOf(3)), Optional.empty());
			SerialNumber willExchange = new SerialNumber(BigInteger.valueOf(100));			RequestStatus status = new RequestStatus();
			Exchange exchange = new Builder().addCompatibleProducts(new SerialNumber(BigInteger.valueOf(1030))).
					addCompatibleProducts(new SerialNumber(BigInteger.valueOf(1000))).addCompatibleProducts(willExchange).build();
			exchange.process(oTV, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
			assertEquals(willExchange.getSerialNumber(), status.getResult().get());
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
			RequestStatus status = new RequestStatus();
			SerialNumber willExchange = new SerialNumber(BigInteger.valueOf(20));
			Exchange exchange = new Builder().addCompatibleProducts(willExchange).addCompatibleProducts(new SerialNumber(BigInteger.valueOf(30))).build();
			exchange.process(oWatch, status);
			assertEquals(StatusCode.OK, status.getStatusCode());
			assertEquals(willExchange.getSerialNumber(), status.getResult().get());
		} catch (RequestException nullRMA) {
			fail();
		} catch (ProductException invalidSerial) {
			fail();
		}
	}
}
