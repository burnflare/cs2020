/**
 * 
 */
package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


class Pair {
	public HashSet<IntegerArr> hs = new HashSet<IntegerArr>();
	public int count = 0;
}

class IntegerArr {
	public Integer[] cArr;
	public int hashCode() {
		// TODO Auto-generated method stub
		return Arrays.hashCode(cArr);
	}
	
	public boolean equals(Object obj) {
		IntegerArr IA = (IntegerArr) obj;
		return Arrays.equals(cArr, IA.cArr);
	}
}

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
		for(String s : args) {
			StopWatch sw = new StopWatch();
			sw.start();
			HashMap<Integer, Pair> hm = new HashMap<Integer, Pair>(500);
			try {
				FileReader dataFile = new FileReader(s);
		        BufferedReader bufferedDataFile = new BufferedReader(dataFile);
		        bufferedDataFile.readLine(); // Dont care about size, discarding
		        
		        String line;
		        int length;
	        	Pair p;
	        	IntegerArr IA;
	        	
		        while ((line = bufferedDataFile.readLine()) != null) {
		        	length = line.length();
		        	IA = new IntegerArr();
	        		IA.cArr = freqChar(line);
	        		
		        	p = hm.get(length);
		        	if(p==null) {
		        		p = new Pair();
		        		p.hs.add(IA);
		        		hm.put(length, p);
		        	} else {
			        	if(p.hs.contains(IA)) { p.count++; }
			        	else {
			        		p.hs.add(IA);
			        	}
		        	}
				}
		        
		        int count = 0;
		        for(Pair pp : hm.values()) {
		        	if(pp.count==0) continue;
		        	count += (pp.count*(pp.count+1))/2;
		        }
		        sw.stop();
		        System.out.println(count + " " + sw.getTime());
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private static String stringSort(String str) {
		if(str==null) return null;
		char[] a = str.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
	
	private static Integer[] freqChar(String str) {
		if(str==null) return null;
		Integer[] arr = new Integer[256];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=0;
		}
		for (int i = 0; i < str.length(); i++) {
			arr[str.charAt(i)]++;
		}
		return arr;
	}
}