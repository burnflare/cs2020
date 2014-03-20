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
	public HashSet<String> hs = new HashSet<String>(); public int count = 0;
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
	        	
	        	int hmcount = 0;
		        while ((line = stringSort(bufferedDataFile.readLine())) != null) {
		        	length = line.length();
		        	p = hm.get(length);
		        	if(p==null) {
		        		p = new Pair();
		        		p.hs.add(line);
		        		hm.put(length, p);
		        		hmcount++;
		        	} else {
			        	if(p.hs.contains(line)) { p.count++; }
			        	else p.hs.add(line);
		        	}
				}
		        
		        int count = 0;
		        for(Pair pp : hm.values()) {
		        	if(pp.count==0) continue;
		        	System.out.println(pp.count);
		        	count += (pp.count*(pp.count+1))/2;
		        }
		        sw.stop();
		        System.out.println(count + " " + sw.getTime() +" " + hmcount);
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
}