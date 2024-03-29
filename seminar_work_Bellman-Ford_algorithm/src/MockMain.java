import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MockMain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("mock_data/mock_graph_1.txt"));
        int numVertices = scanner.nextInt();
        int numEdges = scanner.nextInt();
        System.out.println(numVertices + " " + numEdges + " ");
        Graph graph = new Graph(numVertices, numEdges);
        for (int j = 0; j < numEdges; j++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdges(source, destination, weight);
        }
        int sourceVertex = 1;
        BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm();
        ShortestPaths shortestPaths = bellmanFordAlgorithm.bellmanFordAlgorithm(sourceVertex, graph);
        int iterations = shortestPaths.getIterations();
        long time = shortestPaths.getTime();
        System.out.println(shortestPaths);
        System.out.println(iterations + " " + time);
    }
}
