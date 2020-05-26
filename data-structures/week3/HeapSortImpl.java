
/*
 * sort(int[]) : Heap Sort using MaxBinaryHeap
 * sortInPlace(int[]) : sort in place using buildHeap and extractMax logic
 */
public class HeapSortImpl {

    public static void sort(int[] A) throws Exception {
	int N = A.length;
	MaxBinaryHeap heap = new MaxBinaryHeap(N);
	for (int i = 0; i < N; i++) {
	    heap.insert(A[i]);
	}

	for (int i = N - 1; i >= 0; i--) {
	    A[i] = heap.extractMax();
	}
    }

    public static void sortInplace(int[] A) {
	buildHeap(A);
	int N = A.length;
	for (int i = 0; i < N; i++) {
	    int temp = A[N - 1 - i];
	    A[N - 1 - i] = A[0];
	    A[0] = temp;
	    shiftDown(A, N - 1 - i, 0);
	}
    }

    private static void buildHeap(int[] A) {
	int N = A.length;
	for (int i = N / 2; i >= 0; i--) {
	    shiftDown(A, N, i);
	}
    }

    private static void shiftDown(int[] A, int N, int index) {
	int largest = index, left = 2 * index + 1, right = 2 * index + 2;
	if (left < N && A[left] > A[largest])
	    largest = left;
	if (right < N && A[right] > A[largest])
	    largest = right;

	if (largest != index) {
	    int temp = A[largest];
	    A[largest] = A[index];
	    A[index] = temp;
	    shiftDown(A, N, largest);
	}
    }
}
