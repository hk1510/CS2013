package genericArray;

public class TooFewValuesException extends RuntimeException{
	public TooFewValuesException(String message) {
		super(message);
	}
}
