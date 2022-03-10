import java.util.LinkedHashMap;

/**
 * @author Derek Garcia
 **/

public class p3Node {

    private final int id;
    private final LinkedHashMap<p3Node, Integer> edges;


    private int distance;
    private Integer path;

    public p3Node( int id ) {
        this.id = id;
        this.edges = new LinkedHashMap<>();

        this.distance = -1;
        this.path = null;

    }

    public void addEdge( p3Node node, int distance ) {
        this.edges.put(node, distance);
    }

    public LinkedHashMap<p3Node, Integer> getEdges(){
        return this.edges;
    }


    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance(){
        return this.distance;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public int getEdgeWeight(p3Node otherEnd){
        return this.edges.get(otherEnd);
    }

    @Override
    public String toString() {
        return this.id + " dist: " + this.distance + " path: " + this.path;
    }

}
