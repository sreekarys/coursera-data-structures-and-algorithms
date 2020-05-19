import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LargestNumber {
    private static Comparator<String> getComparator() {
	return Collections.reverseOrder(new Comparator<String>() {
	    @Override
	    public int compare(String o1, String o2) {
		String s1 = o1 + o2;
		String s2 = o2 + o1;
		return s1.compareTo(s2);
	    }
	});
    }

    private static String largestNumber(String[] a) {
	List<String> numberList = Arrays.stream(a).collect(Collectors.toList());
	Collections.sort(numberList, getComparator());

	StringBuilder s = new StringBuilder();
	numberList.stream().forEach(number -> s.append(number));
	return s.toString();
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	String[] a = new String[n];
	for (int i = 0; i < n; i++) {
	    a[i] = scanner.next();
	}
	System.out.println(largestNumber(a));
	scanner.close();
    }
}
