package truck;

/**
 * @author Derek Garcia
 **/

public class Package {
    public final int ID;
    public final int weight;

    public Package(int ID, int weight){
        this.ID=ID;
        this.weight = weight;
    }

    @Override
    public String toString(){
        return "[p" + ID + ":w" + weight + "]";
    }

}
