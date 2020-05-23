import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Hashing the counts of elements
 * O(n) time
 * O(n) extra space
 */
public class MajorityElementByHashing {
    private static int getMajorityElement(int[] a) {
	int N = a.length;
	Map<Integer, Integer> countMap = new HashMap<>();
	for (int i = 0; i < N; i++) {
	    int count = countMap.containsKey(a[i]) ? countMap.get(a[i]) : 0;
	    count++;
	    if (count > N / 2)
		return 1;
	    countMap.put(a[i], count);
	}
	return -1;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int[] a = new int[n];
	for (int i = 0; i < n; i++) {
	    a[i] = scanner.nextInt();
	}
	if (getMajorityElement(a) != -1) {
	    System.out.println(1);
	} else {
	    System.out.println(0);
	}
	scanner.close();
    }
}
