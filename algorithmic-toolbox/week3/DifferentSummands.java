import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
	List<Integer> summands = new ArrayList<Integer>();
	int current = 0;
	while (n >= (current + 1)) {
	    current++;
	    n -= current;
	    summands.add(current);
	}

	int N = summands.size();
	summands.add(N - 1, summands.remove(N - 1) + n);
	return summands;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	List<Integer> summands = optimalSummands(n);
	System.out.println(summands.size());
	for (Integer summand : summands) {
	    System.out.print(summand + " ");
	}
	scanner.close();
    }
}
