import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DetectNegativeCycles {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();
	List<Edge> edges = new ArrayList<>();
	for (int i = 0; i < m; i++) {
	    int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
	    edges.add(new Edge(start - 1, end - 1, weight));
	}
	System.out.println(detectNegativeCycles(0, n, edges));
	scanner.close();
    }

    private static int detectNegativeCycles(int source, int V, List<Edge> edges) {
	int[] distance = new int[V];
	Arrays.fill(distance, Integer.MAX_VALUE);
	distance[source] = 0;
	for (int i = 0; i < V; i++) {
	    int numberOfRelaxations = 0;
	    for (Edge edge : edges) {
		if (distance[edge.start] != Integer.MAX_VALUE
			&& distance[edge.end] > distance[edge.start] + edge.weight) {
		    distance[edge.end] = distance[edge.start] + edge.weight;
		    numberOfRelaxations++;
		}
	    }
	    if (numberOfRelaxations == 0)
		return 0;
	}
	return 1;
    }

    static class Edge {
	int start, end, weight;

	public Edge(int start, int end, int weight) {
	    this.start = start;
	    this.end = end;
	    this.weight = weight;
	}
    }

}
