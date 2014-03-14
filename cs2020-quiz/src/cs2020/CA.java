package cs2020;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Framework code for the CA (Cellular Automaton) class for
 * the CS2020 Coding Quiz, 2014
 * @author gilbert
 *
 */
public class CA implements ICA {

	// Stores the state of the CA.  Each entry should be a 0 or a 1.
	int[] m_state;
	
	// The rule for updating the CA.  This should be an array of 
	// size 8, where each entry is a 0 or a 1.
	int[] m_rule;
	
	// The size of the CA, initialized in the constructor.
	int m_size = 0;
	
	// Boolean flag indicating whether the CA is initialized.
	boolean m_initialized = false;
	
	/**
	 * Constructor
	 * @param size
	 */
	CA(int size){
		// Create a new array of the specified size.
		// Notice that we create an array with (size+2) entries.
		// m_state[0] and m_state[n-1] are dummy values marking
		// the boundary of the CA.
		m_state = new int[size+2];
		m_state[0] = 0;
		m_state[size+1] = 0;
		// Update the size.
		m_size = size;
		// Note that the CA has not yet been initialized
		m_initialized = false;
	}
	
	/**
	 * Initialize method
	 * @param rule array
	 * 
	 *  This method takes in a rules array. First it checks if the rules array is of size 8
	 *  AND rules array only consists of 0s and 1s. If this is not true, it throws an error
	 *  Then this method procedes to fill up the initialize state of m_state array within
	 *  a for loop.
	 */
	@Override
	public void initialize(int[] rule) throws Exception{
		//Throw errors first
		if(rule.length != 8)
			throw new Exception("Rule must be 8 digits.");
		for(int i = 0; i < rule.length; i++) {
			if(rule[i] != 1 && rule[i] != 0)
				throw new Exception("Rule must only have 1s and 0s.");
		}
		m_rule = rule;
		
		//Saving int value, also doing a check here to test if size is odd or even.
		//If it's even, we will add 2 1s while enumerating
		int m_size_half = m_size/2 + 1;
		boolean even = (m_size % 2 == 0);
		for(int i = 1; i < m_size+1; i++) {
			if(m_size_half==i && !even) {
				m_state[i]=1;
			} else if(m_size_half == i && even) {
				m_state[i]=1;
				m_state[i+1]=1;
				i++;
			}
			else {
				m_state[i]=0;
			}
		}
		m_initialized = true;
	}
		
	/**
	 * Step
	 * This method works by first cloning m_state into a temp array.
	 * Then we first extract out the left, middle and right elements from m_state.
	 * Using laws of binary conversion, we convert the left, middle & right values into it's decimal form
	 * IE: left * 4 + middle * 2 + right * 1 = lookup
	 * int lookup corresponds to the index in the rule array.
	 * Finally, we update the temp array accordingly and lastly update m_state 
	 */
	public void step(){
		int[] arr = m_state.clone();
		for(int i = 1; i < m_size+1; i++) {
			//Extract left, middle, right
			int left = m_state[i-1];
			int middle = m_state[i];
			int right = m_state[i+1];
			
			//Build int lookup by doing a binary -> decimal conversion
			int lookup = 0;
			if(left==1) lookup += 4;
			if(middle==1) lookup += 2;
			if(right==1) lookup += 1;
			
			//Set lookedup value to a temp array
			arr[i]=m_rule[lookup];
		}
		//Replace main array with temp array;
		m_state = arr;
	}

	/**
	 * toString
	 * Transforms the state of the CA into a string.
	 */
	@Override
	public String toString(){
		String answer = "";
		for (int i=1; i<m_size+1; i++){
			if (m_state[i] > 0){
				answer += '1';
			}
			else {
				answer += ' ';
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		
		// Create a new cellular automaton of size 31
		CA example = new CA(31);
		
		// Create a new rule
		int[] rule = {0,1,0,0,1,0,0,0};
		
		// Initialize the CA with the rule
		example.initialize(rule);
		
		// Get an iterator
		Iterator<String> iterator = example.iterator();
		
		// I do not know(forgot, can't recall, etc.) how to implement iterator(),
		// so I am going to manually call toString and step here to demonstrate everything else!
		// Run the cellular automaton for 17 steps
		System.out.println(example.toString());
		for (int i=0; i<16; i++){
			example.step();
			System.out.println(example.toString());
		}
	}

	@Override
	public Iterator<String> iterator() {
		/**
		 * Blah, I don't know recall the syntax to implement this guy's hasNext & next!
		 * Psuedocode of their implementations:
		 * hasNext
		 * =======
		 * return true
		 * 
		 * next
		 * ====
		 * boolean firstRun = true;
		 * if(fistRun) {
		 * 		firstRun = false;
		 * 		return toString();
		 * else {
		 * 			step();
		 * 			return toString();
		 * 	}
		 */
		
		return null;
	}
}
