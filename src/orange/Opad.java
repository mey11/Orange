package orange;

import java.util.*;
import java.math.BigInteger;

public class Opad extends AbstractProduct {
	
	private static final BigInteger GCD_BOUND = BigInteger.valueOf(12);
	
	//set the serial number and description with given values
	Opad(SerialNumber serialNumber, Optional<Set<String>> description) {
		super(serialNumber, description);
	}
	
	//return this product type
	public ProductType getProductType() {
		return ProductType.OPAD;
	}
	
	//returns validity of given serial number
	public static Boolean isValidSerialNumber(SerialNumber serialNumber) {
		//check whether even and the third bit is set
		return serialNumber.isEven() && serialNumber.testBit(THIRD_BIT);		
	}
	
	@Override
	//checks for any serial # less than this serial #
	public boolean isValidExchange(SerialNumber serialNumber, Exchange request) {
		return !(getExchangeResult(request) == null);
	}
	
	@Override
	public SerialNumber getExchangeResult(Exchange request) {
		return getLower(getSerialNumber(), request);
	}
	
	//returns the greatest value in compatible products that is strictly less than serial #
	public static SerialNumber getLower(SerialNumber serialNumber, Exchange request) {
		return request.getCompatibleProducts().lower(serialNumber);
	}
	
	@Override
	//check whether a refund can be performed
	public boolean isValidRefund(BigInteger rma, SerialNumber serialNumber) {
		//check if gcd >= 12
		return !(serialNumber.getSerialNumber().gcd(rma).compareTo(GCD_BOUND) == -1);
	}

}

