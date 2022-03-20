import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Derek Garcia
 **/

public class DivideAndConquer extends ClosestPairAlg{


    private enum Coordinate{
        X,
        Y
    }

    private Point[] sortBy(Point[] P, Coordinate coord){
        int i = 0;

        if(coord == Coordinate.X){
            Pointx[] x = new Pointx[P.length];


            for(Point p : P){
                x[i++] = new Pointx(p.x, p.y);
            }

            Arrays.sort(x);

            return x;
        } else {
            Pointy[] y = new Pointy[P.length];

            for(Point p : P){
                y[i++] = new Pointy(p.x, p.y);
            }

            Arrays.sort(y);

            return y;
        }

    }



    private Point[] truncate(Point[] P, double dist){
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

        P = sortBy(P, Coordinate.X);

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

        P = sortBy(P, Coordinate.Y);

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
