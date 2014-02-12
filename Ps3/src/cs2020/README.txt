Dear Tutor, I would recommend that you run the main function in SortInvestigator first before continuing

=====
What we know as of now:
A: 
B:
C:
D:
E:
F:
=====

First, let's eliminate Dr Evil since that's the easiest to identify. Just find a sorter that throws back a unsorted array and we're good. During my initial testing, I realized that if you ask each sorter to sort a element of size n = 1000 just once, it will always return a sorted array. The same was true for n = 1000, 100000 or 1000000. This made me realize that Dr Evil is not deterministic. Maybe I need to stress test it a few times so that it throw out an unsorted array.

So, I implemented checkSorted() to loop the same test multiple times. After some testing, I found that SorterC will always return at least one unsorted array with the array size is 100000 and it's repeated 20 times. Initially, I had written code in main() to run the 100000|20 checkSorted() test for all 6 Sorter classes. However this test took at least 15mins to run on my Mac, so I decided to omit that from my submission. I have only included the code to check if SorterC is sorted, since I already know that it's going to fail.

=====
What we know as of now:
A: 
B:
C: Dr Evil - Inconsistent sorts
D:
E:
F:
=====

Next, let's run our isStable() test so that we narrow our results further.

=====
What we know as of now:
A: Quick sort | Selection sort - Unstable sorting
B: Insertion sort | Merge sort | Bubble sort - Stable sorting
C: Dr Evil - Inconsistent sorts
D: Quick sort | Selection sort - Unstable sorting
E: Insertion sort | Merge sort | Bubble sort - Stable sorting
F: Insertion sort | Merge sort | Bubble sort - Stable sorting
=====

We are done with all the simple categorizations, now it's time to compare asymptotic running times within each sorting algorithm.

SorterA
=======
|N*1 = 10000|
Sorted: 		0.06970499
Almost Sorted: 	0.06827490
Reverse: 		0.07204749
Random: 		0.05981620
Ones: 			0.00324430
|N*10 = 100000|
Sorted: 		0.87703574
Almost Sorted: 	0.83792591
Reverse: 		0.84888113
Random: 		0.75948513
Ones: 			0.03112590

When N*10, the algorithm took ~10x longer. This leads me to believe that SorterA is a NLogN worst case algorithm. Also, the algorithm takes the same time for both sorted and reverse sorted inputs, which are characteristics of Quick or Merge sort. Since SorterA is NLogN and unstable, it has to be Quick Sort.

SorterB
=======
|N*1 = 10000|
Sorted: 		0.00006200
Almost Sorted: 	0.00034240
Reverse: 		0.04634280
Random: 		0.02373270
Ones: 			0.00005150
|N*10 = 100000|
Sorted: 		0.00045600
Almost Sorted: 	0.00086620
Reverse: 		3.35529637
Random: 		1.97331202
Ones: 			0.00049140

When N*10, the algorithm took ~100x longer for unsorted arrays which leads me to believe that SorterB is a N^2 algorithm. This algorithm sorts sorted arrays insanely fast, which are characteristics of Insertion or Bubble sort. We know that the worst case scenario for Insertion sort is a totally reversed array. Since the average time to sort a reverse array in SorterB is > average time to sort a random array, SorterB is Insertion Sort.

SorterD
=======
|N*1 = 10000|
Sorted: 		0.15321091
Almost Sorted: 	0.16233601
Reverse: 		0.16403791
Random: 		0.16375509
Ones: 			0.15830180
|N*10 = 100000|
Sorted: 		18.02224350
Almost Sorted: 	19.36315918
Reverse: 		20.31977844
Random: 		28.46283913
Ones: 			20.16786957

When N*10, the algorithms took ~100x longer. This leads me to believe that SorterD is a N^2 algorithm. Since SorterD is unstable and it is N^2 worst case, it is Selection Sort. Furthermore, we know that Selection Sort does not perform any better for already sorted arrays.

SorterE
=======
|N*1 = 10000|
Sorted: 		0.02848690
Almost Sorted: 	0.02972860
Reverse: 		0.02883220
Random: 		0.02706180
Ones: 			0.02617880
|N*10 = 100000|
Sorted: 		0.30621141
Almost Sorted: 	0.30906621
Reverse: 		0.30841908
Random: 		0.33341828
Ones: 			0.30787659

When N*10, the algorithm took ~10x longer. This leads me to believe that SorterE is a O(NLogN) algorithm. Since SorterE is stable and it's NLogN worst case, it is Merge Sort. Furthermore, we know that Merge sort does not perform any better for already sorted arrays(Best case O(N)).

SorterF
=======
|N*1 = 10000|
Sorted: 		0.00007830
Almost Sorted: 	0.00033610
Reverse: 		0.09496390
Random: 		0.14225121
Ones: 			0.00005410
|N*10 = 100000|
Sorted: 		0.00054760
Almost Sorted: 	0.00063800
Reverse: 		7.43396664
Random: 		15.73558331
Ones: 			0.00046050

When N*10, the algorithm took ~100x longer for unsorted arrays which leads me to believe that SorterF is a N^2 algorithm. This algorithm sorts sorted arrays insanely fast, which are characteristics of Insertion or Bubble sort. Bubble sort is very terrible for large random arrays, it performs best in almost sorted(slightly out of place arrays). Hence, SorterF is Bubble Sort.

=====
What we know as of now:
A: Quick sort - Unstable, NLogN, already sorted is not better
B: Insertion sort - Stable, N^2, already sorted is much better, reversed performs worst
C: Dr Evil - Inconsistent sorts
D: Selection sort - Unstable, N^2, already sorted is not better
E: Merge sort - Stable, NLogN, already sorted is not better
F: Bubble sort - Stable, N^2, already sorted is much better, random performs worst
=====

Done.