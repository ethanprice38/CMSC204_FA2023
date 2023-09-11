
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Ethan Price
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	ArrayList<String> testInvalidPasswords;
	String tooShort;
	String longEnough;
	
	String noUpperAlpha;
	String hasUpperAlpha;
	
	String noLowerAlpha;
	String hasLowerAlpha;
	
	String weakPassword;
	String strongPassword;
	
	String invalidSequence;
	String validSequence;
	
	String noDigit;
	String hasDigit;
	
	String veryValid;
	String notValid;

	
	/**
	 * Sets up strings for passwords that pass and fail given tests, and intializes them
	 * into an array
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		String tooShort = "Sa1!";
		String longEnough = "Sallyaba1!";
		
		String noUpperAlpha = "nouppercase1!";
		String hasUpperAlpha = "Yesuppercase1!";
		
		String noLowerAlpha = "IMLOUD@NIGHT22";
		String hasLowerAlpha = "Imquiet@night55";
		
		String weakPassword = "Weakbut2!";
		String strongPassword = "Strongerthanyou5!";
		
		String invalidSequence = "oooooaaA5!";
		String validSequence = "ooaabbGG6&";
		
		String noDigit = "Ineedanumber*";
		String hasDigit = "Ihaveanumber7*";
		
		String veryValid = "TheBestPassword7Ever!";
		String notValid = "a";
		
		String p[] = {tooShort + longEnough + noUpperAlpha + hasUpperAlpha + noLowerAlpha + hasLowerAlpha +
				weakPassword + strongPassword + invalidSequence + validSequence + noDigit + hasDigit +
				veryValid + notValid};
		
		testInvalidPasswords = new ArrayList<String>();
		testInvalidPasswords.addAll(Arrays.asList(p));
		}
		
		

	@After
	public void tearDown() throws Exception {
	
		testInvalidPasswords = null;
	
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("Sa1!"));
			assertTrue("Didn't throw length exception",false);
		}
		catch(LengthException e)
		{
			assertTrue("Threw length exception",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw a different exception than expected",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Sallyaba1!"));
			assertTrue("Threw no exception, passed!", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception, failed.", false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("nouppercase1!"));
			assertTrue("Didn't throw NoUpperAlpha exception",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Threw NoUpperAlpha exception",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw a different exception than expected",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Yesuppercase1!"));
			assertTrue("Threw no exception, passed!", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception, failed.", false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("IMLOUD@NIGHT22"));
			assertTrue("Didn't throw noLowerAlpha exception",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Threw noLowerAlpha exception",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw a different exception than expected",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Imquiet@night55"));
			assertTrue("Threw no exception, passed!", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception, failed.", false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			boolean isWeak = PasswordCheckerUtility.isValidPassword("Weakbut2!");
			assertTrue("Did not throw WeakPassword Exception",false);
		}
		catch(WeakPasswordException e)
		{
			assertTrue("Threw WeakPassword exception",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw a different exception than expected",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Strongerthanyou5!"));
			assertTrue("Threw no exception, passed!", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception, failed.", false);
		}
	}
	
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("oooooaaA5!"));
			assertTrue("Didn't throw InvalidSequence exception",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Threw InvalidSequence exception",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw a different exception than expected",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("ooaabbGG6&"));
			assertTrue("Threw no exception, passed!", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception, failed.", false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("Ineedanumber*"));
			assertTrue("Didn't throw NoDigit exception",false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Threw NoDigit exception",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw a different exception than expected",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Ihaveanumber7!"));
			assertTrue("Threw no exception, passed!", true);
		}
		catch (Exception e) {
			assertTrue("Threw an exception, failed.", false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("TheBestPassword7Ever!"));
			assertTrue("Threw no exception, passed!",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw a different exception than expected",false);
		}
		
		try {
			assertFalse(PasswordCheckerUtility.isValidPassword("a"));
			assertTrue("Threw no exception, failed.", false);
		}
		
		catch (Exception e)
		{
			assertTrue("Threw an exception, passed!", true);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(testInvalidPasswords);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "Sa1!");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least 6"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "nouppercase1!");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("uppercase alphabetic"));
		
		 
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "IMLOUD@NIGHT22");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase alphabetic"));
		
		scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "Weakbut2!");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("ok but weak"));
		
		scan = new Scanner(results.get(4));  
		assertEquals(scan.next(), "oooooaaA5!");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("contain more than 2 of the same") );
		
		
		scan = new Scanner(results.get(5));  
		assertEquals(scan.next(), "Ineedanumber*");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least one digit") );
		
		scan = new Scanner(results.get(6));  
		assertEquals(scan.next(), "ApplesxxxYYzz#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least 6") );
		
		scan.close();
	}
	
}
