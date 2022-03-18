import java.util.*;
import java.io.*;

public class TestInversions {

    public static void main(String [] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new File(args[0]));
	int n = sc.nextInt();

	int [] a = new int[n];
	for (int i =0;  i < n; i++)
	    a[i] = sc.nextInt();

	int [] b = Arrays.copyOf(a, a.length); // save for use by brute

	System.out.println("Using Inversions.inversions");	
        long start = System.currentTimeMillis();
	long inversions = Inversions.inversions(a);
	long stop = System.currentTimeMillis();

        if (n < 20)
           System.out.println("List: " + Arrays.toString(a));
	System.out.println("Inversions: " + inversions);
	System.out.println("Time: " + (stop-start) + " ms");

	System.out.println("Using brute force");
	start = System.currentTimeMillis();
	inversions = bruteInversions(b);
	stop = System.currentTimeMillis();
        if (n < 20)
           System.out.println("List: " + Arrays.toString(a));
	System.out.println("Inversions: " + inversions);
	System.out.println("Time: " + (stop-start) + " ms");	
	
    }

    public static long bruteInversions(int [] a) {
	long count = 0;
	for (int i = 0; i < a.length; i++)
	    for (int j = i+1; j < a.length; j++)
		if (a[i] > a[j])
		    count++;
	return count;
    }
}

