import java.util.Scanner;

public class MaxPairwiseProduct {
    private static long getMaxPairwiseProduct(long[] numbers) {
	long max = 0, second_max = 0;
	for (int i = 0; i < numbers.length; i++) {
	    if (numbers[i] > max) {
		second_max = max;
		max = numbers[i];
	    } else if (numbers[i] > second_max) {
		second_max = numbers[i];
	    }
	}
	return max * second_max;
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
