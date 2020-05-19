import java.util.Scanner;

public class Change {
    private static final int[] SORTED_DENOMINATIONS = new int[] { 10, 5, 1 };

    private static int getChange(int m) {
	int result = 0;
	for (int i = 0; i < SORTED_DENOMINATIONS.length; i++) {
	    if (m <= 0)
		return result;
	    if (m >= SORTED_DENOMINATIONS[i]) {
		int q = m / SORTED_DENOMINATIONS[i];
		m %= SORTED_DENOMINATIONS[i];
		result += q;
	    }
	}
	return result;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int m = scanner.nextInt();
	System.out.println(getChange(m));
	scanner.close();
    }
}
