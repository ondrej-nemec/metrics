package exception;

public class InvalidOpeationCostException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidOpeationCostException(Number cost, String rule) {
		super("Cost must be " + rule + ", " + cost + " given");
	}
}
