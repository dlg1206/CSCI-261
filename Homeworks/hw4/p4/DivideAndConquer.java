import java.util.ArrayList;
import java.util.Arrays;

/**
 * Recursive divide and conquer method
 *
 * @author Derek Garcia
 **/

public class DivideAndConquer extends ClosestPairAlg{

    /**
     * Truncates any point outside the range of the
     * given distance
     *
     * @param P array to truncate
     * @param dist distance
     * @return truncated array converted to Pointy for later sorting
     */
    private Point[] truncate(Point[] P, double dist){

        // get range
        double min = P[P.length / 2].x - dist ;
        double max = P[P.length / 2].x + dist ;

        ArrayList<Pointy> truncated = new ArrayList<>();

        // Check each point and add it if inside range
        for(Point p : P){
            if(p.x > min && p.x < max){
                truncated.add(new Pointy(p.x, p.y));    // convert to Pointy for sorting
            }
        }

        // return updated range
        return truncated.toArray(new Point[0]);

    }

    /**
     * Recursive function that finds the closest pair of points in an
     * array of points
     * @param P array of points to search
     * @return pair of closest points
     */
    public Triple closestPair(Point[] P){

        // if length <= 3, use brute force method
        if( P.length <= 3){
            return new BruteForce().closestPair(P);
        }

        // convert points to pointx
        int m = 0;
        for (Point p : P){
            P[m++] = new Pointx(p.x, p.y);
        }

        Arrays.sort(P);     // sort based on x value

        int midI = (P.length / 2) - 1;  // get dividing line

        // init left and right arrays
        Point[] leftP = new Point[midI + 1];
        Point[] rightP = new Point[P.length - midI - 1];

        // add points left / right of middle to respective arrays
        int leftI = 0, rightI = 0;
        for (Point p : P) {
            if (p.x <= P[midI].x) {
                leftP[leftI++] = p;
            } else {
                rightP[rightI++] = p;
            }
        }

        // find the closest pair on each side
        Triple left = closestPair(leftP);
        Triple right = closestPair(rightP);

        // get the closer pair of the 2
        Triple closestPair;
        if( left.dist < right.dist){
            closestPair = left;
        } else {
            closestPair = right;
        }

        // rid of all points outside range of the distance
        P = truncate(P, closestPair.dist);

        Arrays.sort(P);     // sort based on y values

        // check all node pairs opposite each other from middle to see if distance is closer
        for(int i = 0; i < P.length / 2; i++){
            // if closer, update closest pair
            if( P[i].distance(P[P.length - 1 - i]) < closestPair.dist){
                closestPair.p1 = P[i];
                closestPair.p2 = P[P.length - 1 - i];
                closestPair.dist = P[i].distance(P[P.length - 1 - i]);
            }
        }

        return closestPair;
    }
}
