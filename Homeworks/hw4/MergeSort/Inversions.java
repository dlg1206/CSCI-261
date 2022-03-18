import java.util.LinkedList;

/**
 * @author Derek Garcia
 **/

public class Inversions {

    /**
     * Recursive divide for merge sort. Divides to get single numbers then merges the results
     * back together
     *
     * @param a the array to sort
     * @param lStart the index of the leftmost value in the current block
     * @param rEnd the index of the rightmost value in the current block
     */
    private static long sortAndCount(int[] parent, int start, int end){
        // init vars
        long rA = 0, rB = 0, r = 0;

        // Split if possible ( lStart == rEnd means only 1 value, which is base case )
        if(start != end){
            // Find the middle of the block
            int middle = (start + end) / 2;	// int division always round down

            // Sort and count left half
            rA = sortAndCount(parent, start, middle);

            // Sort and count right half
            rB = sortAndCount(parent, middle + 1, end);	// +1 not to overlap w/ left end

            // merge the two halves together
            r = mergeAndCount(parent, start, middle, end);
        }

        return rA + rB + r;


    }

    /**
     * Merges sections of the array together
     *
     * @param a the array to sort
     * @param lStart the index of the leftmost value in the current block
     * @param mid the "middle" of the block. If length is odd, will round down
     * @param rEnd the index of the rightmost value in the current block
     */
    private static long mergeAndCount(int[] parent, int start, int mid, int end){

        // convert ranges to arrays
        int[] A = copyRange(parent, start, mid);
        int[] B = copyRange(parent, mid + 1, end);

        int iP = start;

        int iA = 0, iB =  0;

        long numInversions = 0;

        // Repeat until exhaust one or both of the left and right arrays
        while(iA < A.length && iB < B.length){

            // if left head < right head, insert left at location
            if( A[iA] < B[iB]){
                parent[iP++] = A[iA++];

            // else insert the right at location
            } else {
                parent[iP++] = B[iB++];
                numInversions += A.length - iA;
            }
        }


        while(iA < A.length || iB < B.length){

            if(iA < A.length)
                parent[iP++] = A[iA++];

            if(iB < B.length)
                parent[iP++] = B[iB++];

        }

        return numInversions;
    }


    /**
     * Copies a given range from an existing array
     *
     * @param a array to copy from
     * @param start starting index (inclusive)
     * @param end ending index (inclusive)
     * @return a linked list of copied values
     */
    private static int[] copyRange(int[] a, int start, int end){

        // init copy
        int[] copy = new int[end - start + 1];  // +1 account for index start at 0

        // add section to copy
        int copyI = 0;
        for(int i = start; i <= end; i++){
            copy[copyI++] = a[i];
        }

        // return result
        return copy;
    }


    public static long inversions(int [] a){

//        int[] b = {6, 5, 12, 10, 9, 1};
//        sortAndCount(b, 0, b.length - 1);
//
//        for(int i : b){
//            System.out.println(i);
//        }
        return sortAndCount(a, 0, a.length - 1);
    }
}
