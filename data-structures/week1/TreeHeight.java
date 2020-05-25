import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
	System.out.println(calculateHeightBFS(root));
	in.close();
    }

    private static int calculateHeightBFS(Node root) {
	if (root == null)
	    return 0;

	int height = 0;
	Queue<Node> q1 = new LinkedList<>(), q2 = new LinkedList<>();
	q1.add(root);
	while (!q1.isEmpty() || !q2.isEmpty()) {
	    if (!q1.isEmpty()) {
		height++;
	    }

	    while (!q1.isEmpty()) {
		Node n = q1.poll();
		for (Node child : n.children) {
		    q2.add(child);
		}
	    }

	    if (!q2.isEmpty()) {
		height++;
	    }

	    while (!q2.isEmpty()) {
		Node n = q2.poll();
		for (Node child : n.children) {
		    q1.add(child);
		}
	    }
	}
	return height;
    }

    private static int calculateHeightDFS(Node root) {
	if (root == null)
	    return 0;
	int maxHeightAmongChildren = 0;
	for (Node child : root.children) {
	    int heightOfChild = calculateHeightDFS(child);
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
