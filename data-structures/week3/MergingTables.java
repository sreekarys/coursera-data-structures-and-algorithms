import java.util.Scanner;

/*
 * O(M log**N) - Each find operation is O(log**N) and we have 2M find operations
 * O(N) extra space
 */
public class MergingTables {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt(), M = in.nextInt();
	DisjointSet disjointSet = new DisjointSet(N);
	for (int i = 0; i < N; i++) {
	    int size = in.nextInt();
	    disjointSet.makeSet(i, size);
	}

	int queries[][] = new int[M][2];
	for (int i = 0; i < M; i++) {
	    queries[i][0] = in.nextInt();
	    queries[i][1] = in.nextInt();
	}

	for (int i = 0; i < M; i++) {
	    disjointSet.union(queries[i][0] - 1, queries[i][1] - 1);
	    System.out.println(disjointSet.getMaxSize());
	}
	in.close();
    }

    static class DisjointSet {
	int[] parent, setSize, rank;
	int capacity, maxSizeOfSet;

	public DisjointSet(int capacity) {
	    this.capacity = capacity;
	    parent = new int[capacity];
	    setSize = new int[capacity];
	    rank = new int[capacity];
	}

	public void makeSet(int index, int size) {
	    parent[index] = index;
	    setSize[index] = size;
	    rank[index] = 0;
	    if (size > maxSizeOfSet)
		maxSizeOfSet = size;
	}

	public int find(int index) {
	    if (index != parent[index]) {
		parent[index] = find(parent[index]);
	    }
	    return parent[index];
	}

	public void union(int i, int j) {
	    int parentI = find(i), parentJ = find(j);
	    if (parentI == parentJ)
		return;
	    if (rank[parentI] <= rank[parentJ]) {
		parent[parentI] = parentJ;
		setSize[parentJ] += setSize[parentI];
		setSize[parentI] = 0;
		if (setSize[parentJ] > maxSizeOfSet)
		    maxSizeOfSet = setSize[parentJ];
		if (rank[parentI] == rank[parentJ])
		    rank[parentJ]++;
	    } else {
		parent[parentJ] = parentI;
		setSize[parentI] += setSize[parentJ];
		setSize[parentJ] = 0;
		if (setSize[parentI] > maxSizeOfSet)
		    maxSizeOfSet = setSize[parentI];
	    }
	}

	public int getMaxSize() {
	    return maxSizeOfSet;
	}
    }

}
