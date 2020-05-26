
/*
 * Max Binary Heap Implementation
 * getMax() : O(1)
 * insert(i) : O(log n)
 * changePriority(i, priority) : O(log n)
 * extractMax() : O(log n)
 * remove(i) : O(log n)
 */
public class MaxBinaryHeap {
    private int end = -1, capacity;
    private int[] H;

    public MaxBinaryHeap(int capacity) {
	this.capacity = capacity;
	H = new int[capacity];
    }

    public int getMax() {
	return end >= 0 ? H[0] : Integer.MIN_VALUE;
    }

    public void insert(int element) throws Exception {
	if (end + 1 == capacity)
	    throw new Exception("Capacity Exceeded!!!");
	end++;
	H[end] = element;
	shiftUp(end);
    }

    public int extractMax() {
	if (end < 0 || end >= capacity)
	    return Integer.MIN_VALUE;
	int max = H[0];
	H[0] = H[end];
	end--;
	if (end > 0)
	    shiftDown(0);
	return max;
    }

    public void changePriority(int index, int newPriority) throws Exception {
	if (index < 0 || index > end)
	    throw new Exception("Invalid Index");
	int oldPriority = H[index];
	H[index] = newPriority;
	if (oldPriority > newPriority)
	    shiftDown(index);
	else
	    shiftUp(index);
    }

    public void remove(int index) throws Exception {
	changePriority(index, Integer.MAX_VALUE);
	extractMax();
    }

    private void shiftUp(int index) {
	int parent = parent(index);
	while (parent >= 0 && H[index] > H[parent]) {
	    swap(index, parent);
	    index = parent;
	    parent = parent(index);
	}
    }

    private void shiftDown(int index) {
	int largest = index, left = left(index), right = right(index);
	if (left <= end && H[left] > H[largest]) {
	    largest = left;
	}
	if (right <= end && H[right] > H[largest]) {
	    largest = right;
	}

	if (largest != index) {
	    swap(largest, index);
	    shiftDown(largest);
	}
    }

    private void swap(int i, int j) {
	int temp = H[j];
	H[j] = H[i];
	H[i] = temp;
    }

    private int parent(int i) {
	return (i - 1) / 2;
    }

    private int left(int i) {
	return 2 * i + 1;
    }

    private int right(int i) {
	return 2 * i + 2;
    }

}
