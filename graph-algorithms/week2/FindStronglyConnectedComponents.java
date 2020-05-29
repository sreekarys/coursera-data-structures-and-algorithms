import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FindStronglyConnectedComponents {
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
	List<Integer> postOrderReverseGraph = new ArrayList<>(V), postOrder = new ArrayList<>(V);
	int[] sccReverseGraph = new int[V], scc = new int[V];
	Arrays.fill(sccReverseGraph, -1);
	Arrays.fill(scc, -1);

	int stronglyConnectedComponents = 0;
	for (int i = 0; i < V; i++) {
	    if (sccReverseGraph[i] == -1)
		explore(i, reverseAdjacencyList, postOrderReverseGraph, sccReverseGraph, ++stronglyConnectedComponents);
	}

	stronglyConnectedComponents = 0;
	for (int i = V - 1; i >= 0; i--) {
	    int sink = postOrderReverseGraph.get(i);
	    if (scc[sink] == -1)
		explore(sink, adjacencyList, postOrder, scc, ++stronglyConnectedComponents);
	}
	for (int i = 1; i <= stronglyConnectedComponents; i++) {
	    for (int j = 0; j < V; j++) {
		if (scc[j] == i)
		    System.out.print((j + 1) + " ");
	    }
	    System.out.println();
	}
	return stronglyConnectedComponents;
    }

    private static void explore(int x, List<List<Integer>> adjacencyList, List<Integer> postOrder, int[] scc,
	    int currentScc) {
	if (scc[x] != -1)
	    return;
	scc[x] = currentScc;
	for (Integer neighbourX : adjacencyList.get(x)) {
	    if (scc[neighbourX] == -1)
		explore(neighbourX, adjacencyList, postOrder, scc, currentScc);
	}
	postOrder.add(x);
    }
}
