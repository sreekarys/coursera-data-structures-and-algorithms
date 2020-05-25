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
	private final MaxStack auxiliaryStack = new MaxStack();
	private final MaxStack stack = new MaxStack();

	public void enqueue(int operand) {
	    stack.push(operand);
	}

	public int dequeue() {
	    if (auxiliaryStack.empty()) {
		while (!stack.empty()) {
		    auxiliaryStack.push(stack.peek());
		    stack.pop();
		}
	    }
	    int earliest = auxiliaryStack.peek();
	    auxiliaryStack.pop();
	    return earliest;
	}

	public int max() {
	    int a = stack.max(), b = auxiliaryStack.empty() ? Integer.MIN_VALUE : auxiliaryStack.max();
	    return a > b ? a : b;
	}
    }

    static class MaxStack {
	private final Stack<Integer> mainStack = new Stack<>();
	private final Stack<Integer> auxiliaryStack = new Stack<>();

	public void push(int operand) {
	    mainStack.push(operand);
	    if (auxiliaryStack.empty() || operand >= auxiliaryStack.peek()) {
		auxiliaryStack.push(operand);
	    }
	}

	public void pop() {
	    if (!mainStack.empty()) {
		int latest = mainStack.pop();
		if (auxiliaryStack.peek() == latest) {
		    auxiliaryStack.pop();
		}
	    }
	}

	public int peek() {
	    return mainStack.peek();
	}

	public boolean empty() {
	    return mainStack.empty();
	}

	public int max() {
	    return auxiliaryStack.peek();
	}
    }
}
