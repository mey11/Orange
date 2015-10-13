package orange;

import java.util.*;

//defines methods common to Orange products
public interface Product {
	
	//returns this product's serial number
	public SerialNumber getSerialNumber();
	
	//returns this product's type
	public ProductType getProductType();
	
	//returns this product's name
	public String getProductName();
	
	//returns this product's description
	public Optional<Set<String>> getDescription();
	
	//processes the exchange and sets the request status to the given code and result
	public void process(Exchange request, RequestStatus status) throws ProductException;
	
	//processes the refund and sets the request status to the given code and result
	public void process(Refund request, RequestStatus status) throws ProductException;
	
	
}
