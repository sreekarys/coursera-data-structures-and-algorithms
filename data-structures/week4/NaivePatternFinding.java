import java.util.Scanner;

public class NaivePatternFinding {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String p = in.next(), t = in.next();
	int L = p.length(), N = t.length();
	for (int i = 0; i <= N - L; i++) {
	    String s = t.substring(i, i + L);
	    if (s.equals(p)) {
		System.out.print(i + " ");
	    }
	}
	System.out.println();
	in.close();
    }
}
