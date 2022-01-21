import java.util.ArrayList;

/**
 *
 * @author Derek Garcia
 **/

enum Status{
    SINGLE,
    MATCHED
}

public class Person {
    private Gender gender;
    private int ID;
    private Status status;

    public Person(Gender gender, int ID){
        this.gender = gender;
        this.ID = ID;
        this.status = Status.SINGLE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }
}
