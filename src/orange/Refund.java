package orange;

import java.math.BigInteger;

import orange.RequestException.ErrorCode;

public final class Refund implements Request {
	
	//this refund's RMA code
	private BigInteger rma;

	//sets the RMA code to that provided by the builder
	private Refund(Builder builder) {
		rma = builder.getRMA();
	}
	
	//delegate process to product
	@Override
	public void process(Product product, RequestStatus status)	throws RequestException {
		try {
			product.process(this, status);
		} catch (ProductException e) {
			throw new RequestException(ErrorCode.PROCESSING_ERROR);
		}
	}
	
	//returns this refund's RMA code
	public BigInteger getRMA() {
		return rma;
	}
	
	//A builder to provide the RMA code
	public static class Builder {
			
		//this RMA code initialized to null
		private BigInteger rma = null;
			
		//Sets RMA to given input
		public Builder setRMA(BigInteger rma) throws RequestException{
			if (rma == null)
				throw new RequestException(ErrorCode.INVALID_RMA);
			else {
				this.rma = rma;
				return this;
			}
		}
			
		//returns the builder's RMA code
		public BigInteger getRMA() {
				return rma;
		}
			
		//returns a refund with the given RMA code
		public Refund build() {
			return new Refund(this);
		}
	}
}
	

