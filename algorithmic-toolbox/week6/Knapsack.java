import java.util.Scanner;

/*
 * O(NW) time: pseudo polynomial
 * O(NW) extra space
 * value(w,n) = max(value(w,n-1), vn + value(w-wn, n-1))
 */
public class Knapsack {
    static int optimalWeight(int W, int[] weights) {
	int N = weights.length;
	int value[][] = new int[N + 1][W + 1];
	for (int i = 0; i <= N; i++) {
	    value[i][0] = 0;
	}
	for (int i = 0; i <= W; i++) {
	    value[0][i] = 0;
	}

	for (int w = 1; w <= W; w++) {
	    for (int n = 1; n <= N; n++) {
		int val1 = value[n - 1][w], val2 = Integer.MIN_VALUE;
		if (weights[n - 1] <= w) {
		    val2 = weights[n - 1] + value[n - 1][w - weights[n - 1]];
		}
		value[n][w] = Math.max(val1, val2);
	    }
	}
	return value[N][W];
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int W, n;
	W = scanner.nextInt();
	n = scanner.nextInt();
	int[] w = new int[n];
	for (int i = 0; i < n; i++) {
	    w[i] = scanner.nextInt();
	}
	System.out.println(optimalWeight(W, w));
	scanner.close();
    }
}
