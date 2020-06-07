import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SuffixArrayConstructionOptimized {

    private static final int ALPHABET_LENGTH = 5;
    private static final Map<String, Integer> ALPHABET_MAPPING = new HashMap<String, Integer>() {
	private static final long serialVersionUID = 1L;
	{
	    put("$", 0);
	    put("A", 1);
	    put("C", 2);
	    put("G", 3);
	    put("T", 4);
	}
    };

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String input = in.next();
	buildSuffixArray(input);
	in.close();
    }

    private static void buildSuffixArray(String input) {
	int[] order = buildOrder(input);
	int[] equivalenceClass = buildEquivalenceClass(input, order);
	int L = 1;
	while (L < input.length()) {
	    order = updateOrder(order, equivalenceClass, L);
	    equivalenceClass = updateEquivalenceClass(order, equivalenceClass, L);
	    L *= 2;
	}

	for (int i = 0; i < order.length; i++) {
	    System.out.print(order[i] + " ");
	}
	System.out.println();
    }

    private static int[] buildOrder(String input) {
	int[] order = new int[input.length()], count = new int[ALPHABET_LENGTH];
	for (int i = 0; i < input.length(); i++)
	    count[ALPHABET_MAPPING.get(String.valueOf(input.charAt(i)))]++;
	for (int i = 1; i < ALPHABET_LENGTH; i++)
	    count[i] += count[i - 1];

	for (int i = input.length() - 1; i >= 0; i--) {
	    int start = ALPHABET_MAPPING.get(String.valueOf(input.charAt(i)));
	    count[start]--;
	    order[count[start]] = i;
	}
	return order;
    }

    private static int[] buildEquivalenceClass(String input, int[] order) {
	int[] equivalenceClass = new int[order.length];
	int currClass = 0;
	equivalenceClass[order[0]] = 0;
	for (int i = 1; i < order.length; i++) {
	    if (input.charAt(order[i]) != input.charAt(order[i - 1]))
		currClass++;
	    equivalenceClass[order[i]] = currClass;
	}
	return equivalenceClass;
    }

    private static int[] updateOrder(int[] order, int[] equivalenceClass, int L) {
	int[] updatedOrder = new int[order.length], count = new int[order.length];
	for (int i = 0; i < order.length; i++)
	    count[equivalenceClass[i]]++;
	for (int i = 1; i < order.length; i++)
	    count[i] += count[i - 1];

	for (int i = order.length - 1; i >= 0; i--) {
	    int start = (order[i] - L + order.length) % order.length;
	    count[equivalenceClass[start]]--;
	    updatedOrder[count[equivalenceClass[start]]] = start;
	}
	return updatedOrder;
    }

    private static int[] updateEquivalenceClass(int[] updatedOrder, int[] equivalenceClass, int L) {
	int currClass = 0, S = updatedOrder.length;
	int[] updatedEquivalenceClass = new int[S];
	updatedEquivalenceClass[updatedOrder[0]] = 0;
	for (int i = 1; i < S; i++) {
	    int curr = updatedOrder[i], prev = updatedOrder[i - 1];
	    int mid = (curr + L) % S, midPrev = (prev + L) % S;
	    if (equivalenceClass[curr] != equivalenceClass[prev]
		    || equivalenceClass[mid] != equivalenceClass[midPrev]) {
		currClass++;
	    }
	    updatedEquivalenceClass[curr] = currClass;
	}
	return updatedEquivalenceClass;
    }
}
