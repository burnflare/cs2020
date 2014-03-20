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
	        	String sLine = line;
	        	Pair p;
	        	p = hm.get(sLine.length());
	        	if(p==null) {
	        		p = new Pair(new HashSet<StringComparator>(), 0);
	        		hm.put(sLine.length(), p);
	        	}
	        	
	        	if(p.hs.contains(new StringComparator(sLine))) p.count++;
	        	else p.hs.add(new StringComparator(sLine));
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
}

class Pair {
	HashSet<StringComparator> hs; int count;
	public Pair(HashSet<StringComparator> hs, int count) {
		this.hs = hs;
		this.count = count;
	}
}

class StringComparator {
	String str;
	public StringComparator(String str) {
		this.str = stringSort(str);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		StringComparator sc = (StringComparator) obj;
		return str.equals(sc.str);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return str.hashCode();
	}
	private static String stringSort(String str) {
		char[] a = str.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}