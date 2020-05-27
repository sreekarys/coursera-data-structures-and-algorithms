import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaiveSubstringEquality {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String s = in.nextLine();
	int q = Integer.valueOf(in.nextLine());
	List<Query> queries = new ArrayList<>(q);
	for (int i = 0; i < q; i++) {
	    queries.add(new Query(in.nextLine().split(" ")));
	}

	for (Query query : queries) {
	    if (s.substring(query.a, query.a + query.l).equals(s.substring(query.b, query.b + query.l)))
		System.out.println("Yes");
	    else
		System.out.println("No");
	}
	in.close();
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
