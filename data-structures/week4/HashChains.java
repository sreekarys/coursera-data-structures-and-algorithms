import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<List<String>> chains;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
	new HashChains().processQueries();
    }

    private int hashFunc(String s) {
	long hash = 0;
	for (int i = s.length() - 1; i >= 0; --i)
	    hash = (hash * multiplier + s.charAt(i)) % prime;
	return (int) hash % bucketCount;
    }

    private Query readQuery() throws IOException {
	String type = in.next();
	if (!type.equals("check")) {
	    String s = in.next();
	    return new Query(type, s);
	} else {
	    int ind = in.nextInt();
	    return new Query(type, ind);
	}
    }

    private void writeSearchResult(boolean wasFound) {
	System.out.println(wasFound ? "yes" : "no");
	// Uncomment the following if you want to play with the program interactively.
	// out.flush();
    }

    private void processQuery(Query query) {
	switch (query.type) {
	case "add":
	    add(query.s);
	    break;
	case "del":
	    remove(query.s);
	    break;
	case "find":
	    writeSearchResult(find(query.s));
	    break;
	case "check":
	    check(query.ind);
	    // Uncomment the following if you want to play with the program interactively.
	    // out.flush();
	    break;
	default:
	    throw new RuntimeException("Unknown query: " + query.type);
	}
    }

    private boolean find(String s) {
	int hash = hashFunc(s);
	return chains.get(hash).contains(s);
    }

    private void add(String s) {
	if (!find(s)) {
	    int hash = hashFunc(s);
	    chains.get(hash).add(s);
	}
    }

    private void remove(String s) {
	if (find(s)) {
	    int hash = hashFunc(s);
	    chains.get(hash).remove(s);
	}
    }

    private void check(int index) {
	List<String> chain = chains.get(index);
	for (int i = chain.size() - 1; i >= 0; i--) {
	    System.out.print(chain.get(i) + " ");
	}
	System.out.println();
    }

    public void processQueries() throws IOException {
	in = new FastScanner();
	out = new PrintWriter(new BufferedOutputStream(System.out));
	bucketCount = in.nextInt();
	chains = new ArrayList<>();
	for (int i = 0; i < bucketCount; i++) {
	    chains.add(new ArrayList<>());
	}
	int queryCount = in.nextInt();
	for (int i = 0; i < queryCount; ++i) {
	    processQuery(readQuery());
	}
	out.close();
    }

    static class Query {
	String type;
	String s;
	int ind;

	public Query(String type, String s) {
	    this.type = type;
	    this.s = s;
	}

	public Query(String type, int ind) {
	    this.type = type;
	    this.ind = ind;
	}
    }

    static class FastScanner {
	private BufferedReader reader;
	private StringTokenizer tokenizer;

	public FastScanner() {
	    reader = new BufferedReader(new InputStreamReader(System.in));
	    tokenizer = null;
	}

	public String next() throws IOException {
	    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
		tokenizer = new StringTokenizer(reader.readLine());
	    }
	    return tokenizer.nextToken();
	}

	public int nextInt() throws IOException {
	    return Integer.parseInt(next());
	}
    }
}
