import java.util.Scanner;

public class CountInversionsByMergeSort {
    private static int merge(int[] a, int left, int mid, int right) {
	int n = mid - left + 1;
	int m = right - mid;
	int[] arr1 = new int[n];
	int[] arr2 = new int[m];

	for (int i = left; i <= mid; i++) {
	    arr1[i - left] = a[i];
	}

	for (int i = mid + 1; i <= right; i++) {
	    arr2[i - mid - 1] = a[i];
	}

	int i = 0, j = 0, k = left, countInversions = 0;
	while (i < n && j < m) {
	    if (arr1[i] > arr2[j]) {
		a[k++] = arr2[j++];
		countInversions += (n - i);
	    } else {
		a[k++] = arr1[i++];
	    }
	}

	while (j < m) {
	    a[k++] = arr2[j++];
	}

	while (i < n) {
	    a[k++] = arr1[i++];
	}

	return countInversions;
    }

    private static int mergeSort(int[] a, int left, int right) {
	if (left >= right)
	    return 0;
	int mid = (left + right) / 2;
	int countInversions = 0;
	countInversions += mergeSort(a, left, mid);
	countInversions += mergeSort(a, mid + 1, right);
	countInversions += merge(a, left, mid, right);
	return countInversions;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int[] a = new int[n];
	for (int i = 0; i < n; i++) {
	    a[i] = scanner.nextInt();
	}
	System.out.println(mergeSort(a, 0, a.length - 1));
	scanner.close();
    }
}
