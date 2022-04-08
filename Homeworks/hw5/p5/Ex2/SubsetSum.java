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
        M = new int [itemWts.length][W+1];

		for(int row = 0; row < itemWts.length; row++){
			int curWeight = itemWts[row];

			for(int col = 0; col < W + 1; col++){
				if(col < curWeight){
					M[row][col] = M[row][col];
				} else {
					M[row][col] = Math.max(M[row][col], curWeight + M[row][col - curWeight]);
				}

			}

			System.out.println(M[row]);
		}

		for(int r = itemWts.length - 1; r >= 0; r--){

			for(int c = 0; c < W+1; c++){
				System.out.print(M[r][c] + " ");
			}
			System.out.println();
		}

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

	// FINISH ME
	return 0;
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
	// FINISH ME
    }
    
}
