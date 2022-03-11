import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**


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


    private static HashMap<Integer, ArrayList<p3Node>> doDijkstra(p3Node[] graph, int source){
        LinkedList<p3Node> queue = new LinkedList<>();

        HashMap<Integer, ArrayList<p3Node>> result = new HashMap<>();


        p3Node curNode = graph[source];
        curNode.setDistance(0);

        result.put(0, new ArrayList<>());
        result.get(0).add(curNode);

        for( ;; ){


            for(p3Node adj : curNode.getEdges().keySet()){



                if(adj.getDistance() < 0 && !queue.contains(adj)){
                    queue.add(adj);


                }

                int newDist = curNode.getDistance() + curNode.getEdgeWeight(adj);

                if(adj.getDistance() < 0 || adj.getDistance() > newDist){

                    if(!result.containsKey(newDist)){

                        result.put(newDist, new ArrayList<>());
                    }

                    if(adj.getDistance() >= 0){

                        result.get(adj.getDistance()).remove(adj);
                    }



                    adj.setDistance(newDist);

                    result.get(newDist).add(adj);
                    adj.setPath(curNode.getId());

                }
            }

            if(queue.isEmpty()){
                break;
            } else {
                curNode = queue.pop();
            }
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        p3Node[] graph = parseFile(args[0]);


        HashMap<Integer, ArrayList<p3Node>> result;
        if(args.length == 2){
            result = doDijkstra(graph, Integer.parseInt(args[1]));
        } else {
            result = doDijkstra(graph, 1);
        }

        ArrayList<Integer> distances = new ArrayList<>(result.keySet());
        Collections.sort(distances);
        for(int dist : distances){

            for(p3Node node : result.get(dist)){
                System.out.println(node);
            }

        }

    }
}
