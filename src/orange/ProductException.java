package orange;

//A class to define errors
public class ProductException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductType productType;
	private SerialNumber serialNumber;
	private ErrorCode errorCode;
	
	public ProductException(ProductType productType, SerialNumber serialNumber, ErrorCode errorCode) {
		this.productType = productType;
		this.serialNumber = serialNumber;
		this.errorCode = errorCode;
	}
	
	public enum ErrorCode {
		INVALID_SERIAL_NUMBER, INVALID_PRODUCT_TYPE, UNSUPPORTED_OPERATION;
	}
	
	public ProductType getProductType() {
		return productType;
	}
	
	public String getProductName() {
		return productType.getName();
	}
	
	public SerialNumber getSerialNumber() {
		return serialNumber;
	}
	
	public ErrorCode errorCode() {
		return errorCode;
	}

}
