import java.util.Scanner;

class EditDistance {
    private static int editDistance(String s, String t) {
	int n = s.length(), m = t.length();
	int a[][] = new int[n + 1][m + 1];
	for (int i = 0; i <= n; i++) {
	    a[i][0] = i;
	}
	for (int i = 0; i <= m; i++) {
	    a[0][i] = i;
	}

	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j <= m; j++) {
		int a1 = a[i - 1][j] + 1;
		int a2 = a[i][j - 1] + 1;
		int a3 = s.charAt(i-1) == t.charAt(j-1) ? a[i - 1][j - 1] : a[i - 1][j - 1] + 1;
		a[i][j] = Math.min(Math.min(a1, a2), a3);
	    }
	}
	return a[n][m];
    }

    public static void main(String args[]) {
	Scanner scan = new Scanner(System.in);
	String s = scan.next();
	String t = scan.next();
	System.out.println(editDistance(s, t));
	scan.close();
    }

}
