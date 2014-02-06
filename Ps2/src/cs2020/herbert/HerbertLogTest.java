package cs2020.herbert;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * HerbertLogTest
 * 
 * Description: A set of tests to ensure that the correct salary is calculated
 * and the minimum number of minutes for selected goalIncomes are correct
 *
 * @author Goh Chun Teck
 */
public class HerbertLogTest {

	@Test
	/**
	 * Verify the correct salary calculated in veryShortNames
	 */
	public void testVeryShortNamesTotalSalary() {
		HerbertLog log = new HerbertLog("names/veryShortNamesHerbert.txt");
		
		int salary = log.calculateSalary();
		System.out.println("veryShortNamesHerbert numOfGets: " + log.m_numGets);
		assertEquals("veryShortNamesTotalSalary", 52, salary);
	}
	
	@Test
	/**
	 * Verify the correct salary calculated in shortNames
	 */
	public void testShortNamesTotalSalary() {
		HerbertLog log = new HerbertLog("names/shortNamesHerbert.txt");
		
		int salary = log.calculateSalary();
		System.out.println("shortNamesHerbert numOfGets: " + log.m_numGets);
		assertEquals("shortNamesTotalSalary", 401, salary);
	}
	
	@Test
	/**
	 * Verify the correct salary calculated in vmanyNames
	 */
	public void testManyNamesTotalSalary() {
		HerbertLog log = new HerbertLog("names/manyNamesHerbert.txt");
		
		int salary = log.calculateSalary();
		System.out.println("manyNamesHerbert numOfGets: " + log.m_numGets);
		assertEquals("manyNamesTotalSalary", 47803, salary);
	}
	
	@Test
	/**
	 * Verify the correct salary calculated in longNames
	 */
	public void testLongNamesTotalSalary() {
		HerbertLog log = new HerbertLog("names/longNamesHerbert.txt");
		
		int salary = log.calculateSalary();
		System.out.println("longNamesHerbert numOfGets: " + log.m_numGets);
		assertEquals("longNamesTotalSalary", 2796254, salary);
	}

}
