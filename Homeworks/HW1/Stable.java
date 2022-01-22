import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author Derek Garcia
 **/

public class Stable {


    private static LinkedHashMap<String, ArrayList<String>> parseFile(String fileName) throws IOException {

        // Create new Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        br.readLine();

        LinkedHashMap<String, ArrayList<String>> people = new LinkedHashMap<>();

        String line = br.readLine();    // trash 1st line

        // Read all content lines
        while (line != null && !line.equals("")) {

            String[] info = line.split("\\s");  // strip whitespace

            // Get the first name
            String name = "";
            int n = 0;
            while (name.equals("")) {
                name = info[n++];
            }

            // Add to peoples list if not already added
            if (!people.containsKey(name)) {
                people.put(name, new ArrayList<>());
            }

            // Add preferences
            for (int i = n; i < info.length; i++) {
                if (!info[i].equals("")) {
                    // Add prefix for simplicity
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


    private static LinkedHashMap<String, ArrayList<String>> deepCopy(LinkedHashMap<String, ArrayList<String>> lhm){
        LinkedHashMap<String, ArrayList<String>> copy = new LinkedHashMap<>();

        for(String key : lhm.keySet()){
            copy.put(key, new ArrayList<>());

            for (String pref : lhm.get(key)){
                copy.get(key).add(pref);
            }

        }
        return copy;
    }

    private static LinkedHashMap<String, String> match(LinkedHashMap<String, ArrayList<String>> people) {

        // ONLY men keys
        LinkedHashMap<String, String> matches = new LinkedHashMap<>();

        // Repeat until every man has a match
        while (matches.size() != people.size() / 2) {

            // Cycle through all people
            for (String currMan : people.keySet()) {

                // If a man and free (ie not matched)
                if (currMan.contains("m") && !matches.containsKey(currMan)) {

                    String woman = people.get(currMan).remove(0); // pop list

                    // If woman free, engage
                    if (!matches.containsValue(woman)) {
                        matches.put(currMan, woman);    // engage
                    } else {

                        // Get fiance
                        String fiance = "";
                        for(String man : matches.keySet()){
                            if(matches.get(man).equals(woman)){
                                fiance = man;
                                break;
                            }
                        }

                        // Check woman's preferences
                        for (String wPref : people.get(woman)) {

                            // If woman prefers current man, leaves fiance, fiance becomes free
                            if (wPref.equals(currMan)) {
                                matches.put(wPref, woman);  // new engagement
                                matches.remove(fiance);    // fiance set as "free" (ie unmatched)
                                break;
                            }

                            // If woman's preference matches her fiance before the current man, stay engaged
                            if (wPref.equals(fiance)) {
                                break;  // no need to cont. checking
                            }
                        }
                    }


                }
            }
        }

        return matches;
    }


    private static void output(String destName, LinkedHashMap<String, ArrayList<String>> people,
                               LinkedHashMap<String, String> matches) throws IOException {

        // Make new file and file writer
        File dest = new File(destName);
        FileWriter fw = new FileWriter(dest);

        // Print Size
        fw.write(matches.size() + "\n");

        // Print each person and their preferences
        for (String person : people.keySet()) {
            fw.write(person + " ");
            // Print preferences
            for (String pref : people.get(person)) {
                fw.write(pref.replaceAll("[mw]", "") + " ");    // rid prefix
            }
            fw.write("\n");
        }

        // Print all matches
        for (String man : matches.keySet()) {
            fw.write(man + " " + matches.get(man) + "\n");
        }
        fw.close();
    }


    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, ArrayList<String>> people = parseFile(args[0]);       // Parse Input
        LinkedHashMap<String, String> matches = match(deepCopy(people)); // Generate Matches
        output(args[1], people, matches);                               // Write results to output file
    }
}
