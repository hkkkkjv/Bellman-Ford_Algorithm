import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int V;
    private int E;
    private final List<Edge> edges;

    public Graph(int v, int e) {
        V = v;
        E = e;
        this.edges = new ArrayList<>();
    }

    public void addEdges(int source, int destination, int weght) {
        edges.add(new Edge(source, destination, weght));
    }

    public int getV() {
        return V;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
