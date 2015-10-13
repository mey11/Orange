package orange;

public interface Request {

	//sets the request status and processes this request for the given product
	public void process(Product product, RequestStatus status) throws RequestException;
	
}
