package cs2020;

import java.util.Arrays;

public class GradeRank {
	public static int getRank(int[] classGrades, int grade) {
		int[] arr = Arrays.copyOf(classGrades, classGrades.length);
		Arrays.sort(arr);
		
		for (int i = arr.length - 1; i >= 0; i--) {
			int j = arr[i];
			if( grade >= j) return arr.length - i;
		}
		return arr.length + 1;
	}

	public static void main(String[] args) {
		int[] classGrades = { 99, 94, 81, 57, 92, 37, 58, 72, 73, 75, 75, 64, 97 };
		int myGrade = 99;
		System.out.println(getRank(classGrades, myGrade)); // Should return 1
		myGrade = 79;
		System.out.println(getRank(classGrades, myGrade)); // Should return 6
		
	}
}
