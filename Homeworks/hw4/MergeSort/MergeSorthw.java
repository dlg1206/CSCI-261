import java.util.*;
import java.io.*;

public class MergeSorthw {

    public static void main(String [] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new File(args[0]));
	// get the size of the array
	int n = sc.nextInt();

	// construct array and fill	
	int [] a = new int[n];
	for (int i =0;  i < n; i++)
	    a[i] = sc.nextInt();

	// mergeSort the array
	mergeSort(a);
	// if array length < 20, print it
        if (n < 20)
	   System.out.println(Arrays.toString(a));
	// else for longer arrays, check the ordering	
	else {
	    for (int i  = 0; i < a.length-1; i++)
		if (a[i] > a[i+1])
		    System.out.println("UNSORTED");
	    System.out.println("If working, this is the only output.");
	}
    }

	private static void swap(int[] a, int indexA, int indexB){

		int temp = a[indexA];

		a[indexA] = a[indexB];

		a[indexB] = temp;
	}

	private static void print(int [] a, int start, int end){
		System.out.print("[");
		for(int i = start; i < end; i++){
			System.out.print(" " + a[i] + " ");
		}
		System.out.println("]");
	}

	public static void split(int[] a, int start, int end){


		if(start != end){

			int mid = (start + end) / 2;
			// split left half
			split(a, start, mid);

			// split right half
			split(a, mid + 1, end);

			// merge the two halves together
			merge(a, start, end);
		}




	}


	private static int[] copyRange(int[] a, int start, int end){

		int[] copy = new int[end - start];

		for(int i = 0; i < copy.length; i++){
			copy[i] = a[start + i];
		}

		return copy;

	}

	// a, l, m, r
	// a, p, q, r
	// a, s,  , e
	private static void merge(int[] a, int start, int end){

		int mid = (start + end) / 2;

		int[] left = copyRange(a, start, mid + 1);
		int[] right = copyRange(a, mid + 1 , end);

		int lIndex = 0; int rIndex = 0;

		int curIndex = start;

		while(lIndex < left.length && rIndex < right.length){

			if( left[lIndex] < right[rIndex]){
				a[curIndex++] = left[lIndex];
				lIndex++;
			} else {
				a[curIndex++] = right[rIndex];
				rIndex++;
			}


		}

		int i;
		for(i = lIndex; i < left.length; i++){
			a[curIndex++] = left[i];
		}

		for(i = rIndex; i < right.length; i++){
			a[curIndex++] = right[i];
		}
	}

    public static void mergeSort(int [] a) {
        // Finish me and add any methods you need to finish me
		// methods: split, compare
		int [] b = {6, 5, 12, 10, 9, 1};
		int [] c = {38, 27, 43, 3, 9, 82, 10};
		int [] d = {7, 3, 2, 16, 24, 4, 11, 9};
		int [] e = {5, 2, 4, 7, 1, 3, 2, 6};
		int [] f = {13, 2, 7, 9, 12, 1};

		int [][] tests = {b, c, d, e, f};

//		for(int[] test : tests){
//			doSort(test, 0, test.length);
//			System.out.println();
//		}
//		int start = 0;
//		int len = 5;
//		System.out.println(start + len/2 + len%2);
		split(b, 0, b.length - 1);

		print(b, 0, b.length);





    }



}

