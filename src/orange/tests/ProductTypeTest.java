package orange.tests;

import static org.junit.Assert.*;
import orange.ProductType;

import org.junit.Test;

//test the methods in ProductType
public class ProductTypeTest {
	
	@Test
	public void testGetName() {
		
		//test oPod
		assertEquals("oPod", ProductType.OPOD.getName());
		
		//test oPad
		assertEquals("oPad", ProductType.OPAD.getName());
		
		//test oPhone
		assertEquals("oPhone", ProductType.OPHONE.getName());
		
		//test oWatch
		assertEquals("oWatch", ProductType.OWATCH.getName());
		
		//test oTv
		assertEquals("oTv", ProductType.OTV.getName());
	}

}
