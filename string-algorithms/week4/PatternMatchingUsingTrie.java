import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PatternMatchingUsingTrie {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String pattern = in.next(), text = in.next();
	Trie root = constructTrie(Arrays.asList(new String[] { pattern }));
	matchPatterns(root, text);
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
	    current.setLeaf(true);
	}
	return root;
    }

    private static void matchPatterns(Trie root, String text) {
	for (int i = 0; i < text.length(); i++) {
	    String currentText = text.substring(i);
	    if (isMatch(root, currentText))
		System.out.print(i + " ");
	}
	System.out.println();
    }

    private static boolean isMatch(Trie root, String text) {
	Trie current = root;
	for (int i = 0; i < text.length(); i++) {
	    String content = String.valueOf(text.charAt(i));
	    if (current.getChild(content) == null)
		return false;
	    current = current.getChild(content);
	    if (current.isLeaf())
		return true;
	}

	return current.isLeaf();
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

	public void setLeaf(boolean leaf) {
	    this.leaf = leaf;
	}

	public Map<String, Trie> getChildren() {
	    return children;
	}

	public Trie getChild(String childContent) {
	    return children.get(childContent);
	}

    }
}
