package orange;
import java.util.*;
import java.math.BigInteger;

//defines methods common to Opods
public final class Opod extends AbstractProduct {
	
	private static final BigInteger GCD_BOUND = BigInteger.valueOf(24);
	
	//set the serial number and description with given values
	Opod(SerialNumber serialNumber, Optional<Set<String>> description) {
		super(serialNumber, description);
	}
	
	//return this product type
	public ProductType getProductType() {
		return ProductType.OPOD;
	}
	
	//returns validity of given serial number
	public static Boolean isValidSerialNumber(SerialNumber serialNumber) {
		//check whether even and the third bit is set
		return serialNumber.isEven() && !serialNumber.testBit(THIRD_BIT);		
	}
	
	@Override
	//valid if compatible products exist
	public boolean isValidExchange(SerialNumber serialNumber, Exchange request) {
		return !request.getCompatibleProducts().isEmpty();
	}
	
	@Override
	public SerialNumber getExchangeResult(Exchange request) {
		return request.getCompatibleProducts().first();
	}

	//valid if gcd of serial # and rma is at least 24
	public boolean isValidRefund(BigInteger rma, SerialNumber serialNumber) {
		//valid if gcd >= 24
		return !(serialNumber.getSerialNumber().gcd(rma).compareTo(GCD_BOUND) == -1);
	}
	

}

