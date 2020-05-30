import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BipartiteGraph {
    static enum COLOR {
	GREY, WHITE, BLACK;

	public COLOR complement() {
	    return this == WHITE ? BLACK : WHITE;
	}
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();
	List<Set<Integer>> adj = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    adj.add(new HashSet<>());
	}

	for (int i = 0; i < m; i++) {
	    int x, y;
	    x = scanner.nextInt();
	    y = scanner.nextInt();
	    adj.get(x - 1).add(y - 1);
	    adj.get(y - 1).add(x - 1);
	}
	System.out.println(isBipartite(adj));
	scanner.close();
    }

    private static int isBipartite(List<Set<Integer>> adj) {
	int V = adj.size();
	COLOR[] colorsOfVertices = new COLOR[V];
	Arrays.fill(colorsOfVertices, COLOR.GREY);

	for (int i = 0; i < V; i++) {
	    if (colorsOfVertices[i] == COLOR.GREY && isBipartite(adj, colorsOfVertices, i) == 0)
		return 0;
	}
	return 1;
    }

    private static int isBipartite(List<Set<Integer>> adj, COLOR[] colorsOfVertices, int source) {
	Queue<Integer> queue = new LinkedList<>();
	queue.add(source);
	colorsOfVertices[source] = COLOR.WHITE;
	while (!queue.isEmpty()) {
	    int x = queue.poll();
	    for (Integer neighbourX : adj.get(x)) {
		if (colorsOfVertices[neighbourX] == COLOR.GREY) {
		    colorsOfVertices[neighbourX] = colorsOfVertices[x].complement();
		    queue.add(neighbourX);
		}
	    }
	}

	for (int i = 0; i < adj.size(); i++) {
	    for (Integer neighbour : adj.get(i)) {
		if (colorsOfVertices[i] == colorsOfVertices[neighbour] && colorsOfVertices[i] != COLOR.GREY)
		    return 0;
	    }
	}
	return 1;
    }
}
