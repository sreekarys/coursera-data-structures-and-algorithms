import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Acyclicity {
    enum COLOR {
	GREY("the vertex is under processing"), WHITE("the vertex is yet to be processed"),
	BLACK("the vertex is already processed");

	private String description;

	private COLOR(String description) {
	    this.description = description;
	}
    }

    private static int acyclic(List<List<Integer>> adj) {
	int V = adj.size();
	COLOR[] colorOfVertices = new COLOR[V];
	Arrays.fill(colorOfVertices, COLOR.WHITE);
	for (int i = 0; i < V; i++) {
	    if (colorOfVertices[i] == COLOR.WHITE && hasCycles(i, adj, colorOfVertices))
		return 1;

	}
	return 0;
    }

    private static boolean hasCycles(int x, List<List<Integer>> adj, COLOR[] colorOfVertices) {
	if (colorOfVertices[x] == COLOR.GREY)
	    return true;

	colorOfVertices[x] = COLOR.GREY;
	for (Integer neighbourX : adj.get(x)) {
	    if (colorOfVertices[neighbourX] == COLOR.GREY)
		return true;
	    if (colorOfVertices[neighbourX] == COLOR.WHITE && hasCycles(neighbourX, adj, colorOfVertices))
		return true;
	}
	colorOfVertices[x] = COLOR.BLACK;
	return false;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int m = scanner.nextInt();
	List<List<Integer>> adj = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    adj.add(new ArrayList<>());
	}

	for (int i = 0; i < m; i++) {
	    int x, y;
	    x = scanner.nextInt();
	    y = scanner.nextInt();
	    adj.get(x - 1).add(y - 1);
	}
	System.out.println(acyclic(adj));
	scanner.close();
    }
}
