import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class InputDataGenerator {
    public void generateInputData(int numSets, int minSize, int maxSize) {
        Random random = new Random();
        File folder = new File("data/data1");
        if (!folder.exists()) {
            folder.mkdir();
        }
        for (int i = 0; i < numSets; i++) {
            int edges = random.nextInt(maxSize - minSize + 1) + minSize;
            int vertices = random.nextInt(edges) + minSize;
            boolean[][] edgesInGraph = new boolean[vertices][vertices];
            for (int source = 0; source < vertices; source++) {
                for (int destination = 0; destination < vertices; destination++) {
                    edgesInGraph[source][destination] = (source == destination);
                }
            }
            String filename = "data/data1/input_data_" + i + ".txt";
            File file = new File(filename);
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println(vertices + " " + edges);
                for (int j = 0; j < edges; j++) {
                    int source = random.nextInt(vertices);
                    int destination = random.nextInt(vertices);
                    int weight = random.nextInt(100) - 50;
                    if (weight != 0 & !edgesInGraph[source][destination]) {
                        writer.println(source + " " + destination + " " + weight);
                        edgesInGraph[source][destination] = true;
                    } else {
                        j--;
                    }
                }
                boolean[] visited = new boolean[vertices];
                Queue<Integer> queue = new ArrayDeque<>();
                queue.add(0);
                visited[0] = true;
                while (!queue.isEmpty()) {
                    int currentVertex = queue.poll();
                    for (int neighbor = 0; neighbor < vertices; neighbor++) {
                        if (edgesInGraph[currentVertex][neighbor] && !visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
                boolean isConnected = true;
                for (boolean v : visited) {
                    if (!v) {
                        isConnected = false;
                        break;
                    }
                }
                if (!isConnected){
                    i--;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
