package hw08;

public class DuplicateItemException extends RuntimeException {
	DuplicateItemException() {
		super();
	}
	DuplicateItemException(String errorMessage) {
		super(errorMessage);
	}
}