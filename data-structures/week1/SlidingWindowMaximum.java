import java.util.Scanner;
import java.util.Stack;

public class SlidingWindowMaximum {

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
	MyQueue queue = new MyQueue();
	for (int i = 0; i < M; i++) {
	    queue.enqueue(a[i]);
	}
	System.out.print(queue.max() + " ");

	int index = M;
	while (index < N) {
	    queue.dequeue();
	    queue.enqueue(a[index]);
	    System.out.print(queue.max() + " ");
	    index++;
	}
    }

    static class MyQueue {
	private static final Stack<Integer> AUXILIARY_STACK = new Stack<>();
	private static final MaxStack MAX_STACK = new MaxStack();

	public void enqueue(int operand) {
	    while (!MAX_STACK.empty()) {
		AUXILIARY_STACK.push(MAX_STACK.peek());
		MAX_STACK.pop();
	    }

	    MAX_STACK.push(operand);

	    while (!AUXILIARY_STACK.empty()) {
		MAX_STACK.push(AUXILIARY_STACK.pop());
	    }
	}

	public int dequeue() {
	    int earliest = MAX_STACK.peek();
	    MAX_STACK.pop();
	    return earliest;
	}

	public int max() {
	    return MAX_STACK.max();
	}
    }

    static class MaxStack {
	private static final Stack<Integer> STACK = new Stack<>();
	private static final Stack<Integer> AUXILIARY_STACK = new Stack<>();

	public void push(int operand) {
	    STACK.push(operand);
	    if (AUXILIARY_STACK.empty() || operand >= AUXILIARY_STACK.peek()) {
		AUXILIARY_STACK.push(operand);
	    }
	}

	public void pop() {
	    if (!STACK.empty()) {
		int latest = STACK.pop();
		if (AUXILIARY_STACK.peek() == latest) {
		    AUXILIARY_STACK.pop();
		}
	    }
	}

	public int peek() {
	    return STACK.peek();
	}

	public boolean empty() {
	    return STACK.empty();
	}

	public int max() {
	    return AUXILIARY_STACK.peek();
	}
    }
}
