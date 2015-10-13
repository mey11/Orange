package orange;

import java.util.*;
import java.math.BigInteger;

public class Ophone extends AbstractProduct {
	
	//set the serial number and description with given values
	Ophone(SerialNumber serialNumber, Optional<Set<String>> description) {
		super(serialNumber, description);
	}
	
	//return this product type
	public ProductType getProductType() {
		return ProductType.OPHONE;
	}
	
	//returns validity of given serial number
	public static Boolean isValidSerialNumber(SerialNumber serialNumber) {
		//check whether even and the third bit is set
		return serialNumber.isOdd() && checkUpperBound(serialNumber);		
	}
	
	//checks whether an exchange can be performed
	public boolean isValidExchange(SerialNumber serialNumber, Exchange request) {
		//create the subset of serial #'s in request exclusively greater than the given serial #
		NavigableSet<SerialNumber> xTailSet = getXTailSet(request, serialNumber);
		//creates a subset from request with this serial # and the above set's average as exclusive bounds
		//if an element in this subset exists, exchange can occur
		return !(getExclusiveSubset(request, serialNumber, average(xTailSet)).isEmpty());
	}
	
	//returns a set with serial #'s strictly greater than this serial #
	public static NavigableSet<SerialNumber> getXTailSet(Exchange request, SerialNumber serialNumber) {
		return request.getCompatibleProducts().tailSet(serialNumber, false);
	}
	
	@Override
	public SerialNumber getExchangeResult(Exchange request) {
		//get the serial #'s greater than this serial # and less than the average serial # in compatible products
		NavigableSet<SerialNumber> xTailSet = getXTailSet(request, getSerialNumber());
		//creates a subset from request with this serial # and the above set's average as exclusive bounds
		NavigableSet<SerialNumber> xSubSet = getExclusiveSubset(request, getSerialNumber(), average(xTailSet));
		return xSubSet.last();
	}
	
	//checks whether a refund can be performed
	public boolean isValidRefund(BigInteger rma, SerialNumber serialNumber) {
		return (checkLeftShift(rma,serialNumber,1) || checkLeftShift(rma,serialNumber,2) || checkLeftShift(rma,serialNumber,3));
	}
	
	//checks that shifting rma to the left by the given shiftCount gives the given serial #
	public static boolean checkLeftShift(BigInteger rma, SerialNumber serialNumber, int shiftCount) {
		return (rma.shiftLeft(shiftCount).compareTo(serialNumber.getSerialNumber()) == 0);
	}
	

}

