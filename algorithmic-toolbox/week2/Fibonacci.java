import java.util.Scanner;

public class Fibonacci {
    private static long calc_fib(int n) {
	if (n == 0)
	    return 0;
	if (n == 1)
	    return 1;

	long enMinusTwo = 0, enMinusOne = 1;
	for (int i = 2; i <= n; i++) {
	    long en = enMinusOne + enMinusTwo;
	    enMinusTwo = enMinusOne;
	    enMinusOne = en;
	}
	return enMinusOne;
    }

    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	System.out.println(calc_fib(n));
	in.close();
    }
}
