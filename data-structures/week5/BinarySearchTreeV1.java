import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearchTreeV1 {
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
	in.close();

	List<Integer> inOrder = new ArrayList<>();
	inOrder(nodes[0], inOrder);
	for (int i = 0; i < inOrder.size() - 1; i++) {
	    if (inOrder.get(i) >= inOrder.get(i + 1)) {
		System.out.println("INCORRECT");
		return;
	    }
	}
	System.out.println("CORRECT");
    }

    private static void inOrder(Node root, List<Integer> inOrder) {
	if (root == null)
	    return;
	inOrder(root.left, inOrder);
	inOrder.add(root.val);
	inOrder(root.right, inOrder);
    }

    static class Node {
	int val;
	Node left, right;

    }
}
