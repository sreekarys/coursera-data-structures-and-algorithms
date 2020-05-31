import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
	    if (relax(distance, prev, edges) == Integer.MAX_VALUE)
		return;
	}

	int updateOnLastIteration = relax(distance, prev, edges);
	if (updateOnLastIteration == Integer.MAX_VALUE)
	    return;

	int current = updateOnLastIteration;
	Set<Integer> negativeCycle = new HashSet<>();
	do {
	    negativeCycle.add(current);
	    current = prev[current];
	} while (current != updateOnLastIteration);
	negativeCycle.stream().forEach(i -> distance[i] = Integer.MIN_VALUE);
    }

    private static int relax(int[] distance, int[] prev, List<Edge> edges) {
	int updatedOnLastIteration = Integer.MAX_VALUE;
	for (Edge edge : edges) {
	    if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.weight) {
		updatedOnLastIteration = edge.end;
		distance[edge.end] = distance[edge.start] + edge.weight;
		prev[edge.end] = edge.start;
	    }
	}
	return updatedOnLastIteration;
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
