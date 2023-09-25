
public class ArraySum {
	
	public int sumOfArray (Integer[] a,int index) {
// Note that ‘a’ is an array of type Integer that is specified in the driver file, and ‘index’ is an integer 
// that shows which number in the array to sum next.  
//Use the driver class ArraySumDriver.java to populate your array and demonstrate that your method works. 
		int sum = 0;
		if (index == 0) {
			sum = a[index];
		}
		else {
			sum = a[index--] + sumOfArray(a, index--);
		}
		
		return sum;
	}

}
