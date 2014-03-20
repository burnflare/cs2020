package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Pair {
	public HashSet<String> hs = new HashSet<String>();
	public int count = 0;
}

public class PremPS5 {

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
	        	
		        while ((line = stringSort(bufferedDataFile.readLine())) != null) {
		        	length = line.length();
		        	p = hm.get(length);
		        	if(p==null) {
		        		p = new Pair();
		        		p.hs.add(line);
		        		hm.put(length, p);
		        	} else {
			        	if(p.hs.contains(line)) { p.count++; }
			        	else p.hs.add(line);
		        	}
				}
		        
		        int count = 0;
		        for(Pair pr : hm.values()) {
		        	if(pr.count==0) continue;
		        	count += (pr.count*(pr.count+1))/2;
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
}