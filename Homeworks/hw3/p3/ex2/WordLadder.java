package ex2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

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

            // init full node if needed
            if(graph[nodeID] == null){
                graph[nodeID] = new Node(nodeID, adjNodes[1]);
            // Else previously defined, just update word
            } else {
                graph[nodeID].setWord(adjNodes[1]);
            }

            Node curNode = graph[nodeID];

            // Add all adj nodes
            for(int i = 2; i < adjNodes.length; i++){

                String adj = adjNodes[i];

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

    /**
     * Completes Dijkstra Algorithm
     *
     * @param graph Original graph to reference
     * @param source Node to start at
     * @return the resulting distance and paths
     */
    private static HashMap<Integer, ArrayList<Node>> doDijkstra(Node[] graph, Node source){

        // init vars
        LinkedList<Node> queue = new LinkedList<>();
        HashMap<Integer, ArrayList<Node>> result = new HashMap<>();

        // Get and set initial node
        Node curNode = source;
        curNode.setDistance(0);

        // add to results
        result.put(0, new ArrayList<>());
        result.get(0).add(curNode);

        // Repeat until nothing is left in the queue
        for( ;; ){

            // Go through all od the adjacent nodes to current node
            for(Node adj : curNode.getEdges().keySet()){

                // Add to queue if adj hasn't been visited and not already in queue
                if(adj.getDistance() < 0 && !queue.contains(adj)){
                    queue.add(adj);
                }

                int newDist = curNode.getDistance() + curNode.getEdgeWeight(adj);   // calculate new distance

                // If distance hasn't been set or the new distance is better
                if(adj.getDistance() < 0 || adj.getDistance() > newDist){

                    // make new key if needed
                    if(!result.containsKey(newDist))
                        result.put(newDist, new ArrayList<>());

                    // if improving distance, remove from old distance
                    if(adj.getDistance() >= 0)
                        result.get(adj.getDistance()).remove(adj);

                    // Update values
                    adj.setDistance(newDist);
                    adj.setPath(curNode);

                    // update result
                    result.get(newDist).add(adj);
                }
            }

            // End if queue is empty, else get next node
            if(queue.isEmpty()){
                break;
            } else {
                curNode = queue.pop();
            }
        }
        return result;
    }

    private static void printResults(HashMap<Integer, ArrayList<Node>> result){

        // sort the keys from least to greatest distance
        ArrayList<Integer> distances = new ArrayList<>(result.keySet());
        Collections.sort(distances);

        // go through all distances
        for(int dist : distances){
            // print each node
            for(Node node : result.get(dist)){
                System.out.print(dist + ": ");

                while(node.getParent() != null){
                    System.out.print(node + "<");
                    node = node.getParent();
                }
                System.out.println(node);

            }
        }
    }

    private static Node getByString(Node[] graph, String word){
       for(int i = 1; i < graph.length; i++){

           if(graph[i].toString().equals(word)){
               return graph[i];
           }
       }

       return null;
    }


    public static void main(String[] args) throws IOException {
        Node[] graph = parseFile(args[0]);

        // get result
        HashMap<Integer, ArrayList<Node>> result;
        if(args.length == 2){
            result = doDijkstra(graph, getByString(graph, args[1]));  // use given source
        } else {
            result = doDijkstra(graph, graph[1]);  // default to Node 1
        }

        printResults(result);


    }
}
