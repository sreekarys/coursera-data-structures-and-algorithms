import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BinarySearchTreeV2 {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	if (N == 0) {
	    System.out.println("CORRECT");
	    in.close();
	    return;
	}

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
	in.close();

	List<Node> inOrder = inOrderTraversal(nodes[0]);
	for (int i = 0; i < inOrder.size() - 1; i++) {
	    Node now = inOrder.get(i), later = inOrder.get(i + 1);
	    if (now.val > later.val) {
		System.out.println("INCORRECT");
		return;
	    }
	    if (now.val == later.val) {
		if (now.right == null || !find(now.right, later)) {
		    System.out.println("INCORRECT");
		    return;
		}
	    }
	}
	System.out.println("CORRECT");
    }

    private static boolean find(Node root, Node n) {
	if (root.equals(n))
	    return true;
	return root.val <= n.val ? find(root.right, n) : find(root.left, n);
    }

    private static List<Node> inOrderTraversal(Node root) {
	List<Node> inOrder = new ArrayList<>();
	Node curr = root;
	Stack<Node> stack = new Stack<>();
	while (curr != null || !stack.empty()) {
	    while (curr != null) {
		stack.push(curr);
		curr = curr.left;
	    }

	    curr = stack.pop();
	    inOrder.add(curr);
	    curr = curr.right;
	}
	return inOrder;
    }

    static class Node {
	int val;
	Node left, right;

	public boolean equals(Object obj) {
	    Node another = (Node) obj;
	    return this.val == another.val && (this.left == another.left) && (this.right == another.right);
	}
    }
}
