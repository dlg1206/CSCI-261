import java.util.LinkedHashMap;

/**
 * @author Derek Garcia
 **/

public class p3Node {

    private final int id;
    private final LinkedHashMap<p3Node, Integer> edges;

    public p3Node( int id ) {
        this.id = id;
        this.edges = new LinkedHashMap<>();
    }

    public void addEdge( p3Node node, int weight ) {
        this.edges.put(node, weight);
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Node[" + this.id + "]:");
        for (p3Node node: this.edges.keySet())
            str.append(" ").append(node.id).append(":").append(this.edges.get(node));
        return str.toString();
    }

}
