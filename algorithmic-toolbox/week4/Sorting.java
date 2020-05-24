import java.util.Random;
import java.util.Scanner;

public class Sorting {
    private static Random random = new Random();

    private static void swap(int[] a, int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
    }

    private static int[] partition3(int[] a, int l, int r) {
	int pivot = a[l], m1 = l + 1, m2 = r;
	for (int i = l; i <= r; i++) {
	    if (a[i] < pivot) {
		swap(a, m1, i);
		m1++;
	    }
	}

	for (int i = l; i <= r; i++) {
	    if (a[i] > pivot) {
		swap(a, m2, i);
		m2--;
	    }
	}

	int[] m = { m1, m2 };
	return m;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
	if (l >= r) {
	    return;
	}
	int k = random.nextInt(r - l + 1) + l;
	int t = a[l];
	a[l] = a[k];
	a[k] = t;
	int[] m = partition3(a, l, r);
	randomizedQuickSort(a, l, m[0] - 1);
	randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int[] a = new int[n];
	for (int i = 0; i < n; i++) {
	    a[i] = scanner.nextInt();
	}
	randomizedQuickSort(a, 0, n - 1);
	for (int i = 0; i < n; i++) {
	    System.out.print(a[i] + " ");
	}
	scanner.close();
    }

}
