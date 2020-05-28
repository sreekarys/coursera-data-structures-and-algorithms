import java.util.Scanner;

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

	inOrder(nodes[0]);
	System.out.println();
	preOrder(nodes[0]);
	System.out.println();
	postOrder(nodes[0]);
	System.out.println();
	in.close();
    }

    private static void inOrder(Node root) {
	if (root == null)
	    return;
	inOrder(root.left);
	System.out.print(root.val + " ");
	inOrder(root.right);
    }

    private static void preOrder(Node root) {
	if (root == null)
	    return;
	System.out.print(root.val + " ");
	preOrder(root.left);
	preOrder(root.right);
    }

    private static void postOrder(Node root) {
	if (root == null)
	    return;
	postOrder(root.left);
	postOrder(root.right);
	System.out.print(root.val + " ");
    }

    static class Node {
	int val;
	Node left, right;

    }
}
