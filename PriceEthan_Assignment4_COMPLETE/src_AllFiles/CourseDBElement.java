/**
 * Class that contains the elements of an indiviudal course
 * 
 * @author Ethan Price
 *
 */
public class CourseDBElement implements Comparable {
	
	String ID;
	int CRN;
	int credits;
	String roomNumber;
	String instructorName;
	
	/**
	 * Default null constructor
	 */
	CourseDBElement() {
		ID = "";
		CRN = 0;
		credits = 0;
		roomNumber = "";
		instructorName = "";
	}
	
	/**
	 * Constructor with parameters
	 * @param ID
	 * @param CRN
	 * @param credits
	 * @param Room number
	 * @param Instructor Name
	 */
	CourseDBElement(String ID, int CRN, int credits, String roomNumber, String instructorName) {
		this.CRN = CRN;
		this.ID = ID;
		this.credits = credits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
	}

	/**
	 * CRN setter method
	 * @param CRN
	 */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}
	 
	/**
	 * Hash code getter method
	 * @return Hash code
	 */
	public int getHashCode() {
		return Integer.toString(CRN).hashCode();
	}
	
	/**
	 * Gets a string of the elements of the course
	 * @return String containing the course's elements
	 */
	public String toString() {
		String finalString = "Course:" + ID + " CRN:" + CRN + " Credits:" + credits + " Instructor:" + instructorName + " Room:" + roomNumber;
		
		return finalString;
	}

	/**
	 * Method to compare the course to another
	 * @return 0 if equivalent, 1 if the courses CRN is larger, -1 if the courses CRN is smaller
	 */
	public int CompareTo(CourseDBElement a) {
		if (a.CRN == CRN) {
			return 0;
		}
		else if (a.CRN > CRN) {
			return 1;
		}
		else {
			return -1;
		}
	}

	/**
	 * Getter method for the CRN
	 * @return CRN
	 */
	public int getCRN() {
		return CRN;
	}

	/**
	 * Getter method for the course name
	 * @return Course name
	 */
	public String getID() {

		return ID;
	}

	/**
	 * Getter method for the room number
	 * @return room number as a string
	 */
	public String getRoomNum() {
		
		return roomNumber;
	}

}
