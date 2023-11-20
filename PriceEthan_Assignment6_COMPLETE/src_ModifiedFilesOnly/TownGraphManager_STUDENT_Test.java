import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[5];
		  
		  town[0] = "Town_" + 0;
		  graph.addTown(town[0]);
		  town[1] = "Town_" + 1;
		  graph.addTown(town[1]);
		  town[2] = "Town_" + 2;
		  graph.addTown(town[2]);
		  town[3] = "Town_" + 3;
		  graph.addTown(town[3]);
		  town[4] = "Town_" + 4;
		  graph.addTown(town[4]);
		  
		  graph.addRoad(town[0], town[4], 2, "Connects_0_and_4");
		  graph.addRoad(town[1], town[3], 2, "Connects_1_and_3");
		  graph.addRoad(town[0], town[2], 3, "Connects_0_and_2");
		  graph.addRoad(town[1], town[2], 2, "Connects_1_and_2");
		  graph.addRoad(town[1], town[4], 2, "Connects_1_and_4");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Connects_0_and_2", roads.get(0));
		assertEquals("Connects_0_and_4", roads.get(1));
		assertEquals("Connects_1_and_2", roads.get(2));
		assertEquals("Connects_1_and_3", roads.get(3));
		graph.addRoad(town[3], town[4], 1,"Connects_3_and_4");
		roads = graph.allRoads();
		assertEquals("Connects_0_and_2", roads.get(0));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Connects_1_and_4", graph.getRoad(town[1], town[4]));
		assertEquals("Connects_1_and_2", graph.getRoad(town[1], town[2]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Mystery_Town"));
		graph.addTown("Mystery_Town");
		assertEquals(true, graph.containsTown("Mystery_Town"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Disconnected_Town"));
		graph.addTown("Disconnected_Town");
		ArrayList<String> path = graph.getPath(town[0],"Disconnected_Town");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_15"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[0], town[2]));
		assertEquals(false, graph.containsRoadConnection(town[4], town[2]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Connects_0_and_2", roads.get(0));
		assertEquals("Connects_0_and_4", roads.get(1));
		assertEquals("Connects_1_and_2", roads.get(2));
		assertEquals("Connects_1_and_3", roads.get(3));
		assertEquals("Connects_1_and_4", roads.get(4));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[0], town[4]));
		graph.deleteRoadConnection(town[0], town[4], "Connects_0_and_4");
		assertEquals(false, graph.containsRoadConnection(town[0], town[4]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_0", roads.get(0));
		assertEquals("Town_1", roads.get(1));
		assertEquals("Town_2", roads.get(2));
		assertEquals("Town_3", roads.get(3));
		assertEquals("Town_4", roads.get(4));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[0],town[1]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_0 via Connects_0_and_4 to Town_4 2 mi",path.get(0).trim());
		  assertEquals("Town_4 via Connects_1_and_4 to Town_1 2 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[3],town[0]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_3 via Connects_1_and_3 to Town_1 2 mi",path.get(0).trim());
		  assertEquals("Town_1 via Connects_1_and_4 to Town_4 2 mi",path.get(1).trim());
		  assertEquals("Town_4 via Connects_0_and_4 to Town_0 2 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[2],town[3]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_2 via Connects_1_and_2 to Town_1 2 mi",path.get(0).trim());
		  assertEquals("Town_1 via Connects_1_and_3 to Town_3 2 mi",path.get(1).trim());
		  

	}

}
