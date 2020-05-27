import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneBook {
    private static final Map<Integer, String> PHONE_BOOK = new HashMap<>();

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = Integer.valueOf(in.nextLine());
	String[] queries = new String[N];
	for (int i = 0; i < N; i++) {
	    queries[i] = in.nextLine();
	}

	for (int i = 0; i < N; i++) {
	    managePhoneBook(queries[i]);
	}
	in.close();
    }

    private static void managePhoneBook(String query) {
	String[] arr = query.split(" ");
	switch (arr[0]) {
	case "add":
	    PHONE_BOOK.put(Integer.valueOf(arr[1]), arr[2]);
	    break;
	case "del":
	    PHONE_BOOK.remove(Integer.valueOf(arr[1]));
	    break;
	case "find":
	    String name = "not found";
	    Integer key = Integer.valueOf(arr[1]);
	    if (PHONE_BOOK.containsKey(key)) {
		name = PHONE_BOOK.get(key);
	    }
	    System.out.println(name);
	    break;
	default:
	    break;
	}
    }
}
