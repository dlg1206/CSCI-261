package ex1;

import java.util.LinkedHashMap;

/**
 * Node for Dijkstra's algorithm
 *
 * @author Derek Garcia
 **/

public class Node {

    private final int id;                               // node id
    private final LinkedHashMap<Node, Integer> edges;   // adj list

    // For Dijkstra's
    private int distance;
    private Integer path;

    /**
     * Node Constructor. builds node
     *
     * @param id id of node
     */
    public Node(int id ) {
        this.id = id;
        this.edges = new LinkedHashMap<>();

        this.distance = -1;     // "infinity" distance, hasn't been set
        this.path = null;       // no parent node

    }

    ///
    /// Getters
    ///

    // get id
    public int getId() {
        return this.id;
    }

    // get edges
    public LinkedHashMap<Node, Integer> getEdges(){
        return this.edges;
    }

    // get distance
    public int getDistance(){
        return this.distance;
    }


    ///
    /// Setters
    ///

    // set distance
    public void setDistance(int distance) {
        this.distance = distance;
    }

    // set parent
    public void setPath(int path) {
        this.path = path;
    }

    ///
    /// Methods
    ///

    /**
     * Adds an edge to this node's adj list
     *
     * @param node adj node
     * @param distance set distance / weight of edge
     */
    public void addEdge(Node node, int distance ) {
        this.edges.put(node, distance);
    }

    /**
     * Gets the distance / weight of an edge connecting an adj node
     *
     * @param otherEnd node on other
     * @return distance, null if node doesn't exist
     */
    public int getEdgeWeight(Node otherEnd){
        return this.edges.get(otherEnd);
    }

    // prints node correctly
    @Override
    public String toString() {
        return this.id + " dist: " + this.distance + " path: " + this.path;
    }

}
