package cs2020.list;

public class MyFastList extends FixedLengthList{

	/**
	 * Constructor
	 * @param length the length of the list
	 * Creates a new list of specified length.
	 * Initializes array m_list, setting every slot to -1.
	 */
	MyFastList(int length){
		super(length);
	}
	
	/**
	 * Method: search checks whether the key is in the list
	 * @param key the integer to search for in the list
	 * @return true if key is in the last and false otherwise
	 * Description: performs a linear search in the list
	 * and notes the position index of that key
	 * An inefficieny of MoveToFrontList is that even if a very low
	 * probability key is only called ONCE, it would still be moved to the
	 * head of the list, thereby requiring extra time to pass that key during a search.
	 * To solve this, instead of moving a key to the head of the list after each search,
	 * I only move it a few indexes forward, not all the way. Hence, the higher probability
	 * keys would naturally make their way to the head of the list while the lesser ones would stay around the middle
	 */
	
	public boolean search(int key){
		int keyIndex = 0;
		int jumper = 5;
		boolean returnBool = false;
		for (int i=0; i<=m_max; i++){
			if (m_list[i] == key){
				keyIndex = i;
				returnBool = true;
				break;
			}
		}
		if(returnBool) {
			if(keyIndex<jumper) jumper = keyIndex;
			for(int i = keyIndex; i>keyIndex-jumper; i--) {
				m_list[i] = m_list[i-1];
			}
			m_list[keyIndex-jumper] = key;
			return returnBool;
		} else return false;
	}
}
