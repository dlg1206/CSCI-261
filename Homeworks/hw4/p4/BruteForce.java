/**
 * @author Derek Garcia
 **/

public class BruteForce extends ClosestPairAlg{

    public Triple closestPair(Point[] P) {


        /*
        1 - get init point
        2 for each point, compare w/ current
        3 if closer than current, update
         */
        // init first dist
        Triple closestPair = new Triple(P[0], P[1], P[0].distance(P[1]));

        for(Point currPoint : P){

            for(Point otherPoint : P){

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
