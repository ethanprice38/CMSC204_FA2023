import java.util.ArrayList;
import java.util.List;

public class Town implements Comparable<Town> {
	String name;
	ArrayList<Town> adjacentTowns;
	
	Town(String town) {
		name = town;
		adjacentTowns = new ArrayList<Town>();
	}
	
	Town(Town town) {
		name = town.name;
		adjacentTowns = town.adjacentTowns;
	}
	
	public void deleteAll() {
		name = null;
		adjacentTowns = null;
	}
	
	public int compareTo(Town town) {
		int result = name.compareTo(town.name);
		return result;
	}
	
	boolean equals(Town town) {
		if (town == null) {
			return false;
		}
		if (town.name == name) {
			return true;
		}
		return false;
	}
	
	String getName() {
		return name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		String adjacentTownString = "";
		
		for (int i = 0; i < adjacentTowns.size(); i++) {
			adjacentTownString += adjacentTowns.get(i).getName() + " ";
		}
		
		return "Town Name: " + name + "\t Adjacent Towns: " + adjacentTownString + "\n";
	}

}
