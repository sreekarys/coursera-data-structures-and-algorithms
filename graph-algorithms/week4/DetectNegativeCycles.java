import java.util.Arrays;
import java.util.Scanner;

public class DetectNegativeCycles {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();
	int[][] weights = new int[n][n];
	for (int i = 0; i < n; i++) {
	    Arrays.fill(weights[i], Integer.MAX_VALUE);
	}
	for (int i = 0; i < m; i++) {
	    int start = scanner.nextInt(), end = scanner.nextInt(), weight = scanner.nextInt();
	    weights[start - 1][end - 1] = weight;
	}
	System.out.println(detectNegativeCycles(0, weights));
	scanner.close();
    }

    private static int detectNegativeCycles(int source, int[][] weights) {
	int V = weights.length;
	int[] distance = new int[V];
	Arrays.fill(distance, Integer.MAX_VALUE);
	distance[source] = 0;
	for (int i = 0; i < V; i++) {
	    int numberOfRelaxations = 0;
	    for (int j = 0; j < V; j++) {
		for (int k = 0; k < V; k++) {
		    if (weights[j][k] != Integer.MAX_VALUE && distance[j] != Integer.MAX_VALUE
			    && distance[k] > distance[j] + weights[j][k]) {
			distance[k] = distance[j] + weights[j][k];
			numberOfRelaxations++;
		    }
		}
	    }
	    if (numberOfRelaxations == 0)
		return 0;
	}
	return 1;
    }

}
