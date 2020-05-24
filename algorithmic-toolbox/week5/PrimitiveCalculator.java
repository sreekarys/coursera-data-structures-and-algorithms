import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
	if (n == 1)
	    return Arrays.stream(new int[] { 1 }).boxed().collect(Collectors.toList());

	int a[] = new int[n + 1];
	a[0] = 0;
	a[1] = 0;
	for (int i = 2; i <= n; i++) {
	    int i1 = a[i - 1];
	    int i2 = i % 2 == 0 ? a[i / 2] : Integer.MAX_VALUE;
	    int i3 = i % 3 == 0 ? a[i / 3] : Integer.MAX_VALUE;
	    a[i] = Math.min(Math.min(i1, i2), i3) + 1;
	}
	List<Integer> sequence = new ArrayList<Integer>();
	sequence.add(n);
	while (n > 1) {
	    if (n % 3 == 0 && a[n] == a[n / 3] + 1) {
		n /= 3;
	    } else if (n % 2 == 0 && a[n] == a[n / 2] + 1) {
		n /= 2;
	    } else {
		n--;
	    }
	    sequence.add(n);
	}
	Collections.reverse(sequence);
	return sequence;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	List<Integer> sequence = optimal_sequence(n);
	System.out.println(sequence.size() - 1);
	for (Integer x : sequence) {
	    System.out.print(x + " ");
	}
	scanner.close();
    }
}
