package ex2;

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

        this.distance = -1;
        this.path = null;

    }

    public void addEdge(Node node, int distance ) {
        this.edges.put(node, distance);
    }

    public LinkedHashMap<Node, Integer> getEdges(){
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

    public int getEdgeWeight(Node otherEnd){
        return this.edges.get(otherEnd);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id + " dist: " + this.distance + " path: " + this.path;
    }

}
