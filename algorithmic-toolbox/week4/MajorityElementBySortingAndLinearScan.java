import java.util.Arrays;
import java.util.Scanner;

/*
 * Sorting and Liner Scan - Greedy?
 * O(nlogn) time
 * O(1) extra space, other than stack space used for sorting
 */
public class MajorityElementBySortingAndLinearScan {
    private static int getMajorityElement(int[] a) {
	Arrays.sort(a);
	int N = a.length, index = 0;
	while (index < N) {
	    int count = 1;
	    while (index < N - 1 && a[index] == a[index + 1]) {
		index++;
		count++;
	    }
	    if (count > N / 2)
		return a[index];
	    index++;
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
