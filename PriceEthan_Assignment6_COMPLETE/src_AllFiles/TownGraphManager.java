import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph townGraph;
	
	TownGraphManager() {
		townGraph = new Graph();
	}
	
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		
		Set<Town> townSet = townGraph.vertexSet();
		Town town1Copy = new Town(town1);
		Town town2Copy = new Town(town2);
		
		if (containsTown(town1) && containsTown(town2)) {
			for (Town x : townSet) {
				if (town1Copy.getName().equalsIgnoreCase(x.getName())) {
					town1Copy = x;
				}
				if (town2Copy.getName().equalsIgnoreCase(x.getName())) {
					town2Copy = x;
				}
			}
		}
		else {
			return false;
		}
		
		townGraph.addEdge(town1Copy, town2Copy, weight, roadName);
		
		return true;
	}

	
	public String getRoad(String town1, String town2) {
		Town town1Copy = getTown(town1);
		Town town2Copy = getTown(town2);
		if (!(town1Copy == null) && !(town2Copy == null)) {
			return townGraph.getEdge(town1Copy, town2Copy).getName();
		}
	
		return null;
	}
	
	public Road getRoad(Town town1, Town town2) {
		if (townGraph.getEdge(town1, town2) != null) {
			return townGraph.getEdge(town1, town2);
		}
	
		return null;
	}

	
	public boolean addTown(String v) {
		Town town = new Town(v);
		return townGraph.addVertex(town);
	}

	
	public Town getTown(String name) {
		Town test = new Town(name);
		Set<Town> townSet = townGraph.vertexSet();
		
		for (Town x : townSet) {
			if (x.getName().equalsIgnoreCase(test.getName())) {
				return x;
			}
		}
		
		return null;
	}

	
	public boolean containsTown(String v) {
		Town town = new Town(v);
		Set<Town> townSet = townGraph.vertexSet();
		
		for (Town check : townSet) {
			if (town.getName().equalsIgnoreCase(check.getName())) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsRoadConnection(String town1, String town2) {
		Town town1Copy = getTown(town1);
		Town town2Copy = getTown(town2);
		
		for (Road road : townGraph.edgesOf(town1Copy)) {
			if (road.contains(town2Copy)) {
				return true;
			}
		}
		
		return false;
	}

	
	public ArrayList<String> allRoads() {
		
		Set<Road> roadSet = townGraph.edgeSet();
		ArrayList<String> roadNames = new ArrayList<String>();
		
		for (Road x : roadSet) {
			roadNames.add(x.getName());
		}
		Collections.sort(roadNames);
		
		return roadNames;
	}

	
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town town1Copy = new Town(town1);
		Town town2Copy = new Town(town2);
		
		if (townGraph.removeEdge(town1Copy, town2Copy, townGraph.getEdge(town1Copy, town2Copy).getWeight(), 
				road) != null) {
			townGraph.removeEdge(town1Copy, town2Copy, townGraph.getEdge(town1Copy, town2Copy).getWeight(), road);
			townGraph.removeEdge(town1Copy, town2Copy, townGraph.getEdge(town1Copy, town2Copy).getWeight(), road);
			townGraph.removeEdge(town1Copy, town2Copy, townGraph.getEdge(town1Copy, town2Copy).getWeight(), road);
			
			return true;
		}
		else {
			return false;
		}
	}

	
	public boolean deleteTown(String toBeDeleted) {
		return townGraph.removeVertex(getTown(toBeDeleted));
	}

	
	public ArrayList<String> allTowns() {
		
		ArrayList<String> allTowns = new ArrayList<String>();
		for (Town t : townGraph.vertexSet()) {
			allTowns.add(t.getName());
		}
		Collections.sort(allTowns);
		
		return allTowns;
	}

	
	public ArrayList<String> getPath(String town1, String town2) {
		boolean town1ContainsRoads = false;
		boolean town2ContainsRoads = false;
		for (Road r : townGraph.edgeSet()) {
			if (r.contains(new Town(town1))) {
				town1ContainsRoads = true;
			}
			if (r.contains(new Town(town2))) {
				town2ContainsRoads = true;
			}
		}
		if (town1ContainsRoads && town2ContainsRoads)
			return townGraph.shortestPath(getTown(town1), getTown(town2));
		else {
			ArrayList<String> nullPath = new ArrayList<String>();
			return nullPath;
			
		}
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
		try {
			Scanner scan = new Scanner(selectedFile);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] lineSplit = line.split("[,;]");
				
				Town sourceTown = new Town(lineSplit[2]);
				townGraph.addVertex(sourceTown);
				Town destinationTown = new Town(lineSplit[3]);
				townGraph.addVertex(destinationTown);
				
				townGraph.addEdge(sourceTown, destinationTown, Integer.parseInt(lineSplit[1]), lineSplit[0]);
			}
			scan.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		
		return;
	}

}
 