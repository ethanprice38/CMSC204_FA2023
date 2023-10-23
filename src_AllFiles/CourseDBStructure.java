import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class defining the structure of how the courses will be stored
 * in a database.
 * @author Ethan Price
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface {

	int hashTableSize = 0;
	LinkedList<CourseDBElement>[] hashTable;
	
	final double loadFactor = 1.5;
	
	/**
	 * Constructor that constructs a hashtable with buckets of a certain size depending upon
	 * the estimated number of courses to be stored
	 * @param numOfCourses
	 */
	CourseDBStructure(int numOfCourses) {
		
		int prime = 0;
		int i = 1;
		while (numOfCourses/loadFactor > prime || !isPrime(prime)) {
			prime = 4*i + 3;
			++i;
		}
		
		
		hashTableSize = prime;
		hashTable = new LinkedList[hashTableSize];
	}
	
	/**
	 * Test constructor that constructs a hashtable with buckets of a given size
	 * @param test
	 * @param size
	 */
	CourseDBStructure(String test, int size) {
		hashTableSize = size;
		hashTable = new LinkedList[hashTableSize];
	}
	
	/**
	 * Method to test for if a number is prime or not
	 * @param num to be tested
	 * @return True if prime, false if not
	 */
	public boolean isPrime(int num) {
		
		for(int i = 2; i <= num/2; i++)
	       {
	           if((num%i)==0)
	               return  false;
	       }
	       return true;
	}
	
	/**
	 * Method to add an element into the course database structure
	 */
	public void add(CourseDBElement element) {
		
		int toAdd = element.getHashCode();
		int bucketPlace = toAdd % hashTableSize;
		if (bucketPlace < 0) {
			bucketPlace *= -1;
		}
		
		
		
		
		if (hashTable[bucketPlace] == null) {
			hashTable[bucketPlace] = new LinkedList<CourseDBElement>();
			hashTable[bucketPlace].add(element);
			
			Object[] testArray = hashTable[bucketPlace].toArray();
			if (testArray.length > 1) {
				if (testArray[testArray.length - 1] == testArray[testArray.length - 2]) {
					hashTable[bucketPlace].removeLast();
				}
			}
		}
		
		if (hashTable[bucketPlace] != null) {
			for (int i = 0; i < hashTable[bucketPlace].size(); i++) {
				if (hashTable[bucketPlace].get(i).getCRN() == element.getCRN()) {
					if (hashTable[bucketPlace].get(i).credits != element.credits || hashTable[bucketPlace].get(i).ID != element.ID
							|| hashTable[bucketPlace].get(i).instructorName != element.instructorName || hashTable[bucketPlace].get(i).roomNumber != element.roomNumber) {
						hashTable[bucketPlace].set(i, element);
						return;
					}
				}
			}
			
			
			hashTable[bucketPlace].add(element);
			
			Object[] testArray = hashTable[bucketPlace].toArray();
			if (testArray.length > 1) {
				for (int i = 0; i < testArray.length - 1; i++) {
					if (testArray[i] == testArray[testArray.length - 1]) {
						hashTable[bucketPlace].removeLast();
					}
				}
			}
		}
	}

	/**
	 * Method that gets a course from the database given it's CRN
	 * @param CRN to find
	 * @return The course that was found
	 */
	public CourseDBElement get(int crn) throws IOException {
		
		int bucketLocation = Integer.toString(crn).hashCode() % hashTableSize;
		if (hashTable[bucketLocation] == null) {
			throw new IOException();
		}
		else{
			for (int i = 0; i < hashTable[bucketLocation].size(); i++) {
				if (hashTable[bucketLocation].get(i) != null) {
					CourseDBElement e = (CourseDBElement) hashTable[bucketLocation].get(i);
					if (e.getCRN() == crn) {
						return e;
					}
				}
			}
		}
			throw new IOException();
		
	}

	/**
	 * Method that returns an ArrayList of strings containing the elements of all the courses in the structure.
	 * @return ArrayList of strings
	 */
	public ArrayList<String> showAll() {
		
		ArrayList<String> allCourses = new ArrayList<String>();
		
		for (int i = 0; i < hashTableSize; i++) {
			if (hashTable[i] != null) {
				for (int j = 0; j < hashTable[i].size(); j++) {
					CourseDBElement e = (CourseDBElement) hashTable[i].get(j);
					allCourses.add("\n" + e.toString());
				}
			}
		}
		
		return allCourses;
	}

	/**
	 * Getter method for the size of the hashtable
	 * @return size of the hashtable
	 */
	public int getTableSize() {
		return hashTableSize;
	}

}
