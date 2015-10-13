package orange;

import java.util.*;

import orange.ProductException.ErrorCode;

//define all of Orange's products
public enum ProductType {
	
	//for each enum, create method instantiates corresponding class object 
	//must pass serial # validity check
	OPOD("oPod") {
		public Product create(SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException {
			if (Opod.isValidSerialNumber(serialNumber))
				return new Opod(serialNumber, description);
			else
				throw new ProductException(OPOD, serialNumber, ErrorCode.INVALID_SERIAL_NUMBER);
		}
	}, OPAD("oPad") {
		public Product create(SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException {
			if (Opad.isValidSerialNumber(serialNumber))
				return new Opad(serialNumber, description);
			else
				throw new ProductException(OPAD, serialNumber, ErrorCode.INVALID_SERIAL_NUMBER);
		}
	}, OPHONE("oPhone") {
		public Product create(SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException {
			if (Ophone.isValidSerialNumber(serialNumber))
				return new Ophone(serialNumber, description);
			else
				throw new ProductException(OPHONE, serialNumber, ErrorCode.INVALID_SERIAL_NUMBER);
		}
	}, OWATCH("oWatch") {
		public Product create(SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException {
			if (Owatch.isValidSerialNumber(serialNumber)) 
				return new Owatch(serialNumber, description);
			else
				throw new ProductException(OWATCH, serialNumber, ErrorCode.INVALID_SERIAL_NUMBER);
		}
	}, OTV("oTv") {
		public Product create(SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException {
			if (Otv.isValidSerialNumber(serialNumber))
				return new Otv(serialNumber, description);
			else
				throw new ProductException(OTV, serialNumber, ErrorCode.INVALID_SERIAL_NUMBER);
		}
	};
	
	//the string representation of this product type's name
	private String name;
	
	public abstract Product create(SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException;
	
	//A constructor to set the value of name
	private ProductType(String name) {
		this.name = name;
	}
	
	//return this identifier's corresponding name
	public String getName() {
		return name;
	}
		
}
