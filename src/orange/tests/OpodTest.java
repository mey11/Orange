package orange.tests;

import static org.junit.Assert.*;

import java.util.*;
import java.math.BigInteger;

import orange.Opod;
import orange.ProductType;
import orange.SerialNumber;

import org.junit.Test;

//tests the methods of Opod
public class OpodTest {

	@Test
	public void testGetProductType() {
		
		Optional<Set<String>> s = Optional.empty();
		//Opod o = new Opod(new SerialNumber(BigInteger.valueOf(6)), s);
		//assertEquals(ProductType.OPOD, o.getProductType());
	}
	
	@Test
	public void testIsValidSerialNumber() {
		
		//test valid number
		assertTrue(Opod.isValidSerialNumber(new SerialNumber(BigInteger.valueOf(10))));
		
		//test invalid number
		assertFalse(Opod.isValidSerialNumber(new SerialNumber(BigInteger.valueOf(6))));
	}

}