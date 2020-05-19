import java.util.Scanner;

public class FractionalKnapsack {
    private static void swap(double[] a, int i, int j) {
	double temp = a[i];
	a[i] = a[j];
	a[j] = temp;
    }

    private static void swap(int[] a, int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
    }

    private static int partition(double[] primaryArray, int[] secondaryArray, int l, int r) {
	double pivot = primaryArray[r];
	int j = l - 1;
	for (int i = l; i < r; i++) {
	    if (primaryArray[i] < pivot) {
		j++;
		swap(primaryArray, i, j);
		swap(secondaryArray, i, j);
	    }
	}

	swap(primaryArray, j, r);
	swap(secondaryArray, j, r);
	return j + 1;
    }

    private static void sort(double[] primaryArray, int[] secondaryArray, int l, int r) {
	if (l >= r)
	    return;
	int p = partition(primaryArray, secondaryArray, l, r);
	sort(primaryArray, secondaryArray, l, p - 1);
	sort(primaryArray, secondaryArray, p + 1, r);
    }

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
	int N = values.length;
	double[] valuesPerWeightUnit = new double[N];
	for (int i = 0; i < N; i++) {
	    valuesPerWeightUnit[i] = (double) values[i] / weights[i];
	}
	sort(valuesPerWeightUnit, weights, 0, N - 1);

	double value = 0;
	for (int i = 0; i < N; i++) {
	    if (capacity <= 0)
		return value;

	    int weightToBeTaken = (capacity > weights[i]) ? weights[i] : capacity;
	    capacity -= weightToBeTaken;
	    value += ((double) weightToBeTaken) * valuesPerWeightUnit[i];
	}
	return value;
    }

    public static void main(String args[]) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	int capacity = scanner.nextInt();
	int[] values = new int[n];
	int[] weights = new int[n];
	for (int i = 0; i < n; i++) {
	    values[i] = scanner.nextInt();
	    weights[i] = scanner.nextInt();
	}
	System.out.println(getOptimalValue(capacity, values, weights));
	scanner.close();
    }
}
