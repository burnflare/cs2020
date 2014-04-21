/**
 * 
 */
package cs2020;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author vishnu
 * TextGenerator class is mainly used to test MarkovModel
 */
public class TextGenerator {
	static int order;
	static int n;
	static String fileName;
	static String inputString;
	
	/**
	 * Main method. We first check if there are 3 arguments in args. If not, throw error.
	 * Then we parse the inputs, and throw errors accordingly.
	 * Then we read all the text via a Stringbuffer then assign it to a string.
	 * After that's done, we initialize MarkovModel and call it's constructor.
	 * As the final step, we begin to generate our output text. Which we print at the end.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		if(args.length < 3) throw new Exception("Not enough arguments");
		try {
			order = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
			fileName = args[2];
		} catch (Exception e) {
			throw new Exception("Error parsing inputs");
		}
		
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader bufferedDataFile = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = bufferedDataFile.readLine()) != null) {
				sb.append(line);
			}
			inputString = sb.toString();
		} catch (Exception e) {
			throw new Exception("Error reading text");
		}
		
		MarkovModel mm = new MarkovModel(inputString, order);
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append(inputString.substring(0, order));
		
		StringBuffer kgram = new StringBuffer();
		kgram.append(inputString.substring(0, order));
		
		for (int i = 0; i < n; i++) {
			char next = mm.nextCharacter(kgram.toString());
			if(next==255){
				kgram.delete(0, order);
				kgram.append(inputString.substring(0, order));
				next = mm.nextCharacter(kgram.toString());
			}
			kgram.deleteCharAt(0);
			kgram.append(next);
			outputBuffer.append(next);
		}
		System.out.println(outputBuffer.toString());
	}
}
