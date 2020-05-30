import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class DijkstraUsingPriorityQueue {

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();
	List<Set<Integer>> adjacencyList = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    adjacencyList.add(new HashSet<>());
	}

	int[][] weights = new int[n][n];
	for (int i = 0; i < m; i++) {
	    int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
	    adjacencyList.get(start - 1).add(end - 1);
	    weights[start - 1][end - 1] = weight;
	}
	int x = scanner.nextInt() - 1;
	int y = scanner.nextInt() - 1;
	System.out.println(findShortestPath(x, y, adjacencyList, weights));
	scanner.close();
    }

    private static int findShortestPath(int source, int destination, List<Set<Integer>> adjacencyList,
	    int[][] weights) {
	int V = adjacencyList.size();
	int[] distance = new int[V];
	Arrays.fill(distance, Integer.MAX_VALUE);
	distance[source] = 0;
	PriorityQueue<Vertex> unKnownRegion = new PriorityQueue<>(Comparator.comparing(Vertex::getDistance));
	for (int i = 0; i < V; i++) {
	    unKnownRegion.add(new Vertex(i, i == source ? 0 : Integer.MAX_VALUE));
	}

	while (!unKnownRegion.isEmpty()) {
	    Vertex optimalVertex = unKnownRegion.poll();
	    int v = optimalVertex.getIndex();
	    distance[v] = optimalVertex.getDistance();
	    if (distance[v] == Integer.MAX_VALUE)
		break;
	    Set<Integer> neighboursV = adjacencyList.get(v);
	    for (Integer neighbourV : neighboursV) {
		if (distance[neighbourV] > distance[v] + weights[v][neighbourV]) {
		    distance[neighbourV] = distance[v] + weights[v][neighbourV];
		    Vertex neighbourVertex = new Vertex(neighbourV, distance[neighbourV]);
		    unKnownRegion.remove(neighbourVertex);
		    unKnownRegion.add(neighbourVertex);
		}
	    }
	}
	return distance[destination] == Integer.MAX_VALUE ? -1 : distance[destination];
    }

    static class Vertex {
	private int index, distance;

	public Vertex(int index, int distance) {
	    this.index = index;
	    this.distance = distance;
	}

	public int getIndex() {
	    return index;
	}

	public void setIndex(int index) {
	    this.index = index;
	}

	public int getDistance() {
	    return distance;
	}

	public void setDistance(int distance) {
	    this.distance = distance;
	}

	@Override
	public boolean equals(Object obj) {
	    Vertex another = (Vertex) obj;
	    return another.index == this.index;
	}
    }
}
