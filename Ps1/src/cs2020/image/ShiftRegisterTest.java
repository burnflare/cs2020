package cs2020.image;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {

	/**
	 * getRegister returns a shiftregister to test
	 * @param size
	 * @param tap
	 * @return a new shift register
	 * Description: to test a shiftregister, update this function
	 * to instantiate the shift register
	 */
	ILFShiftRegister getRegister(int size, int tap){
		return new ShiftRegister(size, tap);
	}
	
	/**
	 * Test shift with simple example
	 * @throws Exception 
	 */
	@Test
	public void testShift1() throws Exception {
		ILFShiftRegister r = getRegister(9, 7);		
		int[] seed = {0,1,0,1,1,1,1,0,1};
		r.setSeed(seed);
		int[] expected = {1,1,0,0,0,1,1,1,1,0};
		for (int i=0; i<10; i++){
			assertEquals("ShiftTest", expected[i], r.shift());
		}	
	}
	
	/**
	 * Test generate with simple example
	 * @throws Exception 
	 */
	@Test
	public void testGenerate1() throws Exception {
		ILFShiftRegister r = getRegister(9, 7);		
		int[] seed = {0,1,0,1,1,1,1,0,1};
		r.setSeed(seed);
		int[] expected = {6,1,7,2,2,1,6,6,2,3};
		for (int i=0; i<10; i++){
			assertEquals("GenerateTest", expected[i], r.generate(3));
		}	
	}
		
	/**
	 * Test register of length 1
	 * @throws Exception 
	 */
	@Test
	public void testOneLength() throws Exception {
		ILFShiftRegister r = getRegister(1, 0);		
		int[] seed = {1};
		r.setSeed(seed);
		int[] expected = {0,0,0,0,0,0,0,0,0,0,};
		for (int i=0; i<10; i++){
			assertEquals("GenerateTest", expected[i], r.generate(3));
		}	
	}
	
	/**
	 * Test with erroneous seed.
	 * @throws Exception 
	 */
	@Test(expected = Exception.class)
	public void testError() throws Exception {
		ILFShiftRegister r = getRegister(4, 1);		
		int[] seed = {1,0,0,0,1,1,0};
		r.setSeed(seed);
		r.shift();
		r.generate(4);
		
		//I think the proper response for ShiftRegister when it gets a seed that is 
		//unequal to the set size would be to throw a Java Exception. Then by making
		//the above modification to the Test method(line 75), this test now expects
		//an exception to be throw in order to pass.
	}
    
}
