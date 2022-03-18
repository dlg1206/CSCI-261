package truck;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class Truck {

    private final int MAX_WEIGHT = 2;

    public final int ID;
    private int curWeight;
    private ArrayList<Package> content;

    public Truck(int ID){
        this.ID = ID;
        this.curWeight = 0;
        this.content= new ArrayList<>();
    }

    public boolean addPackage(Package pkg){
        if( curWeight + pkg.weight > MAX_WEIGHT){
            return false;
        }
        this.curWeight += pkg.weight;
        content.add(pkg);
        return true;
    }

    public boolean isFull(){
        return curWeight == MAX_WEIGHT;
    }

    @Override
    public String toString(){
        return "Truck: " + ID
                + "\n\tWeight: " + curWeight + "/" + MAX_WEIGHT
                + "\n\tPackages: " + content;
    }
}
