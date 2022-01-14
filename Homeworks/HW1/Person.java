import java.util.ArrayList;

/**
 *
 * @author Derek Garcia
 **/

public class Person {
    private Gender gender;
    private int ID;
    private ArrayList<Integer> preferences;

    public Person(Gender gender, int ID){
        this.gender = gender;
        this.ID = ID;
        this.preferences = new ArrayList<>();
    }

    public void addPref(int ID){
        this.preferences.add(ID);
    }
}
