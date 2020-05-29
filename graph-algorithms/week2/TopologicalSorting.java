import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TopologicalSorting {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();
	List<List<Integer>> adj = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    adj.add(new ArrayList<>());
	}

	for (int i = 0; i < m; i++) {
	    int x, y;
	    x = scanner.nextInt();
	    y = scanner.nextInt();
	    adj.get(x - 1).add(y - 1);
	}
	topologicalSort(adj);
	scanner.close();
    }

    private static void topologicalSort(List<List<Integer>> adj) {
	int V = adj.size();
	boolean[] visited = new boolean[V];
	List<Integer> postOrder = new ArrayList<>(V);
	for (int i = 0; i < V; i++) {
	    if (!visited[i])
		explore(i, adj, postOrder, visited);
	}

	for (int i = V - 1; i >= 0; i--) {
	    System.out.print(postOrder.get(i) + 1 + " ");
	}
	System.out.println();
    }

    private static void explore(int x, List<List<Integer>> adj, List<Integer> postOrder, boolean[] visited) {
	if (visited[x])
	    return;
	visited[x] = true;
	for (Integer neightbourX : adj.get(x)) {
	    if (!visited[neightbourX])
		explore(neightbourX, adj, postOrder, visited);
	}
	postOrder.add(x);
    }
}
