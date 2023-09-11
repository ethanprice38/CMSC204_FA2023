import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Password Checker main methods
 * @author Ethan Price
 *
 */

public class PasswordCheckerUtility {
	
	/** 
	 * Method checks to see if two passwords are the same
	 * @param First password
	 * @param Second password(Should be same as first)
	 * @throws UnmatchedException
	 */
	
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}
	} // DONE
	
	/**
	 * Method checks to see if two passwords are the same, but also returns a boolean
	 * @param First password
	 * @param Second password (Should be same as first)
	 * @return True if the passwords are the same, false otherwise
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		
		if (password.equals(passwordConfirm)) {
			return true;
		}
		
		return false;
	} // DONE
	
	
	/**
	 * Method gets a list of passwords and passes back the invalid ones and why they are invalid
	 * @param List of passwords
	 * @return ArrayList of passwords that are invalid and reasons why
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		
		ArrayList<String> finalPasswords = new ArrayList<String>();
		String passwordsInvalid = "";
		
		for (int i = 0; i < passwords.size(); i++) {
			try {
				passwordsInvalid = passwords.get(i);
				if (!isValidPassword(passwordsInvalid)) {
					passwordsInvalid = passwordsInvalid;
				}
			}
			catch (Exception notValid) {
				finalPasswords.add(passwordsInvalid + " " + notValid.getMessage());
			}
		}
		
		return finalPasswords;
	}
	
	
	/**
	 * Checks to see if a password is between six and nine characters
	 * @param password to check
	 * @return True if the password is between six and nine characters, false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		
		if (password.length() >= 6 && password.length() <= 9) {
			return true;
		}
		
		return false;
	} // DONE
	
	
	/**
	 * Method to see if a password contains a digit
	 * @param password to check
	 * @return true if passsword has a digit, exception thrown otherwise
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException {
		
		boolean thereIsDigit = false;
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == '0' || password.charAt(i) == '1' || password.charAt(i) == '2' || 
					password.charAt(i) == '3' || password.charAt(i) == '4' || password.charAt(i) == '5' || 
					password.charAt(i) == '6' || password.charAt(i) == '7' || password.charAt(i) == '8' || password.charAt(i) == '9') {
				thereIsDigit = true;
			}
		}
		
		if (thereIsDigit == false) {
			throw new NoDigitException();
		}
		
		return thereIsDigit;
	} // DONE
	
	/**
	 * Method to check to see if a password contains a lower case alphabetical character
	 * @param password to be checked
	 * @return true if there is a lowercase letter, exception thrown otherwise
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		
		if (password.equals(password.toUpperCase())) {
			throw new NoLowerAlphaException();
		}
		
		return true;
	} // DONE
	
	/**
	 * Method to check to see if a password contains a special character
	 * @param password to be checked
	 * @return true if password contains a special character, exception thrown otherwise
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			return true;
		}
		else {
			throw new NoSpecialCharacterException();
		}
	}
	
	/**
	 * Method to check if a password contains an uppercase alphabetical character
	 * @param password to be checked
	 * @return true if the password contains an uppercase letter, exception thrown otherwise
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		
		if (password.equals(password.toLowerCase())) {
			throw new NoUpperAlphaException();
		}
		return true;
	} // DONE
	
	/**
	 * Method to check if a password contains less than six characters
	 * @param password to be checked
	 * @return true if it contains more than 6 characters, exception thrown otherwise
	 * @throws LengthException
	 */
	public static boolean isValidLength(String password) throws LengthException {
		
		if (password.length() < 6) {
			throw new LengthException();
		}
		return true;
	} // DONE
	
	/**
	 * Method to check if a password is valid (long enough, contains uppercase character, lowercase character, a digit,
	 * a special character, and has a valid sequence)
	 * @param password
	 * @return
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, 
	NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		
		if (isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password)
				&& hasSpecialChar(password) && noSameCharInSequence(password)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Method to check if a password is weak (has between six and nine characters) and throws an exception if
	 * it is.
	 * @param password to be checked
	 * @return false if the password isn't weak, exception thrown otherwise
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		if (hasBetweenSixAndNineChars(password)) {
			throw new WeakPasswordException();
		}
		
		return false;
	} // DONE

	/**
	 * Method to check if a password contains more than 2 characters in a sequence
	 * @param password to be checked
	 * @return true if there is no three same characters in a sequence, exception thrown otherwise
	 * @throws InvalidSequenceException
	 */
	public static boolean noSameCharInSequence(String password) throws InvalidSequenceException {
		
		for (int i = 0; i < password.length() - 2; i++) {
			if (password.charAt(i) == password.charAt(i+1)) {
				if (password.charAt(i) == password.charAt(i+2)) {
					throw new InvalidSequenceException();
				}
			}
		}
		
		return true;
	} // DONE
}
