/**
 * Brute force method of finding the closest pair of points
 *
 * @author Derek Garcia
 **/
public class BruteForce extends ClosestPairAlg{


    /**
     * Finds the closest pair of points in a given array using a brute
     * force method
     * @param P array of points to search
     * @return Triple containing a pair of points and their distance
     */
    public Triple closestPair(Point[] P) {

        // Init closest pair with first two points
        Triple closestPair = new Triple(P[0], P[1], P[0].distance(P[1]));

        // Start at every point in the array
        for(Point currPoint : P){

            // compare current with all the other nodes
            for(Point otherPoint : P){

                // if not the same point and distance is less than the current, update closest
                if(currPoint != otherPoint && currPoint.distance(otherPoint) < closestPair.dist){
                    closestPair.p1 = currPoint;
                    closestPair.p2 = otherPoint;
                    closestPair.dist = currPoint.distance(otherPoint);
                }
            }

        }

        return closestPair;
    }
}
