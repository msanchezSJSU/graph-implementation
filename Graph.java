public class Graph {
    private boolean[][] adjMatrix;
    
    private int vertCount;
    
    public Graph(int vertCount){
        this.vertCount = vertCount;
        this.adjMatrix = new boolean[vertCount][vertCount];
    }
    
    public void addEdge(int x, int y) {
        adjMatrix[x][y] = true;
        adjMatrix[y][x] = true;
    }

    public void deleteEdge(int x, int y) {
        adjMatrix[x][y] = false;
        adjMatrix[y][x] = false;
    }

    public boolean checkEdge(int x, int y) {
        return adjMatrix[x][y];
    }
}
