package cs2020;

import java.util.*;

public class SortInvestigator {
	
	static StopWatch sw = new StopWatch();
	
	/**
	 *  The next 5 methods will output an Integer array of requested size
	 *  The first method returns a array filled with random number from 0 to size-1
	 *  The second method returns a already sorted array from 0 to size-1
	 *  The third method returns a *almost* sorted array. The first number is largest number.
	 *  The fourth method returns a array filled with just 1s
	 *  The fifth array returns a array sorted in reverse from size-1 to 0
	 *  
	 *  @param size - size of array to spit out
	 *  @return Integer array of requested type & size
	 */
	private static Integer[] getRandomArray(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < arr.length; i++)
			arr[i] = (int)(Math.random() * (size + 1));		
		return arr;
	}
	
	private static Integer[] getSortedArray(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i;
		return arr;
	}
	
	private static Integer[] getAlmostSortedArray(int size) {
		Integer[] arr = new Integer[size];
		arr[0] = size+1;
		for (int i = 1; i < arr.length; i++)
			arr[i] = i;
		return arr;
	}
	
	private static Integer[] getOnesArray(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < arr.length; i++)
			arr[i] = 1;
		return arr;
	}
	
	private static Integer[] getReverseArray(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < arr.length; i++)
			arr[i] = size-i;
		return arr;
	}
    /**
     * This method prints out a array. It's no longer used in production code.
     * For debugging only.
     * 
     * @param arr - array to print
     */
	private static void printArray(Integer[] arr){
		for(int x : arr)
			System.out.print(x+" ");
		System.out.println();
	}
	/**
	 * This method checks if a ISort-type sorter is stable or not.
	 * To test this, we use a new class called IntPair to store 
	 * random 2 digit variables. IntPair's compareTo() method is 
	 * implemented to only compare it's first digit value while
	 * IntPair's equals() method compares the entire 2 digit number
	 * 
	 * We clone the array and then we sort it with the provided sorter
	 * and also with Java's Arrays.sort() method. Java's sort uses
	 * MergeSort so we can be sure that it's stable. Finally, to
	 * check if the sorter is stable, we compare it's sorted array
	 * to Java's sorted array.
	 * 
	 * @param sorter - ISort class to test
	 * @param size - size of test arrays to generate
	 * @return bool whether the sorter stable
	 */
	private static boolean isStable(ISort sorter, int size) {
		IntPair[] arr = new IntPair[size];
		for(int i = 0; i < size; i++) {
			arr[i] = new IntPair((int)(Math.random()*100));
		}
 		IntPair[] arrPreSorted = arr.clone();
 		
 		sorter.sort(arr);
 		Arrays.sort(arrPreSorted);
 		
 		return Arrays.equals(arr, arrPreSorted);
 	}
	
	/**
	 * This method checks if a sorter is truly sorting properly
	 * Simply put, this method generates a array of given size
	 * and asks the sorter to sort it. Then we run a for-loop
	 * through the result to check if arr[n] is always < than arr[n+1].
	 * The moment this is not true, we know there is something
	 * wrong and we return false
	 * 
	 * Special note: After doing some testing, I found out that
	 * Dr Evil is indeed very evil. He tries to hide himself by
	 * returning a sorted array the first few time it's asked to sort.
	 * After doing some more research on all the 6 sorters, I
	 * found out that SorterC consistently returns an unsorted array
	 * of size n=100000 if it's asked to sort it at least 20 times.
	 * 
	 * Hence, there's a for-loop here to try sorting it 20 times so 
	 * that we can get it to break.
	 * 
	 * @param sorter - ISort class to test
	 * @param size - size of test arrays to generate
	 * @return bool whether the sorter sorts properly
	 */
	private static boolean checkSorted(ISort sorter, int size) {
		int interations = 20;
		Integer[] arr = getRandomArray(size);
		for(int i = 0; i < interations; i++) {
			Integer[] arrC = arr.clone();
			sorter.sort(arrC);
			for(int j = 0; j < arrC.length-1; j++) {
				if(arrC[j].compareTo(arrC[j+1])>0)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * This method was created to make my life easier. It sorts an
	 * array of Integers with a sorter multiple times and returns
	 * the average StopWatch time for the sorts.
	 * 
	 * @param sorter - ISort class to use
	 * @param arr - Array to use
	 * @param interations - number of tests to run
	 * @return the average time of sorting arr with sorter, multiple times
	 */
	private static float arrayTester(ISort sorter, Integer[] arr, int interations) {
		float averageTime = 0;
		for(int i = 0; i < interations; i++) {
			sw = new StopWatch();
			sw.start();
			sorter.sort(arr);
			sw.stop();
			
			averageTime += sw.getTime();
		}
		return averageTime/interations;
	}
	
	/**
	 * This is the most critical method here. It takes in
	 * a ArrayList of ISort classes from the main method
	 * and runs various tests
	 * 
	 * It uses arrayTester() as it's helper to actually conduct
	 * StopWatch timings and prints out that average time. For
	 * each sorter, it runs a test with a sorted, almost sorted, 
	 * reverse, random & ones array input.
	 * 
	 * In order to weed out NLogN and N^2 running time algorithms,
	 * it runs the entire test twice. First with size, and then again
	 * with size * 10. For a large array size, we know that a N^2
	 * algorithm would perform much worse  when N*10 vs. a NLogN
	 * algorithm. This is helpful for weeding out N^2 sorters.
	 * 
	 * @param sArr - ArrayList of ISort classes to use
	 * @param size - size of test arrays to generate
	 * @param iterations - number of times to loop each test
	 */
	private static void runTimerTests(ArrayList<ISort> sArr, int size, int iterations) {
		int nScale = 10;
		for (ISort sorter : sArr) {
			System.out.println(sorter.getClass().getSimpleName());
			System.out.println("=======");
			for(int i = 1; i<= nScale; i+=nScale-1) {
				System.out.println("|N*"+i+" = "+size*i+"|");
				// Using printf here so that I can force non scientific notation outputs
				arrayTester(sorter, getSortedArray(size*i), iterations);
				System.out.printf("Sorted: \t%.8f\n", arrayTester(sorter, getSortedArray(size*i), iterations));
				System.out.printf("Almost Sorted: \t%.8f\n",arrayTester(sorter, getAlmostSortedArray(size*i), iterations));
				System.out.printf("Reverse: \t%.8f\n",arrayTester(sorter, getReverseArray(size*i), iterations));
				System.out.printf("Random: \t%.8f\n",arrayTester(sorter, getRandomArray(size*i), iterations));
				System.out.printf("Ones: \t\t%.8f\n",arrayTester(sorter, getOnesArray(size*i), iterations));
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// We make a ArrayList of ISorter classes. I use this to 
		// make my life easier when I need to iterate everyone 
		ArrayList<ISort> sortersArr = new ArrayList<ISort>();
		sortersArr.add(new SorterA());
		sortersArr.add(new SorterB());
		sortersArr.add(new SorterC());
		sortersArr.add(new SorterD());
		sortersArr.add(new SorterE());
		sortersArr.add(new SorterF());
		
		// Finding Dr Evil - With testing done(beforehand), I know that SorterC is EVIL!
		// I am not going to run the same tests on the other 5 sorters here because some
		// of the other algorithms take ALOT of time to sort 100,000 elements 20 times.
		// Since Dr Evil is evil, he tries to hide himself by sorting the array properly
		// the first few times, SorterC would only show itself with a array of size 100000
		// repeated 20 times.
		if(!checkSorted(sortersArr.get(2), 100000)) {
			System.out.println(sortersArr.get(2).getClass().getSimpleName()+" is EVIL!\n");
		}
		// SorterC is Evil! So let's drop SorterC from further testing from now on
		sortersArr.remove(2);
		
		// Check if the remaining 5 sorters are stable or not using a array size of 1000
		for(ISort s : sortersArr) {
			System.out.print(s.getClass().getSimpleName() + " is ");
			if(isStable(s, 1000))
				System.out.print("Stable.");
			else
				System.out.print("Unstable.");
			System.out.println();
		}
		System.out.println();
		
		// Finally, running our main tester method, with a N=1000 size.
		// Note: In the README file results, I actually used n = 10000 instead of 1000.
		// This was done so that I would get larger numbers to play with. However,
		// n = 10000 took about 20mins to run on my machine since it had to perform n*10
		// tests 10 times each. So for the submission only, I chose to reduce n = 1000.
		runTimerTests(sortersArr, 1000, 10);
	}

}
