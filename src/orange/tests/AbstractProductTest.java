package orange.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.*;

import orange.AbstractProduct;
import orange.Product;
import orange.ProductException;
import orange.ProductType;
import orange.SerialNumber;

import org.junit.Test;


public class AbstractProductTest {

	@Test
	public void testMake() {
		
		//test oPod
		Optional<Set<String>> desc = Optional.empty();
		//a valid serial number for oPod
		SerialNumber sn = new SerialNumber(BigInteger.valueOf(10));
		try {
			Product p = AbstractProduct.make(ProductType.OPOD, sn, desc);
			assertEquals(ProductType.OPOD, p.getProductType());
			assertEquals("oPod", p.getProductName());
		  //given serial # should be valid for oPod, method fails if thrown
		} catch (ProductException e) {
			fail(); 
		}
	}
	
	@Test
	public void testToString() {
		
		//test no description
		Optional<Set<String>> desc = Optional.empty();
		SerialNumber testSerial = new SerialNumber(BigInteger.valueOf(6));
		try {
			Product test = AbstractProduct.make(ProductType.OPAD, testSerial, desc);
			assertEquals("Product Name: oPad, Serial #: 6, Description: N/A", test.toString());
		} catch (ProductException e) {
			
		}
		
		//test one description with need for capitalization
		Set<String> st = new HashSet<String>();
		st.add("black");
		desc = Optional.of(st);
		try {
			Product test = AbstractProduct.make(ProductType.OPAD, testSerial, desc);
			assertEquals("Product Name: oPad, Serial #: 6, Description: Black", test.toString());
		} catch (ProductException e) {
			
		}
		
		//test multiple descriptions
		st.add("64 GB");
		st.add("$100");
		desc = Optional.of(st);
		try {
			Product test = AbstractProduct.make(ProductType.OPAD, testSerial, desc);
			assertEquals("Product Name: oPad, Serial #: 6, Description: Black, 64 GB, $100", test.toString());
		} catch (ProductException e) {
			
		}
	}
	
	@Test
	public void testEquals() {
		
		//test equal
		Optional<Set<String>> desc = Optional.empty();
		SerialNumber testSerial = new SerialNumber(BigInteger.valueOf(6));
		SerialNumber otherSerial = new SerialNumber(BigInteger.valueOf(100));
		try {
			Product test = AbstractProduct.make(ProductType.OPAD, testSerial, desc);
			Product other = AbstractProduct.make(ProductType.OPAD, testSerial, desc);
			assertTrue(test.equals(other));
		} catch (ProductException e) {
			
		}		
		
		//test not equal
		try {
			Product test = AbstractProduct.make(ProductType.OPAD, testSerial, desc);
			Product other = AbstractProduct.make(ProductType.OPAD, otherSerial, desc);
			assertFalse(test.equals(other));
		} catch (ProductException e) {
			
		}
	}
	
	@Test
	public void testHashCode() {
		
		//test equals
		Optional<Set<String>> desc = Optional.empty();
		BigInteger bi = BigInteger.valueOf(6);
		try {
			Product test = AbstractProduct.make(ProductType.OPAD, new SerialNumber(bi), desc);
			assertEquals(bi.hashCode(), test.hashCode());
		} catch (ProductException e) {
			
		}
	}
}

