import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class StackWithMaxProblem {

    public static void main(String[] args) throws IOException {
	Scanner scanner = new Scanner(System.in);
	int N = Integer.valueOf(scanner.nextLine());
	List<String> queries = new ArrayList<>();
	for (int i = 0; i < N; i++) {
	    queries.add(scanner.nextLine());
	}

	MaxStack stack = new MaxStack();
	for (int i = 0; i < N; i++) {
	    String query = queries.get(i);
	    int operand = -1;
	    if (query.startsWith("push")) {
		String[] s = query.split(" ");
		query = s[0];
		operand = Integer.valueOf(s[1]);
	    }
	    performOperation(stack, query, operand);
	}
	scanner.close();
    }

    private static void performOperation(MaxStack stack, String operator, int operand) {
	switch (operator) {
	case "push":
	    stack.push(operand);
	    break;

	case "pop":
	    stack.pop();
	    break;
	case "max":
	    System.out.println(stack.max());
	    break;
	default:
	    assert false;
	    break;
	}
    }

    static class MaxStack {
	private static final Stack<Integer> STACK = new Stack<>();
	private static final Stack<Integer> MAX_STACK = new Stack<>();

	public void push(int operand) {
	    STACK.push(operand);
	    if (MAX_STACK.empty() || operand >= MAX_STACK.peek()) {
		MAX_STACK.push(operand);
	    }
	}

	public void pop() {
	    if (!STACK.empty()) {
		int latest = STACK.pop();
		if (MAX_STACK.peek() == latest) {
		    MAX_STACK.pop();
		}
	    }
	}

	public int max() {
	    return MAX_STACK.peek().intValue();
	}
    }
}
