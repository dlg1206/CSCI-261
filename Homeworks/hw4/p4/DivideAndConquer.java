import java.util.ArrayList;
import java.util.Arrays;

/**
 * Uses a recursive divide and conquer method to find
 * closet pair in O(n log n)
 *
 * @author Derek Garcia
 **/

public class DivideAndConquer extends ClosestPairAlg{

    // Enums for sorting
    private enum Coordinate{
        X,
        Y
    }

    /**
     * Sorts given array by X or Y coordinate value
     *
     * @param P array to sort
     * @param coordinate X or Y to sort by
     */
    private static void sortBy(Point[] P, Coordinate coordinate){
        // init counting var
        int i = 0;

        // Convert Point to Pointx
        if (coordinate == Coordinate.X) {
            for (Point p : P) {
                P[i++] = new Pointx(p.x, p.y);
            }

        // else convert to Pointy
        }else{
            for (Point p : P) {
                P[i++] = new Pointy(p.x, p.y);
            }
        }

        // Sort by X or Y coordinate
        Arrays.sort(P);
    }

    /**
     * Removes all points with a distance greater from the middle
     * point and the distance given
     *
     * @param P array to truncate
     * @param dist distance to from middle
     * @return truncated P
     */
    private Point[] truncate(Point[] P, double dist){

        // get bounds
        double min = P[P.length / 2].x - dist ;
        double max = P[P.length / 2].x + dist ;

        ArrayList<Point> truncated = new ArrayList<>();

        for(Point p : P){
            if(p.x > min && p.x < max){
                truncated.add(p);
            }
        }

        return truncated.toArray(new Point[0]);

    }

    public Triple closestPair(Point[] P){

        // if length <= 3, use brute force method
        if( P.length <= 3){
            return new BruteForce().closestPair(P);
        }

        sortBy(P, Coordinate.X);

        int midI = (P.length / 2) - 1;

        Point[] leftP = new Point[midI + 1];
        Point[] rightP = new Point[P.length - midI - 1];


        int leftI = 0, rightI = 0;
        for (Point p : P) {
            if (p.x <= P[midI].x) {
                leftP[leftI++] = p;
            } else {
                rightP[rightI++] = p;
            }
        }

        Triple left = closestPair(leftP);
        Triple right = closestPair(rightP);

        Triple closestPair;

        if( left.dist < right.dist){
            closestPair = left;
        } else {
            closestPair = right;
        }

        P = truncate(P, closestPair.dist);

        sortBy(P, Coordinate.Y);

        for(int i = 0; i < P.length / 2; i++){

            if( P[i].distance(P[P.length - 1 - i]) < closestPair.dist){
                closestPair.p1 = P[i];
                closestPair.p2 = P[P.length - 1 - i];
                closestPair.dist = P[i].distance(P[P.length - 1 - i]);
            }
        }


        return closestPair;
    }
}
