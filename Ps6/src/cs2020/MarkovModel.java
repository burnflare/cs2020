/**
 * 
 */
package cs2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
/**
 * This Pair class was generated to hold elements inside the HashMap. Each kgram String is associated
 * with a Pair. A Pair holds the number of times that kgram was found in the text(count), and a Integer[]
 * array of the number of times the the a preeceding char appeared after that specific kgram.
 * @author vishnu
 *
 */
class Pair {
	private int count = 1;
	private Integer[] arr = new Integer[127];
	private int[] probArr;
	
	/**
	 * Pair constructor. We zero-out the entire 128 element array.
	 */
	Pair() {
		Arrays.fill(arr, 0);
	}
	
	/**
	 * This method adds a char into the Pair. If the char value is >127(Not ASCII), it ignores it.
	 * If not, it increments the char value's index in the array. At the same time, we increment
	 * Pair's count too, indicating that one more matching has been added to this Pair.
	 * @param c
	 */
	void incrementChar(char c) {
		if(c>127) return;
		arr[c]++;
		count++;
	}
	
	/**
	 * @return count - the number of matches to this kgram Pair
	 */
	int getCount(){
		return count;
	}
	
	/**
	 * @param c - char's count to lookup
	 * @return The number of times this char has preceeded this Pair
	 */
	int getCharCount(char c){
		return arr[c];
	}
	
	/**
	 * Upon request, this method returns a raw probability array of chars that preceed this Pair.
	 * We create this probabality array by iterating through the int array, then creating a temp array
	 * that has each char repeated in it as many times as it occurs. We then return this array. Randomly
	 * picking a element from the bounds of this returned array should give you a char in Markov's probability
	 * @return
	 */
	int[] getProbabilityArray() {
		if(this.probArr != null) return this.probArr ;
		
		Integer[] arr2 = arr.clone();
		int[] probArr = new int[count-1];
		
		int i = 0;
		int j = 0;
		
		while(i < arr2.length) {
			if(arr2[i]==0) i++;
			else {
				probArr[j] = i;
				arr2[i]--;
				j++;
			}
		}
		this.probArr = probArr; 
		return this.probArr ;
	}
}

/**
 * @author vishnu
 * This is the main MarkovModel class.
 */
public class MarkovModel {
	private int m_order;
	private String m_text;
	private Random m_random = new Random();
	private HashMap<String, Pair> m_hm = new HashMap<String, Pair>();
	final char NOCHARACTER = (char)(255);
	
	/**
	 * MarkovModel's constructor. We take in the entire string and order as inputs(And throw errors
	 * appropriately). Then we run a forloop through the entire text, extracting out texts of size order
	 * and added it into the HashMap. If the text snippet already exists in the HashMap then we just
	 * increment the Pair's counter. If not we create a new Pair and add it to the hashmap.
	 * @param text
	 * @param order
	 * @throws Exception - Input checking
	 */
	public MarkovModel(String text, int order) throws Exception {
		if(text.length() < order) throw new Exception("MarkovModel's input is shorter than it's order!");
		m_order = order;
		m_text = text;
		
		for(int i = 0; i < m_text.length() - m_order; i++) {
			String subS = m_text.substring(i, i+m_order);
			char nextC = m_text.charAt(i+m_order);
			if(m_hm.containsKey(subS)){
				Pair p = m_hm.get(subS);
				p.incrementChar(nextC);
			}
			else {
				Pair p = new Pair();
				p.incrementChar(nextC);
				m_hm.put(subS, p);
			}
		}
	}
	
	public int order() {
		return m_order;
	}
	
	/**
	 * @param kgram - String to reference from model
	 * @return The number of times kgram appears
	 * @throws Exception - Input length checking
	 */
	public int getFrequency(String kgram) throws Exception {
		if(kgram.length() != m_order) throw new Exception("kgram's length must match MarkovModel's order!");
		return m_hm.get(kgram).getCount();
	}

	/**
	 * @param kgram - String to reference from model
	 * @param c - char to check frequency of
	 * @return The number of times this char appeared for this specific kgram string
	 * @throws Exception - Input length checking
	 */
	public int getFrequency(String kgram, char c) throws Exception {
		if(kgram.length() != m_order) throw new Exception("kgram's length must match MarkovModel's order!");
		return m_hm.get(kgram).getCharCount(c);
	}
	
	/**
	 * This method returns a probable next char given a kgram input. This method works by first asking kgram's
	 * Pair to return a probability array. We then generate a random integer between 0 and the length of the 
	 * probability array. We pick that char from the probability array and return it. 
	 * @param kgram - String to reference from model
	 * @return
	 * @throws Exception - Input length checking
	 */
	public char nextCharacter(String kgram) throws Exception {
		if(kgram.length() != m_order) throw new Exception("kgram's length must match MarkovModel's order!");
		
		try {
			Pair p = m_hm.get(kgram);
			int[] probArr = p.getProbabilityArray();
			
			int randomNum = m_random.nextInt(probArr.length);
			return (char) probArr[randomNum];	
		} catch (Exception e) {
			return NOCHARACTER;
		}
	}
	
	public void setRandomSeed(long s) {
		m_random.setSeed(s);
	}
}
