import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Derek Garcia
 **/

public class main {

    public static void main(String[] args){
        String tac = "tac";
        String at = "at";

        int gapCost = 1;
        int mismatchCost = 3;

        int m = tac.length();
        int n = at.length();

        int[][] M = new int[m + 1][n + 1];

        int cost = 0;
        for(int i = 0; i <= m; i++){
            M[i][0] = cost;
            cost += gapCost;
        }

        cost = 0;
        for(int j = 0; j <= n; j++) {
            M[0][j] = cost;
            cost += gapCost;
        }

        int[] list = new int[3];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++ ){
                int misCost;

                if((i-1 < m && j-1 < n) && tac.charAt(i-1) == at.charAt(j-1)){
                    misCost = 0;
                } else {
                    misCost = mismatchCost;
                }

                list[0] = misCost + M[i - 1][j-1];
                list[1] = gapCost + M[i-1][j];
                list[2] = gapCost + M[i][j-1];

                Arrays.sort(list);

                M[i][j] = list[0];


                list[0] = 0;
                list[1] = 0;
                list[2] = 0;

            }
        }

        printM(M);


    }

    public static void printM(int[][] M){
        for(int i = M.length - 1; i >= 0; i--){
            for(int col : M[i]){
                System.out.print(col + " ");
            }
            System.out.println("");

        }
    }
}
