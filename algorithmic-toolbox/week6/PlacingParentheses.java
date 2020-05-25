import java.util.Scanner;

/*
 * O(n^3) time
 * O(n^2) extra space
 */
public class PlacingParentheses {
    private static int[] getMaxMin(int[][] M, int[][] m, char[] operators, int i, int j) {
	int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	for (int k = i; k <= j - 1; k++) {
	    int a1 = eval(M[i][k], M[k + 1][j], operators[k]);
	    int a2 = eval(M[i][k], m[k + 1][j], operators[k]);
	    int a3 = eval(m[i][k], M[k + 1][j], operators[k]);
	    int a4 = eval(m[i][k], m[k + 1][j], operators[k]);
	    int maxK = Math.max(Math.max(Math.max(a1, a2), a3), a4);
	    int minK = Math.min(Math.min(Math.min(a1, a2), a3), a4);
	    if (maxK > max)
		max = maxK;
	    if (minK < min)
		min = minK;
	}

	int maxmin[] = new int[] { max, min };
	return maxmin;
    }

    private static long getMaximValue(String exp) {
	int n = exp.length() / 2 + 1;
	int[] operands = new int[n];
	char[] operators = new char[n - 1];
	for (int i = 0; i < exp.length(); i++) {
	    if (i % 2 == 0)
		operands[i / 2] = Integer.valueOf(String.valueOf(exp.charAt(i))).intValue();
	    else
		operators[i / 2] = exp.charAt(i);
	}

	int[][] M = new int[n][n], m = new int[n][n];
	for (int i = 0; i < n; i++) {
	    M[i][i] = operands[i];
	    m[i][i] = operands[i];
	}

	for (int s = 1; s < n; s++) {
	    for (int i = 0; i < n - s; i++) {
		int j = i + s;
		int[] maxmin = getMaxMin(M, m, operators, i, j);
		M[i][j] = maxmin[0];
		m[i][j] = maxmin[1];
	    }
	}

	return M[0][n - 1];
    }

    private static int eval(int a, int b, char op) {
	if (op == '+') {
	    return a + b;
	} else if (op == '-') {
	    return a - b;
	} else if (op == '*') {
	    return a * b;
	} else {
	    assert false;
	    return 0;
	}
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	String exp = scanner.next();
	System.out.println(getMaximValue(exp));
	scanner.close();
    }
}
