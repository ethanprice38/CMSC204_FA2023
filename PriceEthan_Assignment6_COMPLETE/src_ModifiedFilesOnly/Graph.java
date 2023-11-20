import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {
	
	ArrayList<Town> towns;
	ArrayList<LinkedList<Road>> roads;
	
	Set<Town> unvisited;
	Map<Town, Integer> townDistances;
	Map<Town, Town> predecessors;
	
	Graph() {
		towns = new ArrayList<Town>();
		roads = new ArrayList<LinkedList<Road>>();
		
		unvisited = new HashSet<Town>();
		townDistances = new HashMap<Town, Integer>();
		predecessors = new HashMap<Town, Town>();
	}
	
	
	public int findTownIndex(Town town) {
		
		for (Town t : towns) {
			if (t.compareTo(town) == 0) {
				return towns.indexOf(t);
			}
		}
		return -1;
	}

	public Town getTown(String town) {
		for (Town t : towns) {
			if (t.getName() == town) {
				return t;
			}
		}
		return null;
	}
	
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		if (sourceVertex == null || destinationVertex == null) {
			return null;
		}
		
		int indexOfSource = findTownIndex(sourceVertex);
		if (indexOfSource == -1) {
			return null;
		}
		
		for (LinkedList<Road> list : roads) {
			for (Road road : list) {
				if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
					return road;
				}
			}
		}
		
		return null;
	}

	
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException {
		
		if (sourceVertex == null) {
			return null;
		}
		if (destinationVertex == null) {
			return null;
		}
		
		Road toBeAdded = new Road(sourceVertex, destinationVertex, weight, description);
		
		int indexOfSource = findTownIndex(sourceVertex);
		int indexOfDestination = findTownIndex(destinationVertex);
		
		if (indexOfSource == -1 || indexOfDestination == -1) {
			throw new IllegalArgumentException();
		}
		
		if (!roads.get(indexOfSource).contains(toBeAdded) && !roads.get(indexOfDestination).contains(toBeAdded)) {
			roads.get(indexOfSource).add(toBeAdded);
			roads.get(indexOfDestination).add(toBeAdded);
			
			sourceVertex.adjacentTowns.add(destinationVertex);
			destinationVertex.adjacentTowns.add(sourceVertex);
			
			return toBeAdded;
		}
		
		return null;
	}

	
	public boolean addVertex(Town town) {
		
		if (containsVertex(town)) {
			return false;
		}
		
		towns.add(town);
		roads.add(new LinkedList<Road>());
		
		return true;
	}

	
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		int indexOfSource = findTownIndex(sourceVertex);
		int indexOfDestination = findTownIndex(destinationVertex);
		if (indexOfSource == -1 || indexOfDestination == -1) {
			return false;
		}
		
		for (Road r : roads.get(indexOfSource)) {
			if (r.contains(destinationVertex)) {
				return true;
			}
		}
		
		return false;
	}

	
	public boolean containsVertex(Town town) {
		
		for (int i = 0; i < towns.size(); i++) {
			if (towns.get(i).compareTo(town) == 0){
				return true;
			}
		}
		
		return false;
	}

	
	public Set<Road> edgeSet() {
		Set<Road> edgeSet = new HashSet<Road>();
		
		for (LinkedList<Road> edges : roads) {
			for (Road r : edges) {
				Road toBeAdded = r;
				if (!edgeSet.contains(toBeAdded)) {
					edgeSet.add(toBeAdded);
				}
			}
		}
		
		return edgeSet;
	}

	
	public Set<Road> edgesOf(Town town) {
		Set<Road> edges = new HashSet<Road>();
		int townIndex = findTownIndex(town);
		
		for (Road r : roads.get(townIndex)) {
			edges.add(r);
		}
		
		return edges;
	}

	
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String name) {
		if (!towns.contains(sourceVertex) || !towns.contains(destinationVertex)) {
			return null;
		}
		int indexOfSource = findTownIndex(sourceVertex);
		int indexOfDestination = findTownIndex(destinationVertex);
		if (indexOfSource == -1 || indexOfDestination == -1) {
			return null;
		}
		Road toBeDeleted = null;
		
		for (Road road : roads.get(indexOfSource)) {
			if (road.getWeight() >= 1 && road.getName() != null) {
				if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
					toBeDeleted = road;
					roads.get(indexOfSource).remove(toBeDeleted);
					break;
				}
			}
		}
		
		
		return toBeDeleted;
	}

	
	public boolean removeVertex(Town town) {
	
		if (!towns.contains(town)) {
			return false;
		}
		

		for (int i = 0; i < town.adjacentTowns.size(); i++) {
			Road toBeRemoved = getEdge(town, town.adjacentTowns.get(i));
			removeEdge(town, town.adjacentTowns.get(i), toBeRemoved.getWeight(), toBeRemoved.getName());
		}
		
		int indexOfTown = findTownIndex(town);
		roads.remove(indexOfTown);
		towns.remove(indexOfTown);
		
		
		return true;
	}
	
	public Set<Town> vertexSet() {
		Set<Town> vertexSet = new HashSet<Town>();
		
		for (int i = 0; i < towns.size(); i++) {
			vertexSet.add(towns.get(i));
		}
		
		return vertexSet;
	}

	
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {

		
		dijkstraShortestPath(sourceVertex);
		
		ArrayList<Town> shortest = new ArrayList<Town>(predecessors.size());
		ArrayList<String> shortestPath = new ArrayList<String>();
		
		shortest.add(destinationVertex);
		
		Town current = destinationVertex;
		
		while (current != sourceVertex) {
			Town town = predecessors.get(current);
			predecessors.remove(current);
			shortest.add(town);
			current = town;
		}
		
		Collections.reverse(shortest);
		
		for (int i = 0; i < shortest.size() - 1; i++) {
			Road edge = getEdge(shortest.get(i), shortest.get(i+1));
			shortestPath.add(shortest.get(i).getName() + " via " + edge.getName() + " to " + shortest.get(i + 1).getName() + " " + edge.getWeight() + " mi");
		}
		predecessors.clear();
		shortest.clear();
		return shortestPath;
	}

	
	public void dijkstraShortestPath(Town sourceVertex) {
		for (Town t : towns) {
			int distance = Integer.MAX_VALUE;
			
			townDistances.put(t, distance);
			unvisited.add(t);
		}
		
		townDistances.put(sourceVertex, 0);
		
		while (!unvisited.isEmpty()) {
			int distance = Integer.MAX_VALUE;
			Town minDistanceTown = null;
			for (Town p : unvisited) {
				if (townDistances.get(p) < distance) {
					minDistanceTown = p;
				}
			}
			unvisited.remove(minDistanceTown);
			
			Set<Town> neighbors = new HashSet<Town>();
			
			for (Road edge : edgeSet()) {
				if (edge.endpointOne.equals(minDistanceTown)) {
					neighbors.add(edge.endpointTwo);
				}
				if (edge.endpointTwo.equals(minDistanceTown)) {
					neighbors.add(edge.endpointOne);
				}
			}
			
			for (Town town : neighbors) {
				if (unvisited.contains(town)) {
					int alternatePath = townDistances.get(minDistanceTown) + getEdge(town, minDistanceTown).getWeight();
					if (alternatePath < townDistances.get(town)) {
						townDistances.put(town, alternatePath);
						predecessors.put(town, minDistanceTown);
					}
				}
			}
		}
	}

}
