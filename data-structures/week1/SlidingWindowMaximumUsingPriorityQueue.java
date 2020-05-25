import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SlidingWindowMaximumUsingPriorityQueue {
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
	PriorityQueue<Integer> pq = new PriorityQueue<>(M,
		Collections.reverseOrder(Comparator.comparingInt(i -> i.intValue())));
	for (int i = 0; i < M; i++) {
	    pq.add(a[i]);
	}
	System.out.print(pq.peek() + " ");

	int index = M;
	while (index < N) {
	    pq.remove(a[index - M]);
	    pq.add(a[index]);
	    System.out.print(pq.peek() + " ");
	    index++;
	}
    }
}
