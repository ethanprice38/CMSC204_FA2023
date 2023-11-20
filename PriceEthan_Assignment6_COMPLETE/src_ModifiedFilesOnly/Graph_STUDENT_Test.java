import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  town = new Town[5];
		  
		  town[0] = new Town("Town_0");
		  graph.addVertex(town[0]);
		  town[1] = new Town("Town_1");
		  graph.addVertex(town[1]);
		  town[2] = new Town("Town_2");
		  graph.addVertex(town[2]);
		  town[3] = new Town("Town_3");
		  graph.addVertex(town[3]);
		  town[4] = new Town("Town_4");
		  graph.addVertex(town[4]);
		  
		  graph.addEdge(town[0], town[4], 2, "Connects_0_and_4");
		  graph.addEdge(town[1], town[3], 2, "Connects_1_and_3");
		  graph.addEdge(town[0], town[2], 3, "Connects_0_and_2");
		  graph.addEdge(town[1], town[2], 2, "Connects_1_and_2");
		  graph.addEdge(town[1], town[4], 2, "Connects_1_and_4");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertTrue(new Road(town[0], town[4], 2, "Connects_0_and_4").equals(graph.getEdge(town[0], town[4])));
		assertTrue(new Road(town[0], town[2], 3, "Connects_0_and_2").equals(graph.getEdge(town[0], town[2])));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[3], town[4]));
		graph.addEdge(town[3], town[4], 1, "newRoad");
		assertEquals(true, graph.containsEdge(town[3], town[4]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Mystery Town");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[2], town[0]));
		assertEquals(false, graph.containsEdge(town[3], town[4]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Town_2")));
		assertEquals(false, graph.containsVertex(new Town("Town_25")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Connects_0_and_2", roadArrayList.get(0));
		assertEquals("Connects_0_and_4", roadArrayList.get(1));
		assertEquals("Connects_1_and_2", roadArrayList.get(2));
		assertEquals("Connects_1_and_3", roadArrayList.get(3));
		assertEquals("Connects_1_and_4", roadArrayList.get(4));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Connects_1_and_2", roadArrayList.get(0));
		assertEquals("Connects_1_and_3", roadArrayList.get(1));
		assertEquals("Connects_1_and_4", roadArrayList.get(2));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[1], town[2]));
		graph.removeEdge(town[1], town[2], 2, "Connects_1_and_2");
		assertEquals(false, graph.containsEdge(town[1], town[2]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[2]));
		graph.removeVertex(town[2]);
		assertEquals(false, graph.containsVertex(town[2]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[0]));
		assertEquals(true, roads.contains(town[1]));
		assertEquals(true, roads.contains(town[2]));
		assertEquals(true, roads.contains(town[3]));
		assertEquals(true, roads.contains(town[4]));
	}

	 @Test
	  public void testTown_0ToTown_1() {
		  String beginTown = "Town_0", endTown = "Town_1";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_0 via Connects_0_and_4 to Town_4 2 mi",path.get(0).trim());
			  assertEquals("Town_4 via Connects_1_and_4 to Town_1 2 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testTown3ToTown_0() {
		  String beginTown = "Town_3", endTown = "Town_0";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_3 via Connects_1_and_3 to Town_1 2 mi",path.get(0).trim());
			  assertEquals("Town_1 via Connects_1_and_4 to Town_4 2 mi",path.get(1).trim());
			  assertEquals("Town_4 via Connects_0_and_4 to Town_0 2 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  @Test
	  public void testTown_2ToTown_3() {
		  String beginTown = "Town_2", endTown = "Town_3";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_2 via Connects_1_and_2 to Town_1 2 mi",path.get(0).trim());
			  assertEquals("Town_1 via Connects_1_and_3 to Town_3 2 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}
