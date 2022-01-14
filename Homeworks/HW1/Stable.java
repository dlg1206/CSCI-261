import java.io.*;

/**
 * @author Derek Garcia
 **/

public class Stable {



    private static void parseFile(String fileName) throws IOException {
        //fileName = "Homeworks/HW1/Test Files/2.in";
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = br.readLine();

        while (line != null){
            System.out.println(line);
            line = br.readLine();
        }

        br.close();

    }


    public static void main(String[] args) throws IOException {

        parseFile(args[0]);
    }
}
