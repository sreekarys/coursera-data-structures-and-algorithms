import java.util.Scanner;

public class PatternMatchingUsingKMP {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String pattern = in.next(), text = in.next();
	kmpMatching(text, pattern);
	in.close();
    }

    private static void kmpMatching(String text, String pattern) {
	String s = pattern + "$" + text;
	int[] prefixFunction = new int[s.length()];
	int P = pattern.length(), L = s.length();
	computePrefixFunction(prefixFunction, s);
	for (int i = P + 1; i < L; i++) {
	    if (prefixFunction[i] == P)
		System.out.print((i - (2 * P)) + " ");
	}
	System.out.println();
    }

    private static void computePrefixFunction(int[] prefixFunction, String s) {
	int L = prefixFunction.length, border = 0;
	prefixFunction[0] = 0;
	for (int i = 1; i < L; i++) {
	    while (border > 0 && s.charAt(i) != s.charAt(border))
		border = prefixFunction[border - 1];
	    border = s.charAt(i) == s.charAt(border) ? border + 1 : 0;
	    prefixFunction[i] = border;
	}
    }
}
