import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class Population {

    private ArrayList<Person> men;
    private ArrayList<Person> women;

    public Population(){
        this.men = new ArrayList<>();
        this.women = new ArrayList<>();
    }

    public void addMan(Person person){
        this.men.add(person);
    }

    public void addWoman(Person person){
        this.women.add(person);
    }
}
