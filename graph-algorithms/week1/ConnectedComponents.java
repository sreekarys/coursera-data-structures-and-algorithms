import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectedComponents {

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
	    adj.get(y - 1).add(x - 1);
	}
	System.out.println(getNumConnectedComponents(adj));
	scanner.close();
    }

    private static int getNumConnectedComponents(List<List<Integer>> adj) {
	int numConnectedComponents = 0;
	boolean[] visited = new boolean[adj.size()];
	for (int i = 0; i < adj.size(); i++) {
	    if (!visited[i]) {
		numConnectedComponents++;
		explore(i, adj, visited);
	    }
	}
	return numConnectedComponents;
    }

    private static void explore(int x, List<List<Integer>> adj, boolean[] visited) {
	if (visited[x])
	    return;
	visited[x] = true;
	for (Integer neighbourX : adj.get(x)) {
	    if (!visited[neighbourX])
		explore(neighbourX, adj, visited);
	}
    }
}
