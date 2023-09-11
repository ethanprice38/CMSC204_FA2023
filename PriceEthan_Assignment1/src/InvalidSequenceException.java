
public class InvalidSequenceException extends Exception {
	
	public InvalidSequenceException() {
		super("The password cannot contain more than 2 of the same character in sequence");
	}

}
