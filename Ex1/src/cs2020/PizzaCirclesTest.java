package cs2020;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Contains a variety of test cases for the PizzaCircles problem.
 *
 */
public class PizzaCirclesTest {
	
	/*
	 * Calls the method being tested.
	 */
	long GetMinSizedPizza(double area){
		return PizzaCircles.MinSizedPizza(area);
	}
	
	@Test
	public void test1() {
		assertEquals("Basic test, positive", GetMinSizedPizza(918.12), 18);
	}
	
	@Test
	public void test2() {
		assertEquals("Basic test, negative", GetMinSizedPizza(887.72), 17);
	}
	
	@Test
	public void test3() {
		assertEquals("Basic test, equals", GetMinSizedPizza(329791.830403242134), 324);
	}
	
	@Test
	public void test4() {
		assertEquals("Basic test, equals", GetMinSizedPizza(33979.46614122714), 104);
	}
	
	@Test
	public void test5() {
		assertEquals("Basic test, positive", GetMinSizedPizza(33979.4661412272+0.0000001), 105);	
	}
	
	@Test
	public void test6() {
		assertEquals("Zero", GetMinSizedPizza(0), 0);
	}
	
	/**
	 * This test ensures that you have carefully read the instructions: negative-sized areas, you must
	 * have a radius of at least zero.  You cannot have a negative radius for a pizza!
	 */
	@Test
	public void test7() {
		assertEquals("Negative", GetMinSizedPizza(-10), 0);
	}
	
	/**
	 * This test is ensuring that you have handled large values correctly.  The final radius
	 * is to be stored in a long, and if you try to store it in an int during the computation, 
	 * then your calculation will be incorrect.
	 * 
	 * Notice that the difference in the needed radius is very small, i.e., 0.0000005, so if
	 * you do not do your calculation precisely, you will get the wrong answer here.
	 */
	@Test
	public void test8() {
		assertEquals("Very large value", GetMinSizedPizza(14488040251965010945.0), 2147483748L);
		assertEquals("Very large value", GetMinSizedPizza(14488040251965000944.0), 2147483747L);
	}

}
