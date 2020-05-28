import java.util.Scanner;

public class BinarySearchTreeRecursion {
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
	if (isBST(nodes[0], Integer.MIN_VALUE, Integer.MAX_VALUE))
	    System.out.println("CORRECT");
	else
	    System.out.println("INCORRECT");
    }

    private static boolean isBST(Node root, int min, int max) {
	if (root == null)
	    return true;
	if (root.val > max || root.val < min)
	    return false;
	return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    static class Node {
	int val;
	Node left, right;

    }
}
