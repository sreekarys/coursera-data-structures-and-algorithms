import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reachability {
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
	int x = scanner.nextInt() - 1;
	int y = scanner.nextInt() - 1;
	System.out.println(reach(adj, x, y));
	scanner.close();
    }

    private static int reach(List<List<Integer>> adj, int x, int y) {
	boolean[] visited = new boolean[adj.size()];
	explore(x, adj, visited);
	return visited[y] ? 1 : 0;
    }

    private static void explore(int x, List<List<Integer>> adj, boolean[] visited) {
	if (visited[x])
	    return;
	visited[x] = true;
	List<Integer> neighboursX = adj.get(x);
	for (Integer neighbourX : neighboursX) {
	    if (!visited[neighbourX])
		explore(neighbourX, adj, visited);
	}
    }

}
