import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class TrieConstruction {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	List<String> patterns = new ArrayList<>();
	for (int i = 0; i < N; i++) {
	    patterns.add(in.next());
	}
	Trie root = constructTrie(patterns);
	printAdjacencyList(root);
	in.close();
    }

    private static Trie constructTrie(List<String> patterns) {
	int label = 0;
	Trie root = new Trie(label, "", true);
	for (String pattern : patterns) {
	    Trie current = root;
	    for (int i = 0; i < pattern.length(); i++) {
		String content = String.valueOf(pattern.charAt(i));
		if (current.getChild(content) == null)
		    current.addChildren(new Trie(++label, content, i == pattern.length() - 1));
		current = current.getChild(content);
	    }
	}
	return root;
    }

    private static void printAdjacencyList(Trie root) {
	Queue<Trie> q = new LinkedList<>();
	q.add(root);
	while (!q.isEmpty()) {
	    Trie current = q.poll();
	    current.getChildren().forEach((content, child) -> {
		System.out.println(current.getLabel() + "->" + child.getLabel() + ":" + child.getContent());
		q.add(child);
	    });
	}
    }

    static class Trie {
	private int label;
	private String content;
	private boolean leaf;
	private Map<String, Trie> children;

	public Trie(int label, String content, boolean leaf) {
	    this.label = label;
	    this.content = content;
	    this.leaf = leaf;
	    this.children = new HashMap<>();
	}

	public void addChildren(Trie child) {
	    children.put(child.getContent(), child);
	}

	public int getLabel() {
	    return label;
	}

	public String getContent() {
	    return content;
	}

	public boolean isLeaf() {
	    return leaf;
	}

	public Map<String, Trie> getChildren() {
	    return children;
	}

	public Trie getChild(String childContent) {
	    return children.get(childContent);
	}

    }
}
