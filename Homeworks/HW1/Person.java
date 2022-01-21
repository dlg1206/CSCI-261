import java.util.ArrayList;

/**
 *
 * @author Derek Garcia
 **/

public class Person {
    private Gender gender;
    private Status status;
    private ArrayList<String> preferences;
    private ArrayList<String> proposedTo;

    public Person(Gender gender, int ID){
        this.gender = gender;
        this.status = Status.FREE;
        this.preferences = new ArrayList<>();
        this.proposedTo = new ArrayList<>();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Gender getGender() {
        return gender;
    }

    public void proposedTo(String woman){
        this.proposedTo.add(woman);
    }

    public boolean hasProposedTo(String woman){
        return this.proposedTo.contains(woman);
    }
}
