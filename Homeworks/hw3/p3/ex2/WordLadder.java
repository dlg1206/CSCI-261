package ex2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Derek Garcia
 **/

public class WordLadder {


    /**
     * Parses input file to create a graph of given values
     *
     * @param fileName name of input file
     * @return graph
     * @throws IOException filename is bad
     */
    private static Node[] parseFile(String fileName) throws IOException {
        // Create new Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int numNodes = Integer.parseInt(br.readLine().trim());  // get number of nodes

        Node[] graph = new Node[numNodes + 1];  // +1 to so nodeID == Index

        String line = br.readLine();    // get first node info

        // Read entire file
        while (line != null){

            int nodeID = Integer.parseInt(line.split("[\\[\\]]")[1]);   // parse line to get ID

            String[] adjNodes = line.split(" ");    // gets adj nodes

            // init node if needed
            if(graph[nodeID] == null){
                graph[nodeID] = new Node(nodeID);
            }

            Node curNode = graph[nodeID];

            boolean skippedFirst = false;

            // Add all adj nodes
            for(String adj : adjNodes){

                // get node and distance
                int adjID = Integer.parseInt(adj.split(":")[0]);
                int distance = Integer.parseInt(adj.split(":")[1]);

                // Init adj node if needed
                if(graph[adjID] == null){
                    graph[adjID] =  new Node(adjID);
                }

                curNode.addEdge(graph[adjID], distance);
            }

            line = br.readLine();
        }

        br.close();
        return graph;
    }


    public static void main(String[] args) throws IOException {
        Node[] graph = parseFile(args[0]);
    }
}
