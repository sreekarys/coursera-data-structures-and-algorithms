import java.util.Scanner;

public class FibonacciSumLastDigit {
    private static final int[] PISANO_SERIES_10 = new int[] { 0, 1, 1, 2, 3, 5, 8, 3, 1, 4, 5, 9, 4, 3, 7, 0, 7, 7, 4,
	    1, 5, 6, 1, 7, 8, 5, 3, 8, 1, 9, 0, 9, 9, 8, 7, 5, 2, 7, 9, 6, 5, 1, 6, 7, 3, 0, 3, 3, 6, 9, 5, 4, 9, 3, 2,
	    5, 7, 2, 9, 1 };
    private static final int PISANO_SERIES_10_PERIOD = PISANO_SERIES_10.length;

    private static int partialSumPisanoSeries(int from, int to) {
	int sum = 0;
	for (int i = from; i <= to; i++) {
	    sum = (sum + PISANO_SERIES_10[i]) % 10;
	}
	return sum;
    }

    private static int getFibonacciSum(long n) {
	int r = (int) (n % PISANO_SERIES_10_PERIOD);
	return partialSumPisanoSeries(0, r) % 10;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	long n = scanner.nextLong();
	System.out.println(getFibonacciSum(n));
	scanner.close();
    }
}
