import java.util.*;

public class Graph {

    class Edge implements Comparable<Edge>{
        private char to;
        private int weight;

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.getWeight(), edge.getWeight());
        }

        public Edge (char to, int weight) {
            this.to = to;
            this.weight= weight;
        }

        public char getTo() {
            return this.to;
        }

        public int getWeight() {
            return this.weight;
        }
    }

    private Map<Character, List<Edge>> adjList;
 
    public Graph(){
        this.adjList = new HashMap<>();
    }
    
    public void addVertex(char vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(char from, char to, int weight) {
        addVertex(from);
        addVertex(to);
        adjList.get(from).add(new Edge(to,weight));
        adjList.get(to).add(new Edge(from,weight));
    }

    public void printGraph() {
        for (Map.Entry<Character, List<Edge>> e : adjList.entrySet()) {
            char vertex = e.getKey();
            List<Edge> neighbors = e.getValue();
            System.out.print(vertex + " --> ");
            for (Edge edge : neighbors) {
                System.out.print("(" + edge.getTo() + ", w: " + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    public List<Edge> mstPrim(char start) {
        int[] keys = new int[adjList.size()];
        char[] parents = new char[adjList.size()];
        boolean[] checked = new boolean[adjList.size()];
        List<Edge> mst = new ArrayList<>();

        Arrays.fill(keys, Integer.MAX_VALUE);
        Arrays.fill(parents,'@');
        Arrays.fill(checked, false);

        PriorityQueue<Edge> Q = new PriorityQueue<>(Comparator.comparingInt(e -> e.getWeight()));
        keys[start - 97] = 0;
        Q.add(new Edge(start,0));

        while(!Q.isEmpty()) {
            Edge curr = Q.poll();
            char currentVert = curr.getTo();
            if (checked[currentVert - 97]) {
                continue;
            }
            
            checked[currentVert - 97] = true;

            if (parents[currentVert - 97] != '@') {
                mst.add(curr);
            }

            for (Edge edge : adjList.get(currentVert)) {
                char key = edge.getTo();
                int weight = edge.getWeight();
                if (!checked[key - 97] && weight < keys[key - 97]) {
                    keys[key - 97] = weight;
                    parents[key - 97] = currentVert;
                    Q.add(new Edge(key,keys[key - 97]));
                }
            }
        }
        return mst;
    }

    public void printMST(List<Edge> mst) {
        for (Edge e : mst) {
            System.out.println(e.getTo() + ", weight: " + e.getWeight());
        }
    }

    public static void main (String[] args) {
        Graph g1 = new Graph();
        g1.addEdge('a', 'b', 4);
        g1.addEdge('a', 'h', 8);
        g1.addEdge('b', 'h', 11);
        g1.addEdge('h', 'i', 7);
        g1.addEdge('h', 'g', 1);
        g1.addEdge('b', 'c', 8);
        g1.addEdge('c', 'i', 2);
        g1.addEdge('c', 'f', 4);
        g1.addEdge('g', 'f', 2);
        g1.addEdge('i', 'g', 6);
        g1.addEdge('c', 'd', 7);
        g1.addEdge('d', 'f', 14);
        g1.addEdge('d', 'e', 9);
        g1.addEdge('e', 'f', 10);
        //g1.printGraph();
        g1.printMST(g1.mstPrim('e'));

    }
}
