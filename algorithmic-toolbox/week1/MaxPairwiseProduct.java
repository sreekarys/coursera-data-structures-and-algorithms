import java.util.Scanner;

public class MaxPairwiseProduct {
    private static long getMaxPairwiseProduct(long[] numbers) {
	long max_product = 0;
	int n = numbers.length;

	for (int first = 0; first < n; ++first) {
	    for (int second = first + 1; second < n; ++second) {
		max_product = Math.max(max_product, numbers[first] * numbers[second]);
	    }
	}

	return max_product;
    }

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int n = sc.nextInt();
	long numbers[] = new long[n];
	for (int i = 0; i < n; i++) {
	    numbers[i] = Long.valueOf(sc.next());
	}
	System.out.println(getMaxPairwiseProduct(numbers));
	sc.close();
    }

}
