/**
 * Merge sort that counts the number of inversions
 *
 * @author Derek Garcia
 **/

public class Inversions {

    /**
     * Recursive divide for merge sort. Divides to get single numbers then merges the results
     * back together
     *
     * @param parent the array to sort
     * @param start the start index of the current subarray in parent
     * @param end the end index of the current subarray in parent
     * @return number of inversions while sorting this subarray
     */
    private static long sortAndCount(int[] parent, int start, int end){
        // init vars
        long rA = 0, rB = 0, r = 0;

        // Split if possible ( start == End means only 1 value, which is base case )
        if(start < end) {

            // Find the middle of the block
            int middle = (start + end) / 2;	// int division always round down

            // Sort and count left half
            rA = sortAndCount(parent, start, middle);

            // Sort and count right half
            rB = sortAndCount(parent, middle + 1, end);	// +1 not to overlap w/ left end

            // merge the two halves together
            r = mergeAndCount(parent, start, middle, end);
        }

        // return total number of inversions
        return rA + rB + r;
    }

    /**
     * Merges sections of the array together
     *
     * @param parent the array to sort
     * @param start the start index of the current subarray in parent
     * @param mid the middle index of the current subarray in parent
     * @param end the end index of the current subarray in parent
     */
    private static long mergeAndCount(int[] parent, int start, int mid, int end){

        // convert ranges to arrays
        int[] A = copyRange(parent, start, mid);
        int[] B = copyRange(parent, mid + 1, end);

        // init counting vars
        int iP = start;     // start location in parent to start adding values
        int iA = 0, iB =  0;    // index for A and B
        long inversions = 0;

        // Repeat until exhaust one or both of the left and right arrays
        while(iA < A.length && iB < B.length){

            // if left head <= right head, insert left at location
            if( A[iA] <= B[iB]){
                parent[iP++] = A[iA++];

            // else insert the right at location
            } else {
                parent[iP++] = B[iB++];
                inversions += A.length - iA;    // update inversions
            }
        }

        // flush arrays
        while(iA < A.length || iB < B.length){

            // flush left
            if(iA < A.length)
                parent[iP++] = A[iA++];

            // flush right
            if(iB < B.length)
                parent[iP++] = B[iB++];
        }

        // return count
        return inversions;
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


    /**
     * Counts the number inversions during a merge sort
     * @param a array to sort
     * @return number of inversions
     */
    public static long inversions(int [] a){
        return sortAndCount(a, 0, a.length - 1);
    }
}
