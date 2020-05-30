import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPathUndirectedGraph {
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
	System.out.println(findDistance(x, y, adj));
	scanner.close();
    }

    private static int findDistance(int source, int destination, List<List<Integer>> adj) {
	int V = adj.size();
	int distance[] = new int[V];
	Arrays.fill(distance, Integer.MAX_VALUE);
	Queue<Integer> queue = new LinkedList<>();
	distance[source] = 0;
	queue.add(source);
	while (!queue.isEmpty()) {
	    int x = queue.poll();
	    for (Integer neighbourX : adj.get(x)) {
		if (distance[neighbourX] == Integer.MAX_VALUE) {
		    distance[neighbourX] = distance[x] + 1;
		    queue.add(neighbourX);
		}
	    }
	}
	return distance[destination] == Integer.MAX_VALUE ? -1 : distance[destination];
    }

}
