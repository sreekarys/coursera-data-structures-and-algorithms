import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SuffixArrayConstructionBruteForce {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String s = in.next();
	List<String> suffixes = new ArrayList<>();
	for (int i = 0; i < s.length(); i++) {
	    suffixes.add(s.substring(i));
	}

	Collections.sort(suffixes);
	for (String suffix : suffixes) {
	    System.out.print((s.length() - suffix.length()) + " ");
	}
	System.out.println();
	in.close();
    }
}
