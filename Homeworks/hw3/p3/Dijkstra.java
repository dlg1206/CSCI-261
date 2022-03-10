import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**


@author Derek Garcia
**/

public class Dijkstra {


    /**
     * Parses input file to create a hashmap graph of given values
     *
     * @param fileName name of input file
     * @return graph
     * @throws IOException filename is bad
     */
    private static p3Node[] parseFile(String fileName) throws IOException {
        // Create new Buffered Reader
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int numNodes = Integer.parseInt(br.readLine());  // get number of nodes

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

                int adjID = Integer.parseInt(adj.split(":")[0]);
                int weight = Integer.parseInt(adj.split(":")[1]);

                // Init adj node if needed
                if(graph[adjID] == null){
                    graph[adjID] =  new p3Node(adjID);
                }

                curNode.addEdge(graph[adjID], weight);
            }

            nodeID++;   // move to next node
            line = br.readLine();
        }

        br.close();
        return graph;
    }


    public static void main(String[] args) throws IOException {
        p3Node[] graph = parseFile(args[0]);

        for(p3Node node : graph){
            System.out.println(node);
        }

    }
}
