/**
 * 
 */
package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

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
		StopWatch sw = new StopWatch();
		sw.start();
		HashMap<Integer, Pair> hm = new HashMap<Integer, Pair>();
		try {
			FileReader dataFile = new FileReader(args[0]);
	        BufferedReader bufferedDataFile = new BufferedReader(dataFile);
	        bufferedDataFile.readLine(); // discarding size
	        
	        String line;
	        
	        while ((line = bufferedDataFile.readLine()) != null) {
	        	String sLine = stringSort(line);
	        	Pair p;
	        	p = hm.get(sLine.length());
	        	if(p==null) {
	        		p = new Pair(new HashSet<String>(), 0);
	        		hm.put(sLine.length(), p);
	        	}
	        	
	        	if(p.hs.contains(sLine)) p.count++;
	        	else p.hs.add(sLine);
			}
	        int count = 0;
	        for(Pair p : hm.values()) {
	        	if(p.count==0) continue;
	        	count += (p.count*(p.count+1))/2;
	        }
	        sw.stop();
	        System.out.println(count + " " + sw.getTime());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static String stringSort(String str) {
		char[] a = str.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}

class Pair {
	HashSet<String> hs; int count;
	public Pair(HashSet<String> hs, int count) {
		this.hs = hs;
		this.count = count;
	}
}