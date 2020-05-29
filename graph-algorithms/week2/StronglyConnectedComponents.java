import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StronglyConnectedComponents {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();
	List<List<Integer>> adjacencyList = new ArrayList<>(), reverseAdjacencyList = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    adjacencyList.add(new ArrayList<>());
	    reverseAdjacencyList.add(new ArrayList<>());
	}

	for (int i = 0; i < m; i++) {
	    int x, y;
	    x = scanner.nextInt();
	    y = scanner.nextInt();
	    adjacencyList.get(x - 1).add(y - 1);
	    reverseAdjacencyList.get(y - 1).add(x - 1);
	}
	System.out.println(calculateStronglyConnectedComponents(adjacencyList, reverseAdjacencyList));
	scanner.close();
    }

    private static int calculateStronglyConnectedComponents(List<List<Integer>> adjacencyList,
	    List<List<Integer>> reverseAdjacencyList) {
	int V = adjacencyList.size();
	boolean[] visitedReverseGraph = new boolean[V], visited = new boolean[V];
	List<Integer> postOrderReverseGraph = new ArrayList<>(), postOrder = new ArrayList<>();

	for (int i = 0; i < V; i++) {
	    if (!visitedReverseGraph[i])
		explore(i, reverseAdjacencyList, postOrderReverseGraph, visitedReverseGraph);
	}

	int stronglyConnectedComponents = 0;
	for (int i = V - 1; i >= 0; i--) {
	    int sink = postOrderReverseGraph.get(i);
	    if (!visited[sink]) {
		stronglyConnectedComponents++;
		explore(sink, adjacencyList, postOrder, visited);
	    }
	}
	return stronglyConnectedComponents;
    }

    private static void explore(int x, List<List<Integer>> adjacencyList, List<Integer> postOrder, boolean[] visited) {
	if (visited[x])
	    return;
	visited[x] = true;
	for (Integer neighbourX : adjacencyList.get(x)) {
	    if (!visited[neighbourX])
		explore(neighbourX, adjacencyList, postOrder, visited);
	}
	postOrder.add(x);

    }
}
