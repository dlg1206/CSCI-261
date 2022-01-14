import java.io.*;

/**
 * @author Derek Garcia
 **/

public class Stable {



    private static void parseFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = br.readLine();

        while (line != null){

            String[] info = line.split("\\s");

            if(info[0].charAt(0) == 'm'){

                Person newMan = new Person(Gender.MAN, Integer.parseInt(info[0].substring(1) ));
            } else if (info[0].charAt(0) == 'w') {

            }
            line = br.readLine();
        }

        br.close();

    }


    public static void main(String[] args) throws IOException {

        parseFile(args[0]);
    }
}
