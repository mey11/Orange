package orange;

import java.util.*;
import java.math.BigInteger;

public class Owatch extends AbstractProduct {

	//set the serial number and description with given values
	Owatch(SerialNumber serialNumber, Optional<Set<String>> description) {
		super(serialNumber, description);
	}
	
	//return this product type
	public ProductType getProductType() {
		return ProductType.OWATCH;
	}
	
	//returns validity of given serial number
	public static Boolean isValidSerialNumber(SerialNumber serialNumber) {
		//check whether odd and gcd of 630 in between 14 and 42
		return serialNumber.isOdd() && !checkUpperBound(serialNumber) && !checkLowerBound(serialNumber);
	}
	
	//valid if there is a compatible product who's serial # is strictly greater than this serial #
	public boolean isValidExchange(SerialNumber serialNumber, Exchange request) {
		return !(request.getCompatibleProducts().higher(serialNumber) == null);
	}
	
	@Override
	public SerialNumber getExchangeResult(Exchange request) {
		return request.getCompatibleProducts().higher(getSerialNumber());
	}

	
	//checks that the exclusive or of serial # and rma > 14
	public boolean isValidRefund(BigInteger rma, SerialNumber serialNumber) {
		return (serialNumber.getSerialNumber().xor(rma).compareTo(LOWER_BOUND) == 1);
	}
	


}