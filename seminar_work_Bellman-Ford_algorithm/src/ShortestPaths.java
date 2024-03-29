import java.util.Arrays;
import java.util.List;

public class ShortestPaths {
    public int[] distances;
    public List<List<Integer>> paths;

    public ShortestPaths(int iterations, long time, boolean negativeCycleInGraph) {
        this.iterations = iterations;
        this.time = time;
        this.negativeCycleInGraph = negativeCycleInGraph;
    }
    public boolean graphIsConnected;

    public boolean isNegativeCycleInGraph() {
        return negativeCycleInGraph;
    }

    private boolean negativeCycleInGraph;

    private int iterations;

    public int getIterations() {
        return iterations;
    }

    public long getTime() {
        return time;
    }

    private long time;

    public ShortestPaths(int[] distances, List<List<Integer>> paths, int iterations, long time) {
        this.distances = distances;
        this.paths = paths;
        this.iterations = iterations;
        this.time = time;
    }

    public ShortestPaths(int[] distances, List<List<Integer>> paths) {
        this.distances = distances;
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "ShortestPaths{" +
                "distances=" + Arrays.toString(distances) +
                ", paths=" + paths +
                '}';
    }
}
