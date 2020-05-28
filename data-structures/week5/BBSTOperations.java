import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BBSTOperations {
    private static final int M = 1000000001;
    private static long lastSum = 0;

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = Integer.valueOf(in.nextLine());
	String[] queries = new String[N];
	for (int i = 0; i < N; i++) {
	    queries[i] = in.nextLine();
	}

	TreeSet<Integer> treeSet = new TreeSet<>();
	for (int i = 0; i < N; i++) {
	    performOperations(queries[i], treeSet);
	}
	in.close();
    }

    private static void performOperations(String query, TreeSet<Integer> treeSet) {
	String[] s = query.split(" ");
	int l = moduloLastSum(Integer.valueOf(s[1]));
	switch (s[0]) {
	case "+":
	    treeSet.add(l);
	    break;
	case "-":
	    treeSet.remove(l);
	    break;
	case "?":
	    System.out.println(treeSet.contains(l) ? "Found" : "Not found");
	    break;
	case "s":
	    int r = moduloLastSum(Integer.valueOf(s[2]));
	    Set<Integer> subset = treeSet.subSet(l, true, r, true);
	    long sum = 0;
	    for (Integer integer : subset) {
		sum += integer;
	    }
	    lastSum = sum;
	    System.out.println(sum);
	    break;
	default:
	    break;
	}
    }

    private static int moduloLastSum(int x) {
	return modulo(x + lastSum, M);
    }

    private static int modulo(long a, long b) {
	return (int) ((a % b + b) % b);
    }
}
