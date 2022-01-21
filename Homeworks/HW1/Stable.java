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

        // ONLY men keys
        HashMap<String , String> matches = new HashMap<>();

        // Repeat until every man has a match
        while (matches.size() != people.size() / 2) {

            // Cycle through all people
            for (String man : people.keySet()) {

                // If a man and free (ie not matched)
                if (man.contains("m") && !matches.containsKey(man)) {

                    // Cycle through man's preferences
                    for (String woman : people.get(man)) {
                        System.out.println(man + " is proposing to " + woman);

                        // If woman free, engage
                        if (!matches.containsValue(woman)) {
                            System.out.println(man + " and " + woman + " are engaged!");
                            matches.put(man, woman);
                            break;
                        }
                    }

                    // Check woman's preferences
                    String woman = matches.get(man);
                    for (String wPref : people.get(woman)) {

                        // If woman's preference not engaged, leave current man for preference
                        if (!matches.containsKey(wPref)) {
                            System.out.println("\t" +woman + " left " + man + " for " + wPref + "...");
                            matches.put(wPref, woman);  // new engagement
                            matches.remove(man);    // old man set as "free" (ie unmatched)
                            System.out.println(wPref + " and " + woman + " are engaged!");
                            break;
                        }

                        // If woman's preference matches current man, stay engaged
                        if (wPref.equals(man)) {
                            System.out.println("\t" + woman + " is a happy with " + man + " :)");
                            break;  // no need to cont. checking
                        }
                    }
                } else {

                    if(man.contains("m")){
                        System.out.println(man + " is already engaged");
                    } else {
                        System.out.println("\t" + man + " is a woman 0.o");
                    }

                }
            }
        }

        // replace
        System.out.println("Results");
        for (String man : matches.keySet()) {

            System.out.println("(" + man + ", " + matches.get(man) + ")");
        }

    }


    public static void main(String[] args) throws IOException {

        HashMap<String, ArrayList<String>> people = parseFile(args[0]);
        match(people);
    }
}
