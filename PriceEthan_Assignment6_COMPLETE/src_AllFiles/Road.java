
public class Road implements Comparable<Road>{
	int weight;
	Town endpointOne;
	Town endpointTwo;
	String name;
	
	Road(Town town1, Town town2, int weight, String name) {
		endpointOne = town1;
		endpointTwo = town2;
		this.weight = weight;
		this.name = name;
	}
	
	Road(Town town1, Town town2, String name) {
		endpointOne = town1;
		endpointTwo = town2;
		this.name = name;
		weight = 1;
	}
	
	Road(Road road) {
		endpointOne = road.endpointOne;
		endpointTwo = road.endpointTwo;
		weight = road.weight;
		name = road.name;
	}
	
	public void deleteAll() {
		endpointOne = null;
		endpointTwo = null;
		weight = 0;
		name = null;
	}
	
	public int compareTo(Road road) {
		int result = (name.compareTo(road.name));
		return result;
	}
	
	boolean contains(Town town) {
		if (town.equals(endpointOne) || town.equals(endpointTwo)) {
			return true;
		}
		return false;
	}
	
	boolean equals(Road road) {
		if (endpointOne.equals(road.endpointOne) || endpointOne.equals(road.endpointTwo) &&
				endpointTwo.equals(road.endpointOne) || endpointTwo.equals(road.endpointTwo)){
			return true;
		}
		return false;
	}
	
	Town getDestination() {
		return endpointTwo;
	}
	
	String getName() {
		return name;
	}
	
	Town getSource() {
		return endpointOne;
	}
	
	int getWeight() {
		return weight;
	}
	
	public String toString() {
		
		return "";
	}

	

}
