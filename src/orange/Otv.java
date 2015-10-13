package orange;

import java.util.*;
import java.math.BigInteger;

public class Otv extends AbstractProduct {
	
	private static final BigInteger ADD_FOR_BOUND = BigInteger.valueOf(1024);
	
	//set the serial number and description with given values
	Otv(SerialNumber serialNumber, Optional<Set<String>> description) {
		super(serialNumber, description);
	}
	
	//return this product type
	public ProductType getProductType() {
		return ProductType.OTV;
	}
	
	//returns validity of given serial number
	public static Boolean isValidSerialNumber(SerialNumber serialNumber) {
		//check whether even and the third bit is set
		return serialNumber.isOdd() && checkLowerBound(serialNumber);		
	}
	
	public boolean isValidExchange(SerialNumber serialNumber, Exchange request) {
		//create upper bound for calculation of average
		SerialNumber validityCheckBound = new SerialNumber(serialNumber.getSerialNumber().add(ADD_FOR_BOUND));
		//find the set of serial #'s strictly greater than serialNumber and <= validityCheckBound
		NavigableSet<SerialNumber> temp = getExclusiveToInclusiveSubset(request.getCompatibleProducts(), serialNumber, validityCheckBound);
		//find the st of serial #'s strictly greater than serialNumber and less than average
		//if empty, no compatible product exists
		return !(getExclusiveSubset(request, serialNumber, average(temp)).isEmpty());
	}
	
	//return a subset exclusively greater than lowerBound and inclusively less than upperBound 
	public static NavigableSet<SerialNumber> getExclusiveToInclusiveSubset(NavigableSet<SerialNumber> compatibleProducts, SerialNumber lowerBound, SerialNumber upperBound) {
		return compatibleProducts.subSet(lowerBound, false, upperBound, true);
	}
	
	@Override
	public SerialNumber getExchangeResult(Exchange request) {
		return getExclusiveSubset(request, getSerialNumber(), average(request.getCompatibleProducts())).last();
	}
	
	//checks that rma is positive
	public boolean isValidRefund(BigInteger rma, SerialNumber serialNumber) {
		return (rma.signum() == 1);
	}
	


}


