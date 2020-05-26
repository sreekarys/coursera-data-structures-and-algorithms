
/*
 * makeSet(): O(1)
 * find(i): using path compression heuristic, O(log*n) amortized
 * union(i, j) : using union by rank, O(log*n) amortized 
 * 	as union consists of 2 find operations and some constant time operations
 */
public class DisjointSet {
    int[] parent, rank;
    int capacity;

    public DisjointSet(int capacity) {
	this.capacity = capacity;
	parent = new int[capacity];
	rank = new int[capacity];
    }

    public void makeSet(int index) {
	parent[index] = index;
	rank[index] = 0;
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
	    if (rank[parentI] == rank[parentJ])
		rank[parentJ]++;
	} else
	    parent[parentJ] = parentI;
    }
}
