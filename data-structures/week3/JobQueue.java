import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 * O(M logN) time : M - #jobs, N = #threads
 * O(N) extra space
 */
public class JobQueue {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt(), M = in.nextInt();
	Queue<Long> q = new LinkedList<>();
	for (int i = 0; i < M; i++) {
	    q.add(in.nextLong());
	}

	PriorityQueue<MyThread> pq = new PriorityQueue<>(N,
		Comparator.comparing(MyThread::getTime).thenComparing(MyThread::getId));
	int min = M > N ? N : M;
	for (int i = 0; i < min; i++) {
	    System.out.println(i + " " + 0);
	    pq.add(new MyThread(i, q.poll()));
	}

	while (!q.isEmpty()) {
	    MyThread thread = pq.poll();
	    System.out.println(thread.getId() + " " + thread.getTime());
	    pq.add(new MyThread(thread.getId(), thread.getTime() + q.poll()));
	}
	in.close();
    }

    static class MyThread {
	private int id;
	private long time;

	public MyThread(int id, long time) {
	    this.id = id;
	    this.time = time;
	}

	public int getId() {
	    return id;
	}

	public long getTime() {
	    return time;
	}
    }
}
