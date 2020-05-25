import java.util.Scanner;

/*
 * O(n^3) time
 * O(n^2) extra space
 */
public class PlacingParentheses {
    private static long[] getMaxMin(long[][] M, long[][] m, char[] operators, int i, int j) {
	long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
	for (int k = i; k <= j - 1; k++) {
	    long a1 = eval(M[i][k], M[k + 1][j], operators[k]);
	    long a2 = eval(M[i][k], m[k + 1][j], operators[k]);
	    long a3 = eval(m[i][k], M[k + 1][j], operators[k]);
	    long a4 = eval(m[i][k], m[k + 1][j], operators[k]);
	    long maxK = Math.max(Math.max(Math.max(a1, a2), a3), a4);
	    long minK = Math.min(Math.min(Math.min(a1, a2), a3), a4);
	    if (maxK > max)
		max = maxK;
	    if (minK < min)
		min = minK;
	}

	long maxmin[] = new long[] { max, min };
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

	long[][] M = new long[n][n], m = new long[n][n];
	for (int i = 0; i < n; i++) {
	    M[i][i] = operands[i];
	    m[i][i] = operands[i];
	}

	for (int s = 1; s < n; s++) {
	    for (int i = 0; i < n - s; i++) {
		int j = i + s;
		long[] maxmin = getMaxMin(M, m, operators, i, j);
		M[i][j] = maxmin[0];
		m[i][j] = maxmin[1];
	    }
	}

	return M[0][n - 1];
    }

    private static long eval(long a, long b, char op) {
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
