package orange.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;

import orange.SerialNumber;

import org.junit.Test;

//tests the methods in the SerialNumber class
public class SerialNumberTest {
	
	@Test
	public void testGetAndConstructor() {
		
		//test that constructor sets serial number to 100 and get returns it
		SerialNumber sn = new SerialNumber(BigInteger.valueOf(100));
		assertEquals(100, sn.getSerialNumber().intValue());
	}
	
	@Test
	public void testGcd() {
		
		//test that input 12 and 8 provides gcd of 4
		SerialNumber sn = new SerialNumber(BigInteger.valueOf(12));
		SerialNumber other = new SerialNumber(BigInteger.valueOf(8));
		assertEquals(4, sn.gcd(other).intValue());
	}
	
	@Test
	public void testMod() {
		
		//test that input 6 and 4 provides mod of 2
		SerialNumber sn = new SerialNumber(BigInteger.valueOf(6));
		SerialNumber other = new SerialNumber(BigInteger.valueOf(4));
		assertEquals(2, sn.mod(other).intValue());
	}
	
	@Test
	public void testTestBit() {
		
		//test that input 10 provides bits of 1010
		SerialNumber sn = new SerialNumber(BigInteger.valueOf(10));
		assertFalse(sn.testBit(0));
		assertTrue(sn.testBit(1));
		assertFalse(sn.testBit(2));
		assertTrue(sn.testBit(3));
	}
	
	@Test
	public void testIsEvenIsOdd() {
		
		//test that 100 is even and not odd
		SerialNumber sn = new SerialNumber(BigInteger.valueOf(100));
		assertTrue(sn.isEven());
		assertFalse(sn.isOdd());
	}


}
