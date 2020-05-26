
public class HeapMain {
    public static void main(String[] args) {
	MaxBinaryHeap heap = new MaxBinaryHeap(16);
	System.out.println(heap.getMax());
	System.out.println(heap.extractMax());
	try {
	    heap.changePriority(1, 10);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	try {
	    heap.remove(1);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	try {
	    heap.insert(1);
	    heap.insert(2);
	    heap.insert(3);
	    heap.insert(4);
	    heap.insert(5);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	System.out.println(heap.getMax());
	System.out.println(heap.extractMax());
	try {
	    heap.changePriority(1, 10);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	try {
	    heap.remove(1);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	System.out.println(heap.getMax());
	System.out.println(heap.extractMax());
    }
}
