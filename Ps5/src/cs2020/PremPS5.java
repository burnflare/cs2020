/**
 * 
 */
package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;

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
		try {
			FileReader dataFile = new FileReader(args[0]);
	        BufferedReader bufferedDataFile = new BufferedReader(dataFile);
	        bufferedDataFile.readLine();
	        String line;
	        int i = 0;
	        while ((line = bufferedDataFile.readLine()) != null) {
	        	i++;
				System.out.println(line);
			}
	        System.out.println(i);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
