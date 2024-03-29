import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class OutputData {
    public void outputData(int numSets) {
        File results = new File("results/results1/results11");
        try (PrintWriter printWriter = new PrintWriter(results)) {
            for (int i = 0; i < numSets; i++) {
                File file = new File("data/data1/input_data_" + i + ".txt");
                try (Scanner scanner = new Scanner(file);) {
                    int numVertices = scanner.nextInt();
                    int numEdges = scanner.nextInt();
                    printWriter.print(numVertices + " " + numEdges + " ");
                    Graph graph = new Graph(numVertices, numEdges);
                    for (int j = 0; j < numEdges; j++) {
                        int source = scanner.nextInt();
                        int destination = scanner.nextInt();
                        int weight = scanner.nextInt();
                        graph.addEdges(source, destination, weight);
                    }
                    int sourceVertex = 0;
                    BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm();
                    ShortestPaths shortestPaths = bellmanFordAlgorithm.bellmanFordAlgorithm(sourceVertex, graph);
                    int iterations = shortestPaths.getIterations();
                    long time = shortestPaths.getTime();
                    /*boolean negativeCycleInGraph = shortestPaths.isNegativeCycleInGraph();*/
                    printWriter.println(iterations + " " + time /*+ " " + negativeCycleInGraph*/);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}