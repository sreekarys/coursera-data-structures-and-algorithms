import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * O(n) time - each element is added to and removed from the deque at most once.
 * O(M) extra space for the deque
 */
public class SlidingWindowMaximumUsingDequeue {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	int a[] = new int[N];
	for (int i = 0; i < N; i++) {
	    a[i] = in.nextInt();
	}
	int M = in.nextInt();
	printMaxInSlidingWindows(a, N, M);
	in.close();
    }

    private static void printMaxInSlidingWindows(int a[], int N, int M) {
	Deque<Integer> dq = new LinkedList<>();
	for (int i = 0; i < M; i++) {
	    while (!dq.isEmpty() && a[i] > a[dq.peekLast()]) {
		dq.pollLast();
	    }
	    dq.addLast(i);
	}
	System.out.print(a[dq.peekFirst()] + " ");

	for (int i = M; i < N; i++) {
	    if (i - dq.peekFirst() >= M)
		dq.pollFirst();
	    while (!dq.isEmpty() && a[i] > a[dq.peekLast()])
		dq.pollLast();
	    dq.addLast(i);
	    System.out.print(a[dq.peekFirst()] + " ");
	}
    }
}
