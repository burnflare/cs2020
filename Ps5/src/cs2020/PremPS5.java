package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author vishnu
 * Pair
 * This pair class is used to tuple a HashSet<String> along with a count variable
 * The HashSet<String> is used to store strings of the same length
 * The count is used to store the number of times the same string is repeated in that hashset
 */
class Pair {
	public HashSet<String> hs = new HashSet<String>();
	public int count = 0;
}

public class PremPS5 {
	/**
	 * @author vishnu
	 * Main class lives here
	 * In essence, we count permutations by alphabetically sorting all the characters in a String. Then we compare
	 * strings of the same length. If then 2 strings are alike, that means that they are permutations of each other.
	 * ====================
	 * Firstly we create a HashMap of Integers & Pairs. This HashMap is used to store all strings of the same length
	 * together. Every Pair object has a HashSet and a count integer. In order to not have a HUGE HashMap, we are only
	 * saving a HashMap of HashSets. Each HashMap entry would be used to save Strings of the same length. Then inside
	 * each HashMap entry, it's Pair entry is used to save unique strings of that length.
	 * 
	 * Then we read the whole file line by line.
	 * 
	 * Then we sort the string by rearranging all characters in that line alphabetically
	 * 
	 * Then we try to check if a string of that length exists inside the HashMap. If not, we create it. If
	 * a HashMap exists, we check if this(alphabetically sorted) string already exists in the HashMap, if it does we
	 * increment the specific's Pair by 1.
	 * 
	 * If there are 3 strings of size 5 with the exact same characters(eg. "AABBC"), this means that there
	 * would be 6 permutations of between them. After some explorations, you would realize that the number of 
	 * strings -> permutations can be calculated by applying the Summation formula to the number of strings.
	 * Once we have read all the lines from the input, we iterate through all the values inside the HashMap.
	 * We update the global permutations count by using the summation formula of the Pair's count.
	 * Formula: numberOfPermutations +=  (count * (count + 1)) / 2;
	 */
	public static void main(String[] args) {
		HashMap<Integer, Pair> hm = new HashMap<Integer, Pair>(500);
		try {
			BufferedReader bufferedDataFile = new BufferedReader(new FileReader(args[0]));
			bufferedDataFile.readLine(); // Dont care about size, discarding

			String line;
			int length;
			Pair p;
			int count = 0;

			while ((line = stringSort(bufferedDataFile.readLine())) != null) {
				length = line.length();
				p = hm.get(length);
				if (p == null) {
					p = new Pair();
					p.hs.add(line);
					hm.put(length, p);
				} else {
					if (p.hs.contains(line)) {
						p.count++;
					} else
						p.hs.add(line);
				}
			}

			for (Pair pr : hm.values()) {
				if (pr.count == 0)
					continue;
				count += (pr.count * (pr.count + 1)) / 2;
			}
			System.out.println(count);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method rearranges the characters in the String alphabetically and returns it.
	 * @param str
	 * @return Alphabetically sorted str
	 */
	private static String stringSort(String str) {
		if (str == null)
			return null;
		char[] a = str.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}