import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class OptimalMoneyExchange {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int V = in.nextInt(), E = in.nextInt();
	List<Edge> edges = new ArrayList<>();
	for (int i = 0; i < E; i++) {
	    edges.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
	}
	int source = in.nextInt() - 1;
	int distance[] = new int[V];
	Arrays.fill(distance, Integer.MAX_VALUE);
	distance[source] = 0;
	findMinimumWeightPath(distance, edges);
	for (int i = 0; i < V; i++) {
	    System.out.println(
		    distance[i] == Integer.MIN_VALUE ? "-" : (distance[i] == Integer.MAX_VALUE ? "*" : distance[i]));
	}

	in.close();
    }

    private static void findMinimumWeightPath(int[] distance, List<Edge> edges) {
	int V = distance.length;
	int[] prev = new int[V];
	Arrays.fill(prev, Integer.MAX_VALUE);
	for (int i = 0; i < V - 1; i++) {
	    if (relax(distance, prev, edges).isEmpty())
		return;
	}

	Set<Integer> verticesUpdatedOnLastIteration = relax(distance, prev, edges);
	if (verticesUpdatedOnLastIteration.isEmpty())
	    return;

	Set<Integer> verticesOfNegativeCycle = new HashSet<>();
	verticesUpdatedOnLastIteration.forEach(vertexUpdatedOnLastIteration -> verticesOfNegativeCycle
		.add(getVertexOfNegativeCycle(vertexUpdatedOnLastIteration, V, prev)));

	Set<Integer> allNegativeCycles = new HashSet<>();
	verticesOfNegativeCycle.forEach(
		vertexOfNegativeCycle -> allNegativeCycles.addAll(getNegativeCycle(vertexOfNegativeCycle, prev)));
	Set<Integer> reachableFromNegativeCycle = getReachableFromNegativeCycle(allNegativeCycles, edges);
	reachableFromNegativeCycle.stream().forEach(i -> distance[i] = Integer.MIN_VALUE);
    }

    private static Set<Integer> relax(int[] distance, int[] prev, List<Edge> edges) {
	Set<Integer> updatedVertices = new HashSet<>();
	for (Edge edge : edges) {
	    if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.weight) {
		distance[edge.end] = distance[edge.start] + edge.weight;
		prev[edge.end] = edge.start;
		updatedVertices.add(edge.end);
	    }
	}
	return updatedVertices;
    }

    private static int getVertexOfNegativeCycle(int vertexReachableFromNegativeCycle, int V, int[] prev) {
	int index = 0;
	while (index < V) {
	    vertexReachableFromNegativeCycle = prev[vertexReachableFromNegativeCycle];
	    index++;
	}
	return vertexReachableFromNegativeCycle;
    }

    private static Set<Integer> getNegativeCycle(int vertexOfNegativeCycle, int[] prev) {
	int current = vertexOfNegativeCycle;
	Set<Integer> negativeCycle = new HashSet<>();
	do {
	    negativeCycle.add(current);
	    current = prev[current];
	} while (current != vertexOfNegativeCycle);
	return negativeCycle;
    }

    private static Set<Integer> getReachableFromNegativeCycle(Set<Integer> negativeCycle, List<Edge> edges) {
	Set<Integer> reachableFromNegativeCycle = new HashSet<>();
	Queue<Integer> q = new LinkedList<>();
	negativeCycle.stream().forEach(v -> q.add(v));
	while (!q.isEmpty()) {
	    int vertex = q.poll();
	    reachableFromNegativeCycle.add(vertex);
	    for (Edge edge : edges) {
		if (edge.start == vertex && !reachableFromNegativeCycle.contains(edge.end))
		    q.add(edge.end);
	    }
	}
	return reachableFromNegativeCycle;
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
