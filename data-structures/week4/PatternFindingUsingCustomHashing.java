import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PatternFindingUsingCustomHashing {
    private static final long MULTIPLIER = 263, PRIME = 1000000007;

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String p = in.next(), t = in.next();
	int P = p.length(), T = t.length();
	List<Long> hashes = preComputeSubstringHashes(t, T, P);
	applyRabinKarp(hashes, t, p);
	in.close();
    }

    private static void applyRabinKarp(List<Long> hashes, String t, String p) {
	long patternHash = computePolynomialHash(p);
	int T = t.length(), P = p.length();
	for (int i = 0; i <= T - P; i++) {
	    if (patternHash == hashes.get(i)) {
		if (p.equals(t.substring(i, i + P)))
		    System.out.print(i + " ");
	    }
	}
	System.out.println();
    }

    private static List<Long> preComputeSubstringHashes(String t, int T, int P) {
	List<Long> hashes = new ArrayList<>();
	long hash = computePolynomialHash(t.substring(T - P));
	hashes.add(hash);

	long Y = 1;
	for (int i = 0; i < P; i++) {
	    Y = modulo(Y * MULTIPLIER, PRIME);
	}
	long prevHash = hash;
	for (int i = T - P - 1; i >= 0; i--) {
	    long currHash = ((MULTIPLIER * prevHash) + t.charAt(i) - (t.charAt(i + P) * Y));
	    currHash = modulo(currHash, PRIME);
	    hashes.add(currHash);
	    prevHash = currHash;
	}
	Collections.reverse(hashes);
	return hashes;
    }

    private static long computePolynomialHash(String s) {
	int n = s.length();
	long hash = 0;
	for (int i = n - 1; i >= 0; i--)
	    hash = modulo(hash * MULTIPLIER + s.charAt(i), PRIME);
	return hash;
    }

    private static long modulo(long a, long p) {
	return ((a % p) + p) % p;
    }

}
