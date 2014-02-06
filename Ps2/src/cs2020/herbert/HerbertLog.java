package cs2020.herbert;

//  Import file handling classes
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * class HerbertLog
 * The HerbertLog class records the jobs worked by Herbert, and
 * the wages paid to Herbert, over the last period of employment.
 * The constructor opens the specified log-file, and the get(.) method
 * returns records from the file.
 * 
 */
public class HerbertLog {
		
	/**
	 *  Public static final constants
	 */
	
	// Separator character used in the database file
	public static final String SEP = ":";
	// Length of each record in the database
	public static final int rLength = 18;
	// Padding character for database file
	public static final char PADDING = '.';

	/**
	 * Private state for the HerbertLog
	 */
	// Filename where the database can be found
	private String m_name = null;
	// Variable that points to the database, once opened
	private File m_file = null;
	// Variable for reading from the database file
	private RandomAccessFile m_inRAM = null;
	// Size of the database: number of available records
	private long m_numMinutes = 0;
	
	/**
	 * Debugging information
	 */
	// Number of "get" operations performed on the database
	// Note this is primarily for debugging.
	protected long m_numGets = 0;
	
	/**
	 * Constructor 
	 * @param filename File where the database can be found.
	 * The specified file must exist, and must contain records
	 * in the proper format. 
	 **/
	HerbertLog(String fileName){
		// Save the filename
		m_name = fileName;
		// Next, we open the file
		try {
			// Open the file
			m_file = new File(m_name);
			m_inRAM = new RandomAccessFile(m_file, "r");
			
			// Calculate the number of records in the database by
			// dividing the number of characters by the length of each record
			long numChars = m_inRAM.length();
			m_numMinutes = numChars/rLength;
		} catch (IOException e) {
			System.out.println("Error opening file: " + e);
		}
	}
	
	/**
	 * size
	 * @return the number of records in the database
	 */
	public long numMinutes(){
		return m_numMinutes;
	}

     /**
      * numGets : primarily for debugging
      * @return number of times get has been called
      */
	public long numGets(){
		return m_numGets;
	}

	/**
	 * get
	 * @param i specified the record number to retrieve, starting from 0
	 * @return the specified record, if it exists, or null otherwise
	 */
	public Record get(long i){
		
		// Increment the number of "get" operations
		m_numGets++;
		
		// Check for errors: if i is too large or too small, fail
		if (i > numMinutes()) return null;
		if (i < 0) return null;
		
		// Retrieve the proper record
		try {
			// First, calculate the offset into the file, and seek to that location
			long numChars = i*rLength;			
			m_inRAM.seek(numChars);
			
			// Next, read in rLength bytes
			// Recall that rLength is the length of one record
			byte[] entry = new byte[rLength];
			m_inRAM.read(entry);
			
			// Now, convert the string to a record.
			// Convert it to a string...
			String line = new String(entry);
			// .. parse the string using the record separator
			String[] tokens = line.split(SEP);
			// Every record should have 2 or 3 components
			assert(tokens.length==2 || tokens.length==3);
			// The first token is the name
			String name = tokens[0];
			// The second token is the height
			int height = Integer.parseInt(tokens[1]);
			return new Record(name, height);
			
		} catch (IOException e) {
			System.out.println("Error getting data from file: " + e);
		}
		// If the record wasn't found, for any reason, return null
		return null;
	}
	
	/**
	 * calculateSalary
	 * @return total computed salary
	 * To calculate salary more efficiently, we 'jump' through the log.
	 * If the log we jumped to has the same employer as the log we jumped from,
	 * We will jump again. This will repeat until we find a log that does not
	 * have the same employer. At this point, we will interate backwards until 
	 * we find the same employer again. This signifies that we have reached the
	 * last line of that specific employers' log. We add this value into salary
	 * and repeat the entire process agian until we reach the end of file.
	 */
	public int calculateSalary(){
		int salary = 0;
		
		//Saving the first log into 'pLog' so that we can retrive it in the loop later 
		Record pLog = get(0);
		boolean found = false;
		boolean onceOnly = false;
		
		//Trying to set appropriate jump vales depending on the size of the log
		int jump;
		if(m_numMinutes>10000)
			jump = (int) Math.log(m_numMinutes)*10;
		else if(m_numMinutes>100)
			jump = (int) Math.log(m_numMinutes)/2;
		else jump = 3;
		
		int i = jump;
		while(i<m_numMinutes){
			Record r = get(i);
			//Check if the current index's record has the same employers as the previous one we 
			//jumped from.
			if(pLog.getName().equals(r.getName())){
				if(found) {
					//If found, reset variables, pLog, add salary and start again
					pLog = get(i+1);
					found = false;
					salary = salary + r.getWages();
				}
				i = i + jump;
			} else {
				//If names do not match, this means that we have gone too far
				//It's time to backtrack our steps one at a time till we find
				//the same employer again.
				i--;
				found = true;
			}
			//Making sure we do not exceed BEYOND the end of the log file
			if(i>=m_numMinutes && !onceOnly){
				i = (int) (m_numMinutes-1);
				onceOnly = true;
			}
		}
		//Return salary and add the last salary in as the above loop skips it
		return salary + get(m_numMinutes-1).getWages();
	}
	
	public static void main(String[] args){
		//Run the JUnit test to determine the numOfGets output for each sample file. It should print it out
	}
}
