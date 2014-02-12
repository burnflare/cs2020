/**
 * 
 */
package cs2020;

public class IntPair implements Comparable<IntPair>{
	private int x;
	
	/**
	 * Store our 2 digit number
	 * @param x
	 */
	public IntPair(int x) {
		this.x = x;
	}
	
	/**
	 * Takes in a 2 digit number and spits out the first digit
	 * @param The 2 digit number to split
	 * @return First digit
	 */
	private static Integer firstDigit(int n) {
		while (n < -9 || 9 < n) n /= 10;
		return Math.abs(n);
	}
	
	/**
	 * When SortInvestigator sorts IntPairs, we only want it to
	 * sort with regard to it's first digit
	 * @param obj - Object to compare with
	 * @return -1 | 0 | 1 accordingly
	 */
	public int compareTo(IntPair obj) {
		return (firstDigit(this.x).compareTo(firstDigit(obj.x)));
	}
	
	/**
	 * When SortInvestigator compares 2 arrays, we want to 
	 * compare the entire 2 digit number this time. This is
	 * how we are checking if the sort algorithm is stable
	 * or not
	 * @param obj - Object to compare with
	 * @return bool whether it's equal or not
	 */
	public boolean equals(IntPair obj) {
		return (this.x==obj.x);
	}
}
