import java.util.Scanner;

public class FibonacciSumLastDigit {
    private static int getFibonacciSum(long n) {
	if (n == 0)
	    return 0;
	if (n == 1)
	    return 1;

	int enMinusTwo = 0, enMinusOne = 1, sum = 1;
	for (int i = 2; i <= n; i++) {
	    int en = (enMinusOne + enMinusTwo) % 10;
	    enMinusTwo = enMinusOne;
	    enMinusOne = en;
	    sum += en;
	    sum %= 10;
	}
	return sum;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	long n = scanner.nextLong();
	long s = getFibonacciSum(n);
	System.out.println(s);
	scanner.close();
    }
}
