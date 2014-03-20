/**
 * 
 */
package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author vishnu
 *
 */
public class PremPS5 {

	/**
	 * 
	 */
	public PremPS5() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String[] arr ;
		try {
			FileReader dataFile = new FileReader(args[0]);
	        BufferedReader bufferedDataFile = new BufferedReader(dataFile);
	        String size = bufferedDataFile.readLine();
	        String line;
	        int i = 0;
	        
	        arr = new String[Integer.parseInt(size)];
	        
	        while ((line = bufferedDataFile.readLine()) != null) {
				arr[i] = line;
				i++;
			}
	        countPermurations(arr);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void countPermurations(String[] arr) {
//		arr = new String[7];
//		arr[0] = "BCDEFGH";
//		arr[1] = "ABACD";
//		arr[2] = "BDCEF";
//		arr[3] = "BDCAA";
//		arr[4] = "DBACA";
//		arr[5] = "DABACA";
//		arr[6] = "DABAC";
		Arrays.sort(arr, new LengthFirstComparator());
		System.out.println("Done first sort");
		
		int count = 0;
		int start = 0; int length = arr[0].length();
		for (int i = 1; i < arr.length; i++) {
			if(arr[i].length()!=length) {
				count += helper(arr, start, i);
				length = arr[i].length();
				start = i;
			}
		}
		count += helper(arr, start, arr.length);
		
		System.out.println(count);
	}
	
	private static int helper(String[] arr, int start, int end) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = stringSort(arr[i]);
		}
		System.out.println("Done stringSort");
		Arrays.sort(arr, start, end);
		String previous = null; int count = 0;
		for (int i = start; i < end; i++) {
//			System.out.println(arr[i]);
			if(arr[i].equals(previous)) {
				count++;
			}
			previous = arr[i];
		}
//		System.out.println(count);
		return factorial(count);
	}
	
	public static int factorial(int n) {
        return (n*(n+1))/2;
    }
	
	private static String stringSort(String str) {
		char[] a = str.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}

class LengthFirstComparator implements Comparator<String> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String o1, String o2) {
		if(o1.length() < o2.length()) return -1;
		else if(o1.length() > o2.length()) return 1;
		return 0;
	}
	
}
