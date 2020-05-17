import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciHuge {
    private static boolean isPeriodic(List<Integer> fibonacci, long m) {
	int size = fibonacci.size();
	if (size != 3 && fibonacci.get(size - 1) == 1 && fibonacci.get(size - 2) == 1 && fibonacci.get(size - 3) == 0)
	    return true;
	return false;
    }

    private static int[] getPeriod(long n, long m) {
	if (m == 2)
	    return new int[] { 0, 1, 1 };

	List<Integer> fibonacci = new ArrayList<Integer>();
	fibonacci.add(0);
	fibonacci.add(1);
	for (int i = 2; i <= n; i++) {
	    long en = (long) (fibonacci.get(i - 1) + fibonacci.get(i - 2));
	    fibonacci.add((int) (en % m));
	    if (isPeriodic(fibonacci, m)) {
		return fibonacci.subList(0, i - 2).stream().mapToInt(num -> num).toArray();
	    }
	}
	return fibonacci.stream().mapToInt(num -> num).toArray();
    }

    private static long getFibonacciHuge(long n, long m) {
	if (n == 0)
	    return 0;
	if (n == 1)
	    return 1;

	int[] fibonacciModuloArray = getPeriod(n, m);
	int period = fibonacciModuloArray.length;
	int rem = (int) (n % period);
	return fibonacciModuloArray[rem];
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	long n = scanner.nextLong();
	long m = scanner.nextLong();
	System.out.println(getFibonacciHuge(n, m));
	scanner.close();
    }
}
