package ex2;

import java.util.LinkedHashMap;

/**
 * Node for Word ladder
 *
 * @author Derek Garcia
 **/

public class Node {

    private final int id;                               // node id
    private String word;
    private final LinkedHashMap<Node, Integer> edges;   // adj list

    private int distance = -1;
    private Node path = null;


    /**
     * Node Constructor. builds node
     *
     * @param id id of node
     */
    public Node(int id, String word) {
        this.id = id;
        this.word = word;
        this.edges = new LinkedHashMap<>();
    }

    public Node(int id){
        this.id = id;
        this.edges = new LinkedHashMap<>();
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

    //get parent
    public Node getParent(){
        return this.path;
    }


    ///
    /// Setters
    ///

    // set distance
    public void setDistance(int distance) {
        this.distance = distance;
    }

    // set parent
    public void setPath(Node path) {
        this.path = path;
    }

    // set word
    public void setWord(String word){
        this.word = word;
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
        return this.word;
    }

}
