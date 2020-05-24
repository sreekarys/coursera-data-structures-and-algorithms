import java.util.Scanner;

public class ChangeDP {
    private static int min(int a, int b, int c) {
	return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    private static int getChange(int m) {
	int arr[] = new int[m + 1];
	arr[0] = 0;
	for (int i = 1; i <= m; i++) {
	    int i1 = i >= 1 ? arr[i - 1] : Integer.MAX_VALUE;
	    int i3 = i >= 3 ? arr[i - 3] : Integer.MAX_VALUE;
	    int i4 = i >= 4 ? arr[i - 4] : Integer.MAX_VALUE;
	    arr[i] = min(i1, i3, i4) + 1;
	}
	return arr[m];
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int m = scanner.nextInt();
	System.out.println(getChange(m));
	scanner.close();
    }
}
