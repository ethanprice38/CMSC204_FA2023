import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Town_STUDENT_Test {
	
	Town coolTown = new Town("Cool town");
	Town coolTownCopy = new Town(coolTown);
	
	Town nextTown = new Town("Next town");
	Town farawayTown = new Town("Faraway");
	Town germantown = new Town("Germantown");
	
	@BeforeEach
	public void setUp() throws Exception {
		Town coolTown = new Town("Cool town");
		Town coolTownCopy = new Town(coolTown);
		
		Town nextTown = new Town("Next town");
		Town farawayTown = new Town("Faraway");
		Town germantown = new Town("Germantown");
	}

	@AfterEach
	public void tearDown() throws Exception {
		Town coolTown = null;
		Town coolTownCopy = null;
		
		Town nextTown = null;
		Town farawayTown = null;
		Town germantown = null;	
		}
	
	@Test
	public void testEquals() {
		assertTrue(coolTown.equals(coolTownCopy));
	}
	
	@Test
	public void testCompareTo() {
		assertTrue(coolTown.compareTo(coolTownCopy) == 0);
	}
	
	@Test
	public void testgetName() {
		assertTrue(germantown.getName() == "Germantown");
		assertTrue(farawayTown.getName() == "Faraway");
	}
	
	@Test
	public void testToString() {
		String testToStringNextTown = "Town Name: Next town\t Adjacent Towns: \n";
		
		assertEquals(nextTown.toString(), testToStringNextTown);
	}
	
}
