import java.util.*;
import java.io.*;

/**
 * Merge Sort function
 *
 * @author Derek Garcia
 */
public class MergeSort {

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

	/**
	 * Recursive divide for merge sort. Divides to get single numbers then merges the results
	 * back together
	 *
	 * @param a the array to sort
	 * @param lStart the index of the leftmost value in the current block
	 * @param rEnd the index of the rightmost value in the current block
	 */
	public static void split(int[] a, int lStart, int rEnd){

		// Split if possible ( lStart == rEnd means only 1 value, which is base case )
		if(lStart != rEnd){

			// Find the middle of the block
			int middle = (lStart + rEnd) / 2;	// int division always round down

			// split left
			split(a, lStart, middle);

			// split right half
			split(a, middle + 1, rEnd);	// +1 not to overlap w/ left end

			// merge the two halves together
			merge(a, lStart, middle, rEnd);
		}



	}

	/**
	 * Merges sections of the array together
	 *
	 * @param a the array to sort
	 * @param lStart the index of the leftmost value in the current block
	 * @param mid the "middle" of the block. If length is odd, will round down
	 * @param rEnd the index of the rightmost value in the current block
	 */
	private static void merge(int[] a, int lStart, int mid, int rEnd){

		// make copies of a given section of array a
		LinkedList<Integer> left = copyRange(a, lStart, mid);
		LinkedList<Integer> right = copyRange(a, mid + 1, rEnd);		// +1 not to overlap w/ left end

		// Repeat until exhaust one or both of the left and right arrays
		while(!left.isEmpty() && !right.isEmpty()){

			// if left head < right head, insert left at location
			if( left.getFirst() < right.getFirst() ){
				a[lStart++] = left.pop();
			// else insert the right at location
			} else {
				a[lStart++] = right.pop();
			}
		}

		// flush left
		while(!left.isEmpty()){
			a[lStart++] = left.pop();
		}

		// flush right
		while(!right.isEmpty()){
			a[lStart++] = right.pop();
		}
	}


	/**
	 * Copies a given range from an existing array
	 *
	 * @param a array to copy from
	 * @param start starting index (inclusive)
	 * @param end ending index (inclusive)
	 * @return a linked list of copied values
	 */
	private static LinkedList<Integer> copyRange(int[] a, int start, int end){

		// init copy
		LinkedList<Integer> copy = new LinkedList<>();

		// add section to copy
		for(int i = start; i <= end; i++){
			copy.add(a[i]);
		}

		// return result
		return copy;
	}

	/**
	 * Initiates the recursive merge sort
	 * @param a array to sort
	 */
    public static void mergeSort(int [] a) {
		split(a, 0, a.length - 1);
    }



}

