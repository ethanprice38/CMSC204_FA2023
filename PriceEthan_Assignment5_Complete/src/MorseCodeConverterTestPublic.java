 
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeConverterTestPublic {
	
	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
		assertEquals("hello world",converter1);
	}
	
	/**
	 * Testing for correct implementation of tree and traversal
	 */
	
	@Test
	public void testPrintTree() {	
		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}
	
	/**
	 * Testing for correct conversion of all characters using key phrase to hit all letters
	 */
	
	@Test
	public void testConvertMorseStringToEnglishString() {	
		
		String converter1 = MorseCodeConverter.convertToEnglish("- .... . / --.- ..- .. -.-. -.- / -... .-. --- .-- -. / ..-. --- -..- / .--- ..- -- .--. ... / --- ...- . .-. / - .... . / .-.. .- --.. -.-- / -.. --- --.");
		assertEquals("the quick brown fox jumps over the lazy dog", converter1);

	}
	@Test
	public void testConvertMorseFileToEnglishString() {	
		
		/*Make sure howDoILoveThee.txt is in the src directory for this 
		  test to pass
		*/
		File file = new File("src/howDoILoveThee.txt"); 
		try {
			assertEquals("how do i love thee let me count the ways", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	
	@Test 
	public void testConvertToEnglishStringStudent() {
		String converter = MorseCodeConverter.convertToEnglish("- .... .. ... / .. ... / .- / - . ... - / ..-. --- .-. / -- -.-- / ..-. .. -. .. ... .... . -.. / .--. .-. --- .--- . -.-. -");
		assertEquals("this is a test for my finished project", converter);
	}
	
	@Test
	public void testConvertMorseFileToEnglishStringStudent() {
		File file = new File ("src/myFavoriteSong.txt");
		try {
			assertEquals("I can be anything tell me what you need I can be your baby in real life sugar I can live in your dreams", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	

}
