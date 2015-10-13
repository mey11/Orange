package orange;

import java.math.BigInteger;
import java.lang.Comparable;

//Properties of Orange products' serial numbers
public class SerialNumber implements Comparable<SerialNumber> {
	
	//this serial number
	private BigInteger serialNumber;
	
	//sets this serial number to the given value
	public SerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	//returns this serial number
	public BigInteger getSerialNumber() {
		return serialNumber;
	}
	
	//returns the greatest common divisor between this and the inputted serial number
	public BigInteger gcd(SerialNumber other) {
		return serialNumber.gcd(other.getSerialNumber());
	}
	
	//returns this serial number modulus the inputted serial number
	public BigInteger mod(SerialNumber other) {
		return serialNumber.mod(other.getSerialNumber());
	}
	
	//returns whether or not the given bit is set in this serial number
	public boolean testBit(int bit) {
		return serialNumber.testBit(bit);
	}
	
	//returns whether or not this serial number is even
	public boolean isEven() {
		return !isOdd();
	}
	
	//returns whether or not this serial number is odd
	public boolean isOdd() {
		//indexing of bit begins at zero from the right side
		return testBit(0);
	}
	
	//compares this SerialNumber with the specified SerialNumber for order
	@Override
	public int compareTo(SerialNumber serialNumber) {
		return this.serialNumber.compareTo(serialNumber.getSerialNumber());
	}
}