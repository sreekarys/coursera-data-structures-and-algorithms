import java.util.Scanner;

public class BinarySearch {

    private static int binarySearch(int[] a, int l, int r, int x) {
	if (l > r)
	    return -1;
	int m = (l + r) / 2;
	if (a[m] == x)
	    return m;
	return x < a[m] ? binarySearch(a, l, m - 1, x) : binarySearch(a, m + 1, r, x);
    }

    private static int binarySearch(int[] a, int x) {
	return binarySearch(a, 0, a.length - 1, x);
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
	for (int i = 0; i < m; i++) {
	    System.out.print(binarySearch(a, b[i]) + " ");
	}
	scanner.close();
    }
}
