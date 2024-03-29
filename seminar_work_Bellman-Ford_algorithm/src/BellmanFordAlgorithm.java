import java.util.*;

public class BellmanFordAlgorithm {
    public ShortestPaths bellmanFordAlgorithm(int source, Graph graph) {
        long time_Start = System.nanoTime();
        List<Edge> edges = graph.getEdges();
        int numVertices = graph.getV();
        int[] distances = new int[numVertices];
        int[] previous = new int[numVertices];
        //Шаг 1. Инициализируем исходную вершину путем 0, остальные вершины путь бесконечность(в нашем случае Integer.MAX_VALUE)
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        previous[source] = -1;
        int iterations = 0;
        //Шаг 2. Проходим по каждому ребру uv |v-1| раз, что обеспечит нам нахождение минимальных расстояний
        // (нам приходится делать (v-1) итерацию по всем ребрам, потому что наш алгоритм может обрабатывать отрицательные дуги)
        for (int i = 0; i < numVertices - 1; i++) {
            boolean flag = false;/*В случае не захода в другие вершины на уровне первой итерации, после которой все пути останутся бесконечностями ,
            переменная flag остается отрицательной и завершает бессмысленный цикл*/
            for (Edge edge : edges) {
                iterations++;
                if (distances[edge.getSource()] != Integer.MAX_VALUE && distances[edge.getSource()] + edge.getWeight() < distances[edge.getDestination()]) {
                    distances[edge.getDestination()] = distances[edge.getSource()] + edge.getWeight();
                    previous[edge.getDestination()] = edge.getSource();
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        boolean negativeCycleInGraph = false;
        //Шаг3. Поиск циклов отрицательного веса, после завершения основного алгоритма
        for (Edge edge : edges) {
            iterations++;
            if (distances[edge.getSource()] != Integer.MAX_VALUE && distances[edge.getSource()] + edge.getWeight() < distances[edge.getDestination()]) {
                distances[edge.getDestination()] = Integer.MIN_VALUE;
                negativeCycleInGraph = true;
                break;
                //Присваиваем вершине значение -infinity, потому что в нее приводит отрицательный цикл
            }
        }
        /*Идея шага 3 заключается в том,
        что шаг 2 гарантирует кратчайшее расстояние,
        если граф не содержит цикла отрицательного веса.
        Если мы снова переберем все ребра и
        получим более короткий путь для любой из вершин,
        это будет сигналом присутствия цикла отрицательного веса.*/

        //Шаг 4. Восстановление пути из начальной вершины в остальные
        /*я не использовала этот цикл на 100 наборах данных,
        потому что происходило переполнение heap и я не разобралась с этим :(((((
        Но это исправно работает на одном наборе
        List<List<Integer>> paths = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (distances[i] != Integer.MAX_VALUE && distances[i] != Integer.MIN_VALUE) {
                List<Integer> path = new ArrayList<>();
                int current = i;
                //Текущая вершина та, путь в которую мы ищем
                //восстанавливаем из текущей вершины в предыдущую пока не дойдем до начальной вершины
                //добавляя в список предыдущие, после чего сделаем реверс списка
                while (current != -1) {
                    path.add(current);
                    current = previous[current];
                }
                Collections.reverse(path);
                paths.add(path);
            }
        }*/
        long time_Finish = System.nanoTime();
        long time = time_Finish - time_Start;
        return new ShortestPaths(iterations, time, negativeCycleInGraph);
    }
}