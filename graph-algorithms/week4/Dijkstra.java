import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Dijkstra {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();

	int[][] weights = new int[n][n];
	for (int i = 0; i < m; i++) {
	    int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
	    weights[start - 1][end - 1] = weight;
	}
	int x = scanner.nextInt() - 1;
	int y = scanner.nextInt() - 1;
	System.out.println(runDijkstra(n, m, x, y, weights));
	scanner.close();
    }

    private static int runDijkstra(int V, int E, int source, int destination, int[][] weights) {
	int distance[] = new int[V];
	Arrays.fill(distance, Integer.MAX_VALUE);
	distance[source] = 0;
	if (isDense(V, E))
	    runDijkstraUsingArray(source, distance, weights);
	else
	    runDijkstraUsingPriorityQueue(source, distance, weights);
	return distance[destination] == Integer.MAX_VALUE ? -1 : distance[destination];
    }

    private static void runDijkstraUsingPriorityQueue(int source, int[] distance, int[][] weights) {
	int V = distance.length;
	PriorityQueue<Vertex> unKnownRegion = new PriorityQueue<>(Comparator.comparing(v -> v.distance));
	for (int i = 0; i < V; i++) {
	    unKnownRegion.add(new Vertex(i, i == source ? 0 : Integer.MAX_VALUE));
	}

	while (!unKnownRegion.isEmpty()) {
	    Vertex vertex = unKnownRegion.poll();
	    if (vertex.distance == Integer.MAX_VALUE)
		return;

	    int v = vertex.index;
	    for (int i = 0; i < V; i++) {
		if (weights[v][i] > 0 && distance[i] > distance[v] + weights[v][i]) {
		    distance[i] = distance[v] + weights[v][i];
		    Vertex neighbour = new Vertex(i, distance[i]);
		    unKnownRegion.remove(neighbour);
		    unKnownRegion.add(neighbour);
		}
	    }
	}
    }

    private static void runDijkstraUsingArray(int source, int[] distance, int[][] weights) {
	int V = distance.length;
	Set<Integer> knownRegion = new HashSet<>();
	for (int i = 0; i < V - 1; i++) {
	    int min = Integer.MAX_VALUE, minIndex = -1;
	    for (int j = 0; j < V; j++) {
		if (!knownRegion.contains(j) && min > distance[j]) {
		    min = distance[j];
		    minIndex = j;
		}
	    }

	    if (min == Integer.MAX_VALUE)
		return;

	    knownRegion.add(minIndex);
	    for (int neighbour = 0; neighbour < V; neighbour++) {
		if (weights[minIndex][neighbour] > 0
			&& distance[neighbour] > distance[minIndex] + weights[minIndex][neighbour]) {
		    distance[neighbour] = distance[minIndex] + weights[minIndex][neighbour];
		}
	    }
	}

    }

    private static boolean isDense(int V, int E) {
	return ((V * (V - 1)) - E) < (E - V);
    }

    static class Vertex {
	int index, distance;

	public Vertex(int index, int distance) {
	    this.index = index;
	    this.distance = distance;
	}

	@Override
	public boolean equals(Object obj) {
	    Vertex another = (Vertex) obj;
	    return another.index == this.index;
	}
    }

}
