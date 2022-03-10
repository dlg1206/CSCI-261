import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * @author Derek Garcia
 **/



public class BFS_DFS {

    private static HashMap<String, HashSet<String>> parseFile(String fileName) throws IOException {
        HashMap<String, HashSet<String>> graph = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line = br.readLine();
        do{
            if(!line.contains("#")){
                String[] nodes = line.split(" ");
                if(!graph.containsKey(nodes[0])){
                    graph.put(nodes[0], new HashSet<>());
                }

                for(int i = 1; i < nodes.length; i++){
                    graph.get(nodes[0]).add(nodes[i]);
                }


            }
            line = br.readLine();
        } while(line != null);
        br.close();
        return graph;
    }

    private static void doBFS(HashMap<String, HashSet<String>> graph, String root){

        ArrayList<String> queue = new ArrayList<>();
        queue.add(root);

        ArrayList<String> visited = new ArrayList<>();

        while (!queue.isEmpty()){
            String curNode = queue.remove(0);
            visited.add(curNode);

            for(String adj : graph.get(curNode)){
                if(!visited.contains(adj) && !queue.contains(adj)){
                    queue.add(adj);
                }
            }

        }

        for(String node : visited){

            System.out.print(node);

            if(visited.indexOf(node) != visited.size() - 1){
                System.out.print(" -> ");
            }
        }


    }

    private static void doDFS(HashMap<String, HashSet<String>> graph, String root){

        Stack<String> stack = new Stack<>();
        stack.push(root);

        ArrayList<String> visited = new ArrayList<>();

        while (!stack.isEmpty()){
            String curNode = stack.pop();
            visited.add(curNode);

            for(String adj : graph.get(curNode)){
                if(!visited.contains(adj) && !stack.contains(adj)){
                    stack.push(adj);
                }
            }

        }

        for(String node : visited){

            System.out.print(node);

            if(visited.indexOf(node) != visited.size() - 1){
                System.out.print(" -> ");
            }
        }


    }

    public static void main(String[] args) throws IOException {
        HashMap<String, HashSet<String>> graph = parseFile(args[0]);

        //doBFS(graph, "B");
        doDFS(graph, "0");



    }
}
