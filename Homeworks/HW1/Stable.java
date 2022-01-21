import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Derek Garcia
 **/

public class Stable {



    private static HashMap<String, ArrayList<String>> parseFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        br.readLine();

        HashMap<String, ArrayList<String>>  people = new HashMap<>();

        String line = br.readLine();

        while (line != null && !line.equals("") ){

            String[] info = line.split("\\s");

            String name = "";
            int n = 0;
            while (name.equals("")){
                name = info[n++];
            }

            if(!people.containsKey(name)){
                people.put(name, new ArrayList<>());
            }


            for(int i = n; i < info.length; i++){

                if(!info[i].equals("")){

                    switch (name.charAt(0)) {
                        case 'm' -> people.get(name).add("w" + info[i]);
                        case 'w' -> people.get(name).add("m" + info[i]);
                    }

                }

            }

            line = br.readLine();
        }

        br.close();

        return people;

    }


    private static void match(HashMap<String, ArrayList<String>> people){

        // ONLY men
        HashMap<String , String> matches = new HashMap<>();

        for(String man: people.keySet()){

            // if man and free
            if(man.contains("m") && !matches.containsKey(man)){

                for(String woman : people.get(man)){

                    System.out.println(man + " is proposing to " + woman);
                    // if women is free
                    if(!matches.containsValue(woman)){
                        // engage
                        System.out.println(man + " and " + woman + " are engaged!");
                        matches.put(man, woman );
                        break;
                    }
                }

                String currWoman = matches.get(man);

                // Check woman pref
                for(String pref : people.get(currWoman)){

                    // if higher pref not match, enage
                    if(!matches.containsKey(pref)){
                        System.out.println(currWoman + " left " + man + " for " + pref + "...");
                        matches.put(pref, currWoman);
                        matches.remove(man);
                        System.out.println(pref + " and " + currWoman + " are engaged!");
                        break;
                    }

                    // break when reach curr fiance
                    if(pref.equals(man)){
                        break;
                    }
                }
            } else {
                System.out.println(man + " is a woman 0.o");
            }
        }

        // replace
        for(String man : matches.keySet()){
            System.out.println("(" + man + ", " + matches.get(man) + ")");
        }
    }


    public static void main(String[] args) throws IOException {

        HashMap<String, ArrayList<String>> people = parseFile(args[0]);
        match(people);
    }
}
