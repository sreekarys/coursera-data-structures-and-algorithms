import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubstringEqualityUsingCustomHashing {
    private static final long M1 = 1000000009, M2 = 1000000007, X = 263;
    private static long[] H1, H2;

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String s = in.nextLine();
	int q = Integer.valueOf(in.nextLine());
	List<Query> queries = new ArrayList<>(q);
	for (int i = 0; i < q; i++) {
	    queries.add(new Query(in.nextLine().split(" ")));
	}

	int n = s.length();
	H1 = new long[n + 1];
	populateHashes(s, H1, M1, n);
	H2 = new long[n + 1];
	populateHashes(s, H2, M2, n);

	for (Query query : queries) {
	    long AH1 = getHash(H1, query.a, query.l, M1), BH1 = getHash(H1, query.b, query.l, M1),
		    AH2 = getHash(H2, query.a, query.l, M2), BH2 = getHash(H2, query.b, query.l, M2);
	    if (AH1 == BH1 && AH2 == BH2)
		System.out.println("Yes");
	    else
		System.out.println("No");
	}
	in.close();
    }

    private static long getHash(long[] H, int a, int l, long M) {
	return modulo(H[a + l] - ((long) Math.pow(X, l) * H[a]), M);
    }

    private static void populateHashes(String s, long H[], long M, int n) {
	H[0] = 0;
	for (int i = 1; i <= n; i++) {
	    H[i] = modulo(H[i - 1] * X + s.charAt(i - 1), M);
	}
    }

    private static long modulo(long a, long p) {
	return ((a % p) + p) % p;
    }

    static class Query {
	int a, b, l;

	public Query(String arr[]) {
	    this.a = Integer.valueOf(arr[0]);
	    this.b = Integer.valueOf(arr[1]);
	    this.l = Integer.valueOf(arr[2]);
	}
    }
}
