import java.util.Scanner;

/*
 * Divide and Conquer
 * O(nlogn) time
 * O(1) extra space, other than stack space used for recursion
 */
public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
	if (left == right)
	    return -1;
	if (right - left == 1)
	    return a[left] == a[right] ? a[left] : -1;

	int mid = (left + right) / 2;
	int leftMajority = getMajorityElement(a, left, mid - 1);
	int rightMajority = getMajorityElement(a, mid + 1, right);
	if (leftMajority == rightMajority)
	    return leftMajority;
	int majorityCandidate = leftMajority == -1 ? rightMajority : leftMajority;
	int countMajorityCandidate = 0;
	for (int i = left; i <= right; i++) {
	    if (a[i] == majorityCandidate)
		countMajorityCandidate++;
	    if (countMajorityCandidate > Math.ceil((right - left) / 2))
		return majorityCandidate;
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
	if (getMajorityElement(a, 0, n - 1) != -1) {
	    System.out.println(1);
	} else {
	    System.out.println(0);
	}
	scanner.close();
    }

}
