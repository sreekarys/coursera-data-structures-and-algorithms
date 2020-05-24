import java.util.Scanner;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
	int n = a.length, m = b.length;
	int arr[][] = new int[n + 1][m + 1];
	for (int i = 0; i <= n; i++) {
	    arr[i][0] = 0;
	}
	for (int i = 0; i <= m; i++) {
	    arr[0][i] = 0;
	}

	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j <= m; j++) {
		int c = a[i - 1] == b[j - 1] ? arr[i - 1][j - 1] + 1 : arr[i - 1][j - 1];
		arr[i][j] = Math.max(Math.max(c, arr[i - 1][j]), arr[i][j - 1]);
	    }
	}
	return arr[n][m];
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int[] a = new int[n];
	for (int i = 0; i < n; i++) {
	    a[i] = scanner.nextInt();
	}

	int m = scanner.nextInt();
	int[] b = new int[m];
	for (int i = 0; i < m; i++) {
	    b[i] = scanner.nextInt();
	}
	System.out.println(lcs2(a, b));
	scanner.close();
    }
}
