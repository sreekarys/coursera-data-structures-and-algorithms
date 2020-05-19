import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CoveringSegments {

    private static class Segment {
	int start, end;

	Segment(int start, int end) {
	    this.start = start;
	    this.end = end;
	}
    }

    private static int[] optimalPoints(Segment[] segments) {
	List<Segment> segmentList = Arrays.stream(segments).collect(Collectors.toList());
	Collections.sort(segmentList, Comparator.comparingInt(segment -> segment.end));
	int N = segmentList.size(), index = 1, current = segmentList.get(0).end;
	List<Integer> result = new ArrayList<Integer>();
	while (index < N) {
	    while (index < N && (current >= segmentList.get(index).start)) {
		index++;
	    }

	    result.add(current);
	    if (index < N)
		current = segmentList.get(index).end;
	    index++;
	}

	return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	Segment[] segments = new Segment[n];
	for (int i = 0; i < n; i++) {
	    int start, end;
	    start = scanner.nextInt();
	    end = scanner.nextInt();
	    segments[i] = new Segment(start, end);
	}
	int[] points = optimalPoints(segments);
	System.out.println(points.length);
	for (int point : points) {
	    System.out.print(point + " ");
	}
	scanner.close();
    }
}
