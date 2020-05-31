import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DetectNegativeCycles {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int V = scanner.nextInt();
	int E = scanner.nextInt();
	List<Edge> edges = new ArrayList<>();
	for (int i = 0; i < E; i++) {
	    int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
	    edges.add(new Edge(start - 1, end - 1, weight));
	}
	scanner.close();

	// This for loop is necessary to take care of disconnected components,
	// as some part of the graph with a negative cycle might not be reachable
	// from an arbitrary source
	int[] distance = new int[V];
	Arrays.fill(distance, Integer.MAX_VALUE);
	for (int i = 0; i < V; i++) {
	    if (distance[i] == Integer.MAX_VALUE) {
		distance[i] = 0;
		if (detectNegativeCycles(i, distance, edges) == 1) {
		    System.out.println("1");
		    return;
		}

	    }
	}
	System.out.println("0");
    }

    private static int detectNegativeCycles(int source, int[] distance, List<Edge> edges) {
	int V = distance.length;
	for (int i = 0; i < V; i++) {
	    if (relax(distance, edges) == 0)
		return 0;
	}
	return 1;
    }

    private static int relax(int[] distance, List<Edge> edges) {
	int numberOfRelaxations = 0;
	for (Edge edge : edges) {
	    if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.weight) {
		distance[edge.end] = distance[edge.start] + edge.weight;
		numberOfRelaxations++;
	    }
	}
	return numberOfRelaxations;
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
