import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeHeight {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	int a[] = new int[N];
	for (int i = 0; i < N; i++) {
	    a[i] = in.nextInt();
	}

	Node root = constructTree(a, N);
	System.out.println(calculateHeight(root));
	in.close();
    }

    private static int calculateHeight(Node root) {
	if (root == null)
	    return 0;
	int maxHeightAmongChildren = 0;
	for (Node child : root.children) {
	    int heightOfChild = calculateHeight(child);
	    if (heightOfChild > maxHeightAmongChildren)
		maxHeightAmongChildren = heightOfChild;
	}
	return 1 + maxHeightAmongChildren;
    }

    private static Node constructTree(int a[], int N) {
	Node[] nodes = new Node[N];
	for (int i = 0; i < N; i++) {
	    nodes[i] = new Node(i);
	}

	Node root = null;
	for (int i = 0; i < N; i++) {
	    if (a[i] == -1)
		root = nodes[i];
	    else
		nodes[a[i]].addChild(nodes[i]);
	}
	return root;
    }

    static class Node {
	int value;
	List<Node> children = new ArrayList<>();

	public Node(int value) {
	    this.value = value;
	}

	public void addChild(Node n) {
	    children.add(n);
	}
    }
}
