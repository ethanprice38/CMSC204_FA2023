

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Student test file for CourseDBManager
 * @author Ethan Price
 */
public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface manageData = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		manageData = new CourseDBManager();
	}

	/**
	 * Set manageData reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		manageData = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			manageData.add("PHIL101",10121,3,"HU121","Philosophy Master");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		manageData.add("ASTR502",25126,2,"SC352","Astronomy Wizard");
		manageData.add("MATH182",10402,5,"SW216","Zinedinne Boudhra");
		manageData.add("ARTT126",68319,3,"ART102","Art King");
		ArrayList<String> list = manageData.showAll();
		assertEquals(list.get(2),"\nCourse:ASTR502 CRN:25126 Credits:2 Instructor:Astronomy Wizard Room:SC352");
	 	assertEquals(list.get(1),"\nCourse:MATH182 CRN:10402 Credits:5 Instructor:Zinedinne Boudhra Room:SW216");
		assertEquals(list.get(0),"\nCourse:ARTT126 CRN:68319 Credits:3 Instructor:Art King Room:ART102");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("ASTR502 25126 2 SC352 Astronomy Wizard");
			inFile.print("MATH182 10402 5 SW216 Zinedinne Boudhra");
			
			inFile.close();
			manageData.readFile(inputFile);
			assertEquals("ASTR502",manageData.get(25126).getID());
			assertEquals("MATH182",manageData.get(10402).getID());
			assertEquals("SW216",manageData.get(10402).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
