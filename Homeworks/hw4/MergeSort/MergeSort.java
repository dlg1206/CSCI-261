import java.util.*;
import java.io.*;

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

    public static void mergeSort(int [] a) {
        // Finish me and add any methods you need to finish me
    }



}

