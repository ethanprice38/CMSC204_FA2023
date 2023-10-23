import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {

	CourseDBStructure dataStructure = new CourseDBStructure(100);
	
	/**
	 * Adds to the course database based on the add method of the CourseDBStructure class
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement toAdd = new CourseDBElement(id, crn, credits, roomNum, instructor);
		dataStructure.add(toAdd);
	}

	/**
	 * Gets a course based on it's CRN and the get method of the CourseDBStructure class
	 */
	public CourseDBElement get(int crn) {
		
		try {
			return dataStructure.get(crn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	/**
	 * Reads in a file and adds the courses in the file to the database
	 */
	public void readFile(File input) throws FileNotFoundException {
		Scanner scan = new Scanner(input);
		String[] courseInfoSeparated;
		
		while (scan.hasNextLine()) {
			courseInfoSeparated = scan.nextLine().split(" ", 5);
			int crn = Integer.parseInt(courseInfoSeparated[1]);
			int credits = Integer.parseInt(courseInfoSeparated[2]);
			
			
			CourseDBElement toAdd = new CourseDBElement(courseInfoSeparated[0], crn, credits, courseInfoSeparated[3], courseInfoSeparated[4]);
			dataStructure.add(toAdd);
		}
		
		scan.close();
		
	}

	/**
	 * Returns an ArrayList of strings containing the courses in the database
	 */
	public ArrayList<String> showAll() {
		
		return dataStructure.showAll();
	}

}
