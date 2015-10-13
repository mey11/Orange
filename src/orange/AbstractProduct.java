package orange;

import java.util.*;
import java.lang.Object;
import java.lang.StringBuilder;
import java.math.BigInteger;

import orange.RequestStatus.StatusCode;

//defines methods common to Orange products
public abstract class AbstractProduct implements Product {
	
	//the third bit of a BigInteger
	protected static final int THIRD_BIT = 2;
	
	//upper bound for valid serial # checks
	protected static final BigInteger UPPER_BOUND = BigInteger.valueOf(42);
	
	//lower bound for valid serial # checks
	protected static final BigInteger LOWER_BOUND = BigInteger.valueOf(14);
	
	//gcd comparison for valid serial # checks
	protected static final BigInteger GCD_CHECK = BigInteger.valueOf(630);
	
	//this product's serial number
	private SerialNumber serialNumber;
	
	//this product's description
	private Optional<Set<String>> description;

	//sets fields with given input
	public AbstractProduct(SerialNumber serialNumber, Optional<Set<String>> description) {
		this.serialNumber = serialNumber;
		this.description = description;
	}
	
	//returns this product's serialNumber
	public SerialNumber getSerialNumber() {
		return serialNumber;
	}
	
	//returns this product's name 
	public String getProductName() {
		return this.getProductType().getName();
	}
	
	//returns this product's description
	public Optional<Set<String>> getDescription() {
		return description;
	}
		
	//compares two products and returns equal if they have the same serial numbers
	@Override 
	public boolean equals(Object o) {
		if (o instanceof AbstractProduct) {
			AbstractProduct other = (AbstractProduct)o;
			return serialNumber.getSerialNumber().equals(other.getSerialNumber().getSerialNumber());
		}
		else
			return false;
	}
	
	//return hash code of this product's serial number
	@Override
	public int hashCode() {
		return serialNumber.getSerialNumber().hashCode();
	}
	
	//returns a string representation of this product
	@Override
	public String toString() {
		return stringProductName() + stringSerialNumber() + stringDescription();
	}
	
	//returns a string representation of the product name
	public String stringProductName() {
		//return N/A if no name is present
		if (getProductName() == null)
			return "Product Name: N/A";
		else
			return "Product Name: " + getProductName() + ", ";
	}
	
	//returns a string representation of the serial number
	public String stringSerialNumber() {
		//return N/A if no serial number is present
		if (serialNumber == null)
			return "Serial #: N/A, ";
		else
			return "Serial #: " + serialNumber.getSerialNumber().intValue() + ", ";
	}
	
	//returns a string representation of the description
	public String stringDescription() {
		if (description.isPresent()) {
			StringBuilder sb = new StringBuilder("Description: ");
			Set<String> temp = description.get();
			//add all descriptions to the end of sb
			for (String desc : temp) 
				//capitalize first letter of the string
				sb.append(desc.substring(0, 1).toUpperCase() + desc.substring(1) + ", ");
			//trim last ", " from sb
			sb.delete(sb.length() - 2, sb.length());
			return sb.toString();
		}
		else
			return "Description: N/A";
	}
	
	//create a product of the given type with the given serial # (if valid) and description
	public static Product make(ProductType productType, SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException {
		return productType.create(serialNumber, description);
	}
	
	
	//calculates the BigInteger average given a set of serial #'s and returns it as a new serial #
	public static SerialNumber average(NavigableSet<SerialNumber> set) {
		//add up all the serial #'s
		BigInteger total = BigInteger.ZERO;
		Iterator<SerialNumber> itr = set.iterator();
		while (itr.hasNext()) {
			total = total.add(itr.next().getSerialNumber());
		}
		//divide by amount of serial #'s to find average
		return new SerialNumber(total.divide(BigInteger.valueOf(set.size())));
	}
	
	//reads the compatible products of the given exchange and returns a exclusive subset with the given bounds
	public static NavigableSet<SerialNumber> getExclusiveSubset(Exchange request, SerialNumber lowerBound, SerialNumber UPPER_BOUND) {
		return request.getCompatibleProducts().subSet(lowerBound, false, UPPER_BOUND, false);
	}

	
	//checks if gcd > 42
	public static boolean checkUpperBound(SerialNumber serialNumber) {
		return (serialNumber.getSerialNumber().gcd(GCD_CHECK).compareTo(UPPER_BOUND) == 1);
	}
	
	//checks if gcd =< 14
	public static boolean checkLowerBound(SerialNumber serialNumber) {
		return !(serialNumber.getSerialNumber().gcd(GCD_CHECK).compareTo(LOWER_BOUND) == 1);
	}
	
	//processes the exchange and sets the request status to the given code and result
	public void process(Exchange request, RequestStatus status) throws ProductException {
		if (isValidExchange(getSerialNumber(), request)) {
			status.setStatusCode(StatusCode.OK);
			status.setResult(Optional.of(getExchangeResult(request).getSerialNumber()));
		}
		else {
			status.setStatusCode(StatusCode.FAIL);
			status.setResult(Optional.empty());
		}
	}
	
	//processes the refund and sets the request status to the given code and result
	public void process(Refund request, RequestStatus status) throws ProductException {
		status.setResult(Optional.empty());
		if (isValidRefund(request.getRMA(), getSerialNumber())) {
			status.setStatusCode(StatusCode.OK);
		}
		else {
			status.setStatusCode(StatusCode.FAIL);
		}
	}
	
	//method to be overwritten
	public boolean isValidExchange(SerialNumber serialNumber, Exchange request) {
		return this.isValidExchange(serialNumber, request);
	}
	
	//method to be overwritten
	public boolean isValidRefund(BigInteger rma, SerialNumber serialNumber) {
		return this.isValidRefund(rma, serialNumber);
	}
	
	//method to be overwritten
	public SerialNumber getExchangeResult(Exchange request) {
		return this.getExchangeResult(request);
	}
}



