package orange;

import java.util.*;

import orange.RequestException.ErrorCode;

//Processes exchanges
public final class Exchange implements Request {
	
	//the serial #'s of the compatible products
	private NavigableSet<SerialNumber> compatible;
	
	//sets the information about this exchange through Builder
	private Exchange(Builder builder) {
		compatible = copy(builder.getCompatibleProducts());
	}
	
	//delegate process to product
	@Override
	public void process(Product product, RequestStatus status) throws RequestException {
		try {
			product.process(this, status);
		} catch (ProductException e) {
			throw new RequestException(ErrorCode.PROCESSING_ERROR);
		}
	}
	
	//returns the serial #'s of the products compatible w/ request
	public NavigableSet<SerialNumber> getCompatibleProducts() {
		return compatible;
	}
	
	public NavigableSet<SerialNumber> copy(Set<SerialNumber> set) {
		NavigableSet<SerialNumber> copy = new TreeSet<SerialNumber>();
		copy.addAll(set);
		return copy;
	}
	
	public static class Builder {
		
		//the serial #'s of the compatible products
		private Set<SerialNumber> compatible = new TreeSet<SerialNumber>();
		
		//adds the serial # to the set of products compatible with this exchange
		public Builder addCompatibleProducts(SerialNumber serialNumber) {
			compatible.add(serialNumber);
			return this;
		}
		
		//returns the set of compatible products
		public Set<SerialNumber> getCompatibleProducts() {
			return compatible;
		}
		
		//returns an exchange w/ the given list of compatible products
		public Exchange build() {
			return new Exchange(this);
		}	
	}
}
