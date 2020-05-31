import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConnectingCities {

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int V = in.nextInt();
	List<Point> points = new ArrayList<>();
	for (int i = 0; i < V; i++) {
	    points.add(new Point(in.nextInt(), in.nextInt()));
	}

	List<Edge> edges = new ArrayList<>();
	for (int i = 0; i < V; i++) {
	    for (int j = i; j < V; j++) {
		edges.add(new Edge(i, j, getDistance(points.get(i), points.get(j))));
	    }
	}

	System.out.println(minimumSpanningTreeKruskalAlgorithm(V, edges));
	in.close();
    }

    private static double minimumSpanningTreeKruskalAlgorithm(int V, List<Edge> edges) {
	Collections.sort(edges, Comparator.comparing(e -> e.distance));
	DisjointSets djs = new DisjointSets(V);
	int E = V - 1, size = 0;
	double mstCost = 0;
	for (Edge edge : edges) {
	    if (size == E)
		break;
	    if (djs.find(edge.start) == djs.find(edge.end))
		continue;

	    djs.union(edge.start, edge.end);
	    mstCost += edge.distance;
	    size++;
	}
	return mstCost;
    }

    private static double getDistance(Point p1, Point p2) {
	return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }

    static class Point {
	int x, y;

	public Point(int x, int y) {
	    this.x = x;
	    this.y = y;
	}
    }

    static class Edge {
	int start, end;
	double distance;

	public Edge(int start, int end, double distance) {
	    this.distance = distance;
	    this.start = start;
	    this.end = end;
	}
    }

    static class DisjointSets {
	int size;
	int[] parent, rank;

	public DisjointSets(int size) {
	    this.size = size;
	    parent = new int[size];
	    rank = new int[size];
	    for (int i = 0; i < size; i++) {
		parent[i] = i;
	    }
	    Arrays.fill(rank, 0);
	}

	public int find(int x) {
	    if (x != parent[x]) {
		parent[x] = find(parent[x]);
	    }
	    return parent[x];
	}

	public void union(int x, int y) {
	    int parentX = find(x), parentY = find(y);
	    if (parentX == parentY)
		return;
	    if (rank[parentX] >= rank[parentY]) {
		parent[parentY] = parentX;
		if (rank[parentX] == rank[parentY])
		    rank[parentX]++;
	    } else
		parent[parentX] = parentY;
	}
    }
}
