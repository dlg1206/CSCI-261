import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Derek Garcia
 **/


class edge{
    public int end;
    public int weight;

    public edge(int end, int weight){
        this.end=end;
        this.weight = weight;
    }
}

public class shortestPath {

    public static void main(String[] args){

        HashMap<Integer, ArrayList<edge>> g = new HashMap<>();

        g.put(0, new ArrayList<>());
        g.put(1, new ArrayList<>());
        g.put(2, new ArrayList<>());
        g.put(3, new ArrayList<>());

        g.get(0).add(new edge(3, -1));

        g.get(1).add(new edge(0, 3));
        g.get(1).add(new edge(3, 2));

        g.get(2).add(new edge(0, -1));
        g.get(2).add(new edge(1, -5));

        g.get(3).add(new edge(2, 5));


        int[][] M = new int[4][4];

        for(int i = 0; i<4; i++){
            M[0][i] = 10000000;
        }

        M[0][0] = 0;

        for(int i = 1; i < 4; i++){

            for(int v : g.keySet()){
                M[i][v]  = M[i-1][v];
            }

            for(int v : g.keySet()){
                for(edge e : g.get(v)){
                    int b = e.weight + M[i-1][e.end];

                    if(b < M[i][v]){
                        M[i][v] = b;
                    }
                }
            }


        }

        printM(M);
    }

    public static void printM(int[][] M){
        for(int i = M.length - 1; i >= 0; i--){
            System.out.print(i + ": ");
            for(int col : M[i]){
                System.out.print(col + " ");
            }
            System.out.println("");

        }
    }
}
