import java.util.Scanner;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigit(int n) {
	if (n == 0)
	    return 0;
	if (n == 1)
	    return 1;

	int enMinusTwo = 0, enMinusOne = 1;
	for (int i = 2; i <= n; i++) {
	    int en = enMinusOne + enMinusTwo;
	    enMinusTwo = enMinusOne;
	    enMinusOne = en % 10;
	}
	return enMinusOne;
    }

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	System.out.println(getFibonacciLastDigit(n));
	in.close();
    }
}
