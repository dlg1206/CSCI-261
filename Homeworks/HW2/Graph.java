import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Derek Garcia
 **/


public class Graph {

    final static String BIPARTITE = "bipartite";
    final static String NOT_BIPARTITE = "not bipartite";


    /**
     * Parses input file to create a hashmap graph of given values
     *
     * @param fileName name of input file
     * @return graph
     * @throws IOException filename is bad
     */
    private static HashMap<Integer, Node> parseFile(String fileName) throws IOException {
        // Create new Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        br.readLine();  // trash first line

        HashMap<Integer, Node> graph = new HashMap<>();

        String line = br.readLine();    // get first node info

        int nodeID = 1; // nodes start at 1

        // Read entire file
        while (line != null){

            String[] test = line.split(" ");    // gets adj nodes

            // init node if needed
            if(!graph.containsKey(nodeID)){
                graph.put(nodeID, new Node(nodeID));
            }

            Node curNode = graph.get(nodeID);

            boolean skippedFirst = false;

            // Add all adj nodes
            for(String adj : test){
                // skip over "Node[n]:"
                if(!skippedFirst){
                    skippedFirst = true;
                    continue;
                }

                int adjID = Integer.parseInt(adj);

                // Init adj node if needed
                if(!graph.containsKey(adjID)){
                    graph.put(adjID, new Node(adjID));
                }

                curNode.addEdge(graph.get(adjID));
            }

            nodeID++;   // move to next node
            line = br.readLine();
        }

        br.close();
        return graph;
    }


    /**
     * Does a breadth first search of a given graph at a start node
     * Will recursively go through graph if it detects not all nodes were reached
     *
     * @param componentNum Component number / recursive depth
     * @param startNode Node to start at
     * @param graph graph to search through
     * @return updated graph with nodes set to a layer on a BFS tree
     */
    private static HashMap<Integer, Node> doBFS(int componentNum, int startNode, HashMap<Integer, Node> graph){

        // init vars
        LinkedList<Node> visitQueue = new LinkedList<>();
        boolean bipartite = true;

        // Get initial node
        Node curNode = graph.get(startNode);
        curNode.layer = 0;

        System.out.println("connected component: " + componentNum);

        // Visit all nodes connected to start node
        for(;;){
            System.out.print(curNode.id + "(" + curNode.layer +") ");   // ex. 1(0)

            // Visit all nodes adjacent to current node
            for(Node adj : curNode.adj){

                // Add to visit queue if not visited
                if(adj.layer < 0){
                    adj.layer = curNode.layer + 1;  // adj will always be on the next layer
                    visitQueue.add(adj);
                }

                // Bipartite detection
                if(adj.layer == curNode.layer){
                    bipartite = false;
                }

            }

            // Break when nowhere to visit; else get next node
            if(visitQueue.isEmpty()){
                break;
            } else {
                curNode = visitQueue.pop();
            }

        }

        System.out.println();   // newline

        // Print bipartite status
        if(bipartite){
            System.out.println(BIPARTITE);
        } else {
            System.out.println(NOT_BIPARTITE);
        }

        // check for skipped nodes
        for(Node node : graph.values()){
            // Do another BFS if skipped node found
            if(node.layer < 0){
                doBFS(++componentNum, node.id, graph);
            }
        }

        // Return updated graph
        return graph;
    }


    /**
     * Prints the nodes and its neighbors in a given graph
     *
     * @param graph graph to print
     */
    private static void printGraph(HashMap<Integer, Node> graph){

        System.out.println(graph.size());
        for(Node node : graph.values()){
            System.out.println(node);
        }

        System.out.println();   // newline

    }


    /**
     * Takes in adjacency file and creates a graph
     *
     * @param args file name of adjacent
     * @throws IOException file name is bad
     */
    public static void main(String[] args) throws IOException {
        HashMap<Integer, Node> graph = parseFile(args[0]);
        printGraph(graph);
        doBFS(1,1, graph);


    }

}
