import java.util.Scanner;

public class FibonacciSumSquares {
    private static int getFibonacciSumSquares(long n) {
	if (n == 0)
	    return 0;
	if (n == 1)
	    return 1;

	int enMinusTwo = 0, enMinusOne = 1;
	for (int i = 2; i <= n + 1; i++) {
	    int en = (enMinusOne + enMinusTwo) % 10;
	    enMinusTwo = enMinusOne;
	    enMinusOne = en;
	}
	return (enMinusOne * enMinusTwo) % 10;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	long n = scanner.nextLong();
	long s = getFibonacciSumSquares(n);
	System.out.println(s);
	scanner.close();
    }
}
