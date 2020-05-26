import java.util.Arrays;
import java.util.stream.Collectors;

public class HeapSortMain {

    public static void main(String[] args) throws Exception {
	int A[] = new int[50];
	for (int i = 49; i >= 0; i--) {
	    A[49 - i] = i + 1;
	}

	System.out.println(Arrays.stream(A).boxed().collect(Collectors.toList()));
//	HeapSortImpl.sort(A);
	HeapSortImpl.sortInplace(A);
	System.out.println(Arrays.stream(A).boxed().collect(Collectors.toList()));
    }

}
