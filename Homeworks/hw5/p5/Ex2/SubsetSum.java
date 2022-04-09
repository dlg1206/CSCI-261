package Ex2;	// todo REMOVE

import java.util.*;
import java.io.*;

public class SubsetSum{
    public static int [][] M;

    public static void main (String [] args) throws FileNotFoundException {
        // read input file
	Scanner sc = new Scanner(new File(args[0]));
        int W = Integer.parseInt(args[1]);
	int n = Integer.parseInt(sc.nextLine());
	int [] itemWts = new int[n+1];
	itemWts[0] = 0;
	for (int i = 1; i <= n; i++)
	    itemWts[i] = Integer.parseInt(sc.nextLine());

	long start = System.currentTimeMillis();
	int maxWeight = subsetSumMem(itemWts, W );
	long stop = System.currentTimeMillis();
	System.out.println("Memoized: Max Weight for " + n + " items = " +
			   maxWeight);
	System.out.println("Time = " + (stop-start));
        System.out.println("\nSolution");
        showSolution(itemWts, W, itemWts.length-1);
	
	start = System.currentTimeMillis();
	maxWeight = subsetSumR(itemWts, W, n);
	stop = System.currentTimeMillis();
	System.out.println("\nRecursive: Max Weight for " + n + " items = " +
			   maxWeight);
	System.out.println("Time = " + (stop-start));
    }

    /**
     *
     * @ param int [] itemWts - an array of the weights of items, itemWts[i]
     *                          is the weight of item i
     *         int W - the capacity allowed
     * @ return int - maximized sum of weights from itemWts <= W
     *                    that is compatible with jobs[j]
     */
    public static int subsetSumMem(int [] itemWts, int W) {
        M = new int [itemWts.length][W+1];	// init matrix

		// for each row in the matrix
		for(int row = 1; row < itemWts.length; row++){

			int curWeight = itemWts[row];

			// for each col in the row
			for(int col = 0; col < W + 1; col++){
				// if col < weight, set this col as prev row value
				if(col < curWeight){
					M[row][col] = M[row - 1][col];
				// else set max weight using / excluding current item
				} else {
					M[row][col] = Math.max(M[row - 1][col], curWeight + M[row - 1][col - curWeight]);
				}
			}
		}

		// return max value
		return M[itemWts.length - 1][W];

    }

    /**
     *
     * @ param int [] itemWts - an array of the weights of items, itemWts[i]
     *                          is the weight of item i
     *         int w - the current capacity allowed
     *         int i - index of item under consideration
     * @ return int - maximized sum of weights from itemWts <= W
     *                    that is compatible with jobs[j]
     */
    public static int subsetSumR(int [] itemWts, int w, int i) {

		// base case
		if(i == 0)
			return 0;

		// If the item weights too much, get next item
		if(w < itemWts[i]){
			return subsetSumR(itemWts, w, i - 1);

		// else calc max weight using / excluding item
		} else {
			return Math.max(
					subsetSumR(itemWts, w, i - 1),
					itemWts[i] + subsetSumR(itemWts, w - itemWts[i], i - 1)
					);
		}
    }

    /**
     *
     * @ param int [] itemWts - an array of the weights of items, itemWts[i]
     *                          is the weight of item i
     *         int w - the current capacity allowed
     *         int i - index of item under consideration
     * @ return int - maximized sum of weights from itemWts <= W
     *                    that is compatible with jobs[j]
     */
    public static void showSolution(int [] itemWts, int w, int i) {

		// base case
		if (i <= 0)
			return;

		// if current index == row below, get next item
		if (M[i][w] == M[i - 1][w])
			showSolution(itemWts, w, i - 1);

		// if cur index == curr weight + weight previous row accounting for curr weight
		if (M[i][w] == itemWts[i] + M[i - 1][w - itemWts[i]]) {
			System.out.println("item " + i + " wt: " + itemWts[i]);

			// update weight and get next item
			showSolution(itemWts, w-itemWts[i], i - 1);
		}

    }
    
}
