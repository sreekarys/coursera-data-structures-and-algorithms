import java.util.Scanner;

public class FibonacciPartialSum {
    private static int getFibonacciPartialSum(long m, long n) {
	if (n == 0)
	    return 0;
	if (n == 1)
	    return 1;

	int enMinusTwo = 0, enMinusOne = 1, sum = m == 1 ? 1 : 0;
	for (int i = 2; i <= n; i++) {
	    int en = (enMinusOne + enMinusTwo) % 10;
	    enMinusTwo = enMinusOne;
	    enMinusOne = en;
	    if (i >= m && i <= n) {
		sum += en;
		sum %= 10;
	    }
	}
	return sum;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	long from = scanner.nextLong();
	long to = scanner.nextLong();
	System.out.println(getFibonacciPartialSum(from, to));
	scanner.close();
    }
}
