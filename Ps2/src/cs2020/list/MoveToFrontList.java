package cs2020.list;
/**
 * MoveToFrontList
 * Description: 
 * CS2020 2012
 */


/**
 * 
 */
public class MoveToFrontList extends FixedLengthList {

	/**
	 * Constructor
	 * @param length the length of the list
	 * Creates a new list of specified length.
	 * Initializes array m_list, setting every slot to -1.
	 */
	MoveToFrontList(int length){
		super(length);
	}
	
	/**
	 * Method: search checks whether the key is in the list
	 * @param key the integer to search for in the list
	 * @return true if key is in the last and false otherwise
	 * Description: performs a linear search in the list
	 * and notes the position index of that key
	 * If then runs a loop from index to 1 to shift every element
	 * forward by one step. Then it assigns m_list[0] = key
	 */
	
	public boolean search(int key){
		int keyIndex = 0;
		boolean returnBool = false;
		for (int i=0; i<=m_max; i++){
			if (m_list[i] == key){
				keyIndex = i;
				returnBool = true;
				break;
			}
		}
		if(returnBool) {
			for(int i = keyIndex; i>0; i--) {
				m_list[i] = m_list[i-1];
			}
			m_list[0] = key;
			return returnBool;
		} else return false;
	}
}
