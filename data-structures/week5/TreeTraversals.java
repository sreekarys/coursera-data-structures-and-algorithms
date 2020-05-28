import java.util.Scanner;
import java.util.Stack;

public class TreeTraversals {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	Node[] nodes = new Node[N];
	for (int i = 0; i < N; i++) {
	    nodes[i] = new Node();
	}

	for (int i = 0; i < N; i++) {
	    Node n = nodes[i];
	    n.val = in.nextInt();
	    int left = in.nextInt();
	    n.left = left == -1 ? null : nodes[left];
	    int right = in.nextInt();
	    n.right = right == -1 ? null : nodes[right];
	}

	iterativeInOrderTrversal(nodes[0]);
	System.out.println();
	iterativePreOrderTrversal(nodes[0]);
	System.out.println();
	iterativePostOrderTrversal(nodes[0]);
	System.out.println();
	in.close();
    }

    private static void inOrderTraversal(Node root) {
	if (root == null)
	    return;
	inOrderTraversal(root.left);
	System.out.print(root.val + " ");
	inOrderTraversal(root.right);
    }

    private static void iterativeInOrderTrversal(Node root) {
	Node curr = root;
	Stack<Node> stack = new Stack<>();
	while (curr != null || !stack.empty()) {
	    while (curr != null) {
		stack.push(curr);
		curr = curr.left;
	    }

	    curr = stack.pop();
	    System.out.print(curr.val + " ");
	    curr = curr.right;
	}
    }

    private static void preOrderTraversal(Node root) {
	if (root == null)
	    return;
	System.out.print(root.val + " ");
	preOrderTraversal(root.left);
	preOrderTraversal(root.right);
    }

    private static void iterativePreOrderTrversal(Node root) {
	Node curr = root;
	Stack<Node> stack = new Stack<>();
	while (curr != null) {
	    System.out.print(curr.val + " ");
	    if (curr.right != null)
		stack.push(curr.right);
	    if (curr.left != null)
		stack.push(curr.left);
	    curr = stack.empty() ? null : stack.pop();
	}
    }

    private static void postOrderTraversal(Node root) {
	if (root == null)
	    return;
	postOrderTraversal(root.left);
	postOrderTraversal(root.right);
	System.out.print(root.val + " ");
    }

    private static void iterativePostOrderTrversal(Node root) {
	Stack<Node> stack = new Stack<>(), auxiliaryStack = new Stack<>();
	Node curr;
	stack.push(root);
	while (!stack.empty()) {
	    curr = stack.pop();
	    auxiliaryStack.push(curr);
	    if (curr.left != null)
		stack.push(curr.left);
	    if (curr.right != null)
		stack.push(curr.right);
	}
	while (!auxiliaryStack.empty()) {
	    System.out.print(auxiliaryStack.pop().val + " ");
	}
    }

    static class Node {
	int val;
	Node left, right;

    }
}
