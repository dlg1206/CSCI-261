import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
Dijkstra's algorithm

@author Derek Garcia
**/

public class Dijkstra {

    /**
     * Parses input file to create a graph of given values
     *
     * @param fileName name of input file
     * @return graph
     * @throws IOException filename is bad
     */
    private static p3Node[] parseFile(String fileName) throws IOException {
        // Create new Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int numNodes = Integer.parseInt(br.readLine().trim());  // get number of nodes

        p3Node[] graph = new p3Node[numNodes + 1];  // +1 to so nodeID == Index

        String line = br.readLine();    // get first node info

        int nodeID = 1; // nodes start at 1

        // Read entire file
        while (line != null){

            String[] adjNodes = line.split(" ");    // gets adj nodes

            // init node if needed
            if(graph[nodeID] == null){
                graph[nodeID] = new p3Node(nodeID);
            }

            p3Node curNode = graph[nodeID];

            boolean skippedFirst = false;

            // Add all adj nodes
            for(String adj : adjNodes){
                // skip over "Node[n]:"
                if(!skippedFirst) {
                    skippedFirst = true;
                    continue;
                }

                // get node and distance
                int adjID = Integer.parseInt(adj.split(":")[0]);
                int distance = Integer.parseInt(adj.split(":")[1]);

                // Init adj node if needed
                if(graph[adjID] == null){
                    graph[adjID] =  new p3Node(adjID);
                }

                curNode.addEdge(graph[adjID], distance);
            }

            nodeID++;   // move to next node
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
    private static HashMap<Integer, ArrayList<p3Node>> doDijkstra(p3Node[] graph, int source){

        // init vars
        LinkedList<p3Node> queue = new LinkedList<>();
        HashMap<Integer, ArrayList<p3Node>> result = new HashMap<>();

        // Get and set initial node
        p3Node curNode = graph[source];
        curNode.setDistance(0);

        // add to results
        result.put(0, new ArrayList<>());
        result.get(0).add(curNode);

        // Repeat until nothing is left in the queue
        for( ;; ){

            // Go through all od the adjacent nodes to current node
            for(p3Node adj : curNode.getEdges().keySet()){

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
                    adj.setPath(curNode.getId());

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


    /**
     * Takes in a file and optional source node to perform dijkstra's algorithm
     *
     * @param args [filename, source]
     * @throws IOException given filename is bad
     */
    public static void main(String[] args) throws IOException {

        // parse given file
        p3Node[] graph = parseFile(args[0]);

        // get result
        HashMap<Integer, ArrayList<p3Node>> result;
        if(args.length == 2){
            result = doDijkstra(graph, Integer.parseInt(args[1]));  // use given source
        } else {
            result = doDijkstra(graph, 1);  // default to 1
        }

        // sort the keys from least to greatest distance
        ArrayList<Integer> distances = new ArrayList<>(result.keySet());
        Collections.sort(distances);

        // go through all distances
        for(int dist : distances){
            // print each node
            for(p3Node node : result.get(dist)){
                System.out.println(node);
            }
        }
    }
}
