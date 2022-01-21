import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Derek Garcia
 **/

public class Stable {



    private static HashMap<Person, ArrayList<Integer>> parseFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        br.readLine();

        HashMap<Person, ArrayList<Integer>> people = new HashMap<>();

        String line = br.readLine();

        while (line != null && !line.equals("") ){

            String[] info = line.split("\\s");

            Person person;

            if(info[0].equals("")){
                line = br.readLine();
                continue;
            }

            if(info[0].charAt(0) == 'm'){
                person = new Person(Gender.MAN, Integer.parseInt(info[0].substring(1) ));
            } else {
                person = new Person(Gender.WOMAN, Integer.parseInt(info[0].substring(1) ));
            }
            people.put(person, new ArrayList<>());

            for(int i = 1; i < info.length; i++){
                if(!info[i].equals("")){
                    people.get(person).add(Integer.parseInt(info[i]));
                }

            }

            line = br.readLine();
        }

        br.close();

        return people;

    }


    public static void main(String[] args) throws IOException {

        HashMap<Person, ArrayList<Integer>> people = parseFile(args[0]);
    }
}
