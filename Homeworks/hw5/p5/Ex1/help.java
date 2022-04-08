package Ex1;

/**
 * @author Derek Garcia
 **/

import java.util.*;
class GFG
{

    // A job has start time, finish time and profit.
    static class Job
    {
        int ID, start, finish, profit;
        Job(int ID, int start, int finish, int profit)
        {
            this.ID = ID;
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return Integer.toString(ID);
        }
    }

    // Find the latest job (in sorted array) that doesn't
// conflict with the job[i]. If there is no compatible job,
// then it returns -1.
    static int[] compat = new int[21];
    static int latestNonConflict(Job arr[], int i)
    {
        for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j].finish <= arr[i - 1].start){
//                System.out.println("p(" + arr[i-1] +"): " + arr[j]);
                compat[arr[i-1].ID] = arr[j].ID;
                return j;
            }

        }
//        System.out.println("p(" + arr[i-1] +"): 0");
        return -1;
    }

    // A recursive function that returns the maximum possible
// profit from given array of jobs. The array of jobs must
// be sorted according to finish time.
    static int findMaxProfitRec(Job arr[], int n)
    {
        // Base case
        if (n == 1) return arr[n-1].profit;

        // Find profit when current job is included
        int inclProf = arr[n-1].profit;
        int i = latestNonConflict(arr, n);

        var source = arr[n - 1].ID;
        var prev = 0;
        if(i < 0){
            prev = arr[i + 1].ID;
        } else {
            prev = arr[i].ID;
        }

        if (i != -1)
            inclProf += findMaxProfitRec(arr, i+1);

        // Find profit when current job is excluded
        int exclProf = findMaxProfitRec(arr, n-1);

        return Math.max(inclProf, exclProf);
    }

    // The main function that returns the maximum possible
// profit from given array of jobs
    static int findMaxProfit(Job arr[], int n)
    {
        // Sort jobs according to finish time
        Arrays.sort(arr,new Comparator<Job>(){
            public int compare(Job j1,Job j2)
            {
                return j1.finish-j2.finish;
            }
        });

        return findMaxProfitRec(arr, n);
    }


    // Driver program
    public static void main(String args[])
    {
        int m = 20;
        Job arr[] = new Job[m];
        arr[0] = new Job(1,1, 3, 6);
        arr[1] =  new Job(2,2, 5, 4);
        arr[2] = new Job(3,3, 4, 7);
        arr[3] =  new Job(4,3, 5, 2);
        arr[4] =   new Job(5,4, 5, 7);
        arr[5] =    new Job(6,4, 5, 1);
        arr[6] =    new Job(7,1, 3, 1);
        arr[7] =    new Job(8,2, 4, 4);
        arr[8] =     new Job(9,0, 4, 6);
        arr[9] =    new Job(10, 1, 3, 5);
        arr[10] =    new Job(11, 3, 5, 3);
        arr[11] =    new Job(12,0, 2, 6);
        arr[12] =    new Job(13,0, 2, 7);
        arr[13] =    new Job(14, 1, 2, 7);
        arr[14] =    new Job(15,3, 5, 5);
        arr[15] =    new Job(16,4, 5, 10);
        arr[16] =    new Job(17,2, 4, 10);
        arr[17] =    new Job(18,2, 3, 2);
        arr[18] =    new Job(19, 3, 4, 5);
        arr[19] =     new Job(20,3, 5, 2);
        int n =arr.length;
        System.out.println("The optimal profit is " + findMaxProfit(arr, n));
    }
}
