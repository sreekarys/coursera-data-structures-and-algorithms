import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * O(n) amortized time for converting an array into a min binary heap.
 * O(1) extra space
 */
public class BuildHeap {
    public static void main(String[] args) throws IOException {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	int[] a = new int[N];
	for (int i = 0; i < N; i++) {
	    a[i] = in.nextInt();
	}
	buildHeapAndPrintSwaps(a, N);
	in.close();
    }

    private static void buildHeapAndPrintSwaps(int[] a, int N) {
	List<Pair> swaps = new ArrayList<>();
	for (int i = N / 2; i >= 0; i--) {
	    shiftDown(a, N, i, swaps);
	}
	System.out.println(swaps.size());
	swaps.stream().forEach(pair -> {
	    System.out.println(pair.x + " " + pair.y);
	});
    }

    private static void shiftDown(int a[], int N, int i, List<Pair> swaps) {
	int smallest = i, l = 2 * i + 1, r = 2 * i + 2;
	if (l < N && a[l] < a[smallest]) {
	    smallest = l;
	}

	if (r < N && a[r] < a[smallest]) {
	    smallest = r;
	}

	if (smallest != i) {
	    swap(a, i, smallest);
	    swaps.add(new Pair(i, smallest));
	    shiftDown(a, N, smallest, swaps);
	}
    }

    private static void swap(int a[], int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
    }

    static class Pair {
	int x, y;

	Pair(int x, int y) {
	    this.x = x;
	    this.y = y;
	}
    }
}
