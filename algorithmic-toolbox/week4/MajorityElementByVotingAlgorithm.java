import java.util.Scanner;

/*
 * Voting Algorithm
 * O(n) time
 * O(1) space
 */
public class MajorityElementByVotingAlgorithm {
    private static int getMajorityElement(int[] a) {
	int majorityIndex = 0, majorityCount = 1, index = 1, N = a.length;
	while (index < N) {
	    if (a[majorityIndex] == a[index]) {
		majorityCount++;
	    } else {
		majorityCount--;
	    }

	    if (majorityCount == 0) {
		majorityIndex = index;
		majorityCount = 1;
	    }
	    index++;
	}

	if (majorityCount <= 0)
	    return -1;

	int majorityCandidate = a[majorityIndex], majorityCandidateCount = 0;
	for (int i = 0; i < N; i++) {
	    if (a[i] == majorityCandidate)
		majorityCandidateCount++;
	    if (majorityCandidateCount > N / 2)
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
	if (getMajorityElement(a) != -1) {
	    System.out.println(1);
	} else {
	    System.out.println(0);
	}
	scanner.close();
    }
}
