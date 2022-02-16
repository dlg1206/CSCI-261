import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Derek Garcia
 **/


public class Graph {


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

    private static HashMap<Integer, Node> doBFS(int startNode, HashMap<Integer, Node> graph){

        // init vars
        LinkedList<Node> visitQueue = new LinkedList<>();

        Node curNode = graph.get(startNode);
        curNode.layer = 0;

        while(true){

            System.out.print(curNode.id + "(" + curNode.layer +") ");

            for(Node adj : curNode.adj){

                // If adj hasn't been visited
                if(adj.layer < 0){
                    adj.layer = curNode.layer + 1;
                    visitQueue.add(adj);
                }

            }

            if(visitQueue.isEmpty()){
                break;
            } else {
                curNode = visitQueue.pop();
            }

        }



        return graph;
    }

    private static void printGraph(HashMap<Integer, Node> graph){

        System.out.println(graph.size());
        for(Node node : graph.values()){
            System.out.println(node);
        }

    }



    public static void main(String[] args) throws IOException {
        HashMap<Integer, Node> graph = parseFile(args[0]);
        printGraph(graph);
        doBFS(1, graph);


    }

}
