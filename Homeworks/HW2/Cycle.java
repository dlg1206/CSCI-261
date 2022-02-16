import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Derek Garcia
 **/


public class Cycle {

    final static String BIPARTITE = "bipartite";
    final static String NOT_BIPARTITE = "not bipartite";


    /**
     * Parses input file to create a hashmap graph of given values
     *
     * @param fileName name of input file
     * @return graph
     * @throws IOException filename is bad
     */
    private static Node[] parseFile(String fileName) throws IOException {
        // Create new Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int numNodes = Integer.parseInt(br.readLine());  // get number of nodes

        Node[] graph = new Node[numNodes + 1];  // +1 to so nodeID == Index

        String line = br.readLine();    // get first node info

        int nodeID = 1; // nodes start at 1

        // Read entire file
        while (line != null){

            String[] adjNodes = line.split(" ");    // gets adj nodes

            // init node if needed
            if(graph[nodeID] == null){
                graph[nodeID] = new Node(nodeID);
            }

            Node curNode = graph[nodeID];

            boolean skippedFirst = false;

            // Add all adj nodes
            for(String adj : adjNodes){
                // skip over "Node[n]:"
                if(!skippedFirst){
                    skippedFirst = true;
                    continue;
                }

                int adjID = Integer.parseInt(adj);

                // Init adj node if needed
                if(graph[adjID] == null){
                    graph[adjID] =  new Node(adjID);
                }

                curNode.addEdge(graph[adjID]);
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
     * Updates given graph
     *
     * @param componentNum Component number / recursive depth
     * @param startNode Node to start at
     * @param graph graph to search through
     */
    private static void doBFS(int componentNum, int startNode, Node[] graph){

        // init vars
        LinkedList<Node> visitQueue = new LinkedList<>();
        boolean bipartite = true;

        // Get initial node
        Node curNode = graph[startNode];
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
        for(Node node : graph){
            // Do another BFS if skipped node found
            if(node != null && node.layer < 0){
                doBFS(++componentNum, node.id, graph);
            }
        }

    }


    /**
     * Prints the nodes and its neighbors in a given graph
     *
     * @param graph graph to print
     */
    private static void printGraph(Node[] graph){

        System.out.println(graph.length - 1);   // skip null graph[0]
        for(Node node : graph){
            if(node != null){
                System.out.println(node);
            }

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
        Node[] graph = parseFile(args[0]);
        printGraph(graph);
        doBFS(1,1, graph);


    }

}
