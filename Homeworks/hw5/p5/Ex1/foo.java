package Ex1;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;



/**
 * @author Derek Garcia
 **/
class Joob
{
    int start, finish, profit;

    Joob(int start, int finish, int profit)
    {
        this.start = start;
        this.finish = finish;
        this.profit = profit;
    }
}
public class foo {

    // A class to store a Joob
    

    
        // Function to find the index of the last Joob which doesn't conflict with the
        // given Joob. It performs a linear search on the given list of Joobs.
        public static int findLastNonConflictingJoob(List<Joob> Joobs, int n)
        {
            // find the last Joob index whose finish time is less than or equal to the
            // given Joob's start time
            for (int i = n - 1; i >= 0; i--)
            {
                if (Joobs.get(i).finish <= Joobs.get(n).start) {
                    return i;
                }
            }

            // return the negative index if no non-conflicting Joob is found
            return -1;
        }

        // A recursive function to find the maximum profit subset of non-overlapping
        // Joobs, which are sorted according to finish time
        public static int findMaxProfit(List<Joob> Joobs, int n)
        {
            // base case
            if (n < 0) {
                return 0;
            }

            // return if only one item is remaining
            if (n == 0) {
                return Joobs.get(0).profit;
            }

            // find the index of the last non-conflicting Joob with the current Joob
            int index = findLastNonConflictingJoob(Joobs, n);

            // include the current Joob and recur for non-conflicting Joobs `[0, index]`
            int incl = Joobs.get(n).profit + findMaxProfit(Joobs, index);

            // exclude the current Joob and recur for remaining items `[0, n-1]`
            int excl = findMaxProfit(Joobs, n - 1);

            // return the maximum profit by including or excluding the current Joob
            return Math.max(incl, excl);
        }

        // Wrapper over `findMaxProfit()` function
        public static int findMaxProfit(List<Joob> Joobs)
        {
            // sort Joobs in increasing order of their finish times
            Collections.sort(Joobs, Comparator.comparingInt(x -> x.finish));

            return findMaxProfit(Joobs, Joobs.size() - 1);
        }

        public static void main(String[] args)
        {
            List<Joob> Joobs = Arrays.asList(
                    new Joob(1, 3, 6),
                    new Joob(2, 5, 4),
                    new Joob(3, 4, 7),
                    new Joob(3, 5, 2),
                    new Joob(4, 5, 7),
                    new Joob(4, 5, 1),
                    new Joob(1, 3, 1),
                    new Joob(2, 4, 4),
                    new Joob(0, 4, 6),
                    new Joob(1, 3, 5),
                    new Joob(3, 5, 3),
                    new Joob(0, 2, 6),
                    new Joob(0, 2, 7),
                    new Joob(1, 2, 7),
                    new Joob(3, 5, 5),
                    new Joob(4, 5, 10),
                    new Joob(2, 4, 10),
                    new Joob(2, 3, 2),
                    new Joob(3, 4, 5),
                    new Joob(3, 5, 2)
            );

            System.out.print("The maximum profit is " + findMaxProfit(Joobs));
        }
    
}
