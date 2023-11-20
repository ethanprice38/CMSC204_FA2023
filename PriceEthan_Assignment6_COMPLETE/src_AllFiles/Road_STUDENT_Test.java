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

public class Road_STUDENT_Test {
	Town v1 = new Town("v1");
	Town v2 = new Town("v2");
	Town v3 = new Town("v3");
	Town v4 = new Town("v4");
	Town v5 = new Town("v5");
	
	Road coolRoad = new Road(v1, v2, 1, "Cool road");
	Road coolRoadCopy = new Road(coolRoad);
	
	Road nextRoad = new Road(v3, v4, 1, "Next road");
	Road farawayRoad = new Road(v3, v5, 1, "Faraway");
	Road germantownRoadSecretlyFarawayRoad = new Road(v3, v5, 1, "Germantown road");
	
	@BeforeEach
	public void setUp() throws Exception {
		Road coolRoad = new Road(v1, v2, 1, "Cool road");
		Road coolRoadCopy = new Road(coolRoad);
		
		Road nextRoad = new Road(v3, v4, 1, "Next road");
		Road farawayRoad = new Road(v3, v5, 1, "Faraway");
		Road germantownRoad = new Road(v1, v5, 1, "Germantown road");
	}

	@AfterEach
	public void tearDown() throws Exception {
		Town v1 = null;
		Town v2 = null;
		Town v3 = null;
		Town v4 = null;
		Town v5 = null;
		
		Road coolRoad = null;
		Road coolRoadCopy = null;
		
		Road nextRoad = null;
		Road farawayRoad = null;
		Road germantownRoad = null;
		}
	
	@Test
	public void testCompareTo() {
		assertTrue(coolRoad.compareTo(coolRoadCopy) == 0);
	}
	
	@Test
	public void testContains() {
		assertTrue(nextRoad.contains(v3));
		assertTrue(farawayRoad.contains(v5));
		assertTrue(germantownRoadSecretlyFarawayRoad.contains(v3));
	}
	
	@Test
	public void testEquals() {
		assertTrue(germantownRoadSecretlyFarawayRoad.equals(farawayRoad));
	}

}
