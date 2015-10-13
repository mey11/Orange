package orange.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.*;

import orange.AbstractProduct;
import orange.Opad;
import orange.Product;
import orange.ProductException;
import orange.ProductType;
import orange.SerialNumber;

import org.junit.Test;


public class OpadTest {

	@Test
	public void testGetProductType() {
		
		Optional<Set<String>> descTest = Optional.empty();
		
		//test no error (serial number valid)
		SerialNumber validSerial = new SerialNumber(BigInteger.valueOf(6));
		try {
			Product opad = AbstractProduct.make(ProductType.OPAD, validSerial, descTest);
			assertEquals(ProductType.OPAD, opad.getProductType());
			assertEquals(validSerial, opad.getSerialNumber());
			assertEquals(descTest, opad.getDescription());
		} catch (ProductException e) {
			//no exception should be thrown
			fail();
		}
		
		//test error (serial number invalid)
		SerialNumber invalidSerial = new SerialNumber(BigInteger.valueOf(5));
		try {
			Product opad = AbstractProduct.make(ProductType.OPAD, invalidSerial, descTest);
		} catch (ProductException e) {
			//passes as exception should be thrown
			assertTrue(true);
		}
	}
	
	@Test
	public void testIsValidSerialNumber() {
		
		//test valid number
		assertTrue(Opad.isValidSerialNumber(new SerialNumber(BigInteger.valueOf(100))));
		
		//test invalid number
		assertFalse(Opad.isValidSerialNumber(new SerialNumber(BigInteger.valueOf(7))));
	}		

}
