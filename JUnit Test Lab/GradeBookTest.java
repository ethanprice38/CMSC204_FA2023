import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class GradeBookTest{
	
	private GradeBook firstGrade;
	private GradeBook secondGrade;
	

	@BeforeEach public void setUp() throws Exception {
		firstGrade = new GradeBook(5);
		secondGrade = new GradeBook(5);
		
		firstGrade.addScore(60);
		firstGrade.addScore(80);
		
		secondGrade.addScore(70);
		secondGrade.addScore(90);
	}
	
	
	@AfterEach public void tearDown() throws Exception {
		firstGrade = null;
		secondGrade = null;
	}
	
	@Test public void testAddScore() {
		assertTrue(firstGrade.toString().equals("60.0 80.0 "));
		assertTrue(secondGrade.toString().equals("70.0 90.0 "));
		
	}
	
	@Test public void testSum() {
		assertEquals(140,firstGrade.sum(), .0001);
		assertEquals(160, secondGrade.sum(), .0001);
	}
	
	@Test public void testMinimum() {
		assertEquals(60, firstGrade.minimum(), .0001);
		assertEquals(70, secondGrade.minimum(), .0001);
	}
	
	@Test public void testFinalScore() {
		assertEquals(80, firstGrade.finalScore(), .0001);
		assertEquals(90, secondGrade.finalScore(), .0001);
	}
}
