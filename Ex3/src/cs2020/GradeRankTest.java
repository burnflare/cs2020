package cs2020;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the GradeRank implementation.
 * 
 * @author Khor Shi-Jie
 */

public class GradeRankTest {

	@Test
	public void gradeInArrayTest() {
		int[] testArray = {90, 4, 34, 54, 8, 7, 5, 12, 45};
		Assert.assertEquals("Grade is present in class", 9, GradeRank.getRank(testArray, 4));
		testArray[0] = 4;
		Assert.assertEquals("Duplicate grades are present in class", 8, GradeRank.getRank(testArray, 4));
	}

	@Test
	public void gradeNotInArrayTest() {
		int[] testArray = {90, 4, 34, 54, 8, 7, 5, 12, 45};
		Assert.assertEquals("Grade is not present in class", 1, GradeRank.getRank(testArray, 100));
		testArray[0] = 101;
		Assert.assertEquals("Grade is not present in class", 2, GradeRank.getRank(testArray, 100));
	}
	
	@Test
	public void bigDataTest() {
		int[] testArray = new int[10000000];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = i+1;
		}
		System.out.println(GradeRank.getRank(testArray,0));
		Assert.assertEquals("Big data test", 10000001, GradeRank.getRank(testArray, 0));
	}
	
}
