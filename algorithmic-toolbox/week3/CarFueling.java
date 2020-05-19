import java.util.Scanner;

public class CarFueling {
    private static int computeMinRefills(int dist, int tank, int[] stops) {
	int cities[] = new int[stops.length + 2];
	cities[0] = 0;
	for (int i = 1; i <= stops.length; i++) {
	    cities[i] = stops[i - 1];
	}
	cities[stops.length + 1] = dist;

	int N = cities.length, distanceLeftInTank = tank, cityIndex = 0, distanceTravelled = 0, minRefills = 0;
	while (cityIndex < N) {
	    while (cityIndex < N - 1 && distanceLeftInTank >= (cities[cityIndex + 1] - cities[cityIndex])) {
		distanceLeftInTank -= (cities[cityIndex + 1] - cities[cityIndex]);
		distanceTravelled = cities[cityIndex + 1];
		cityIndex++;
	    }

	    if (distanceTravelled >= dist)
		return minRefills;

	    distanceLeftInTank = tank;
	    minRefills++;
	    if (cityIndex < N - 1 && distanceLeftInTank < (cities[cityIndex + 1] - cities[cityIndex]))
		return -1;
	}

	return distanceTravelled >= dist ? minRefills : -1;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int dist = scanner.nextInt();
	int tank = scanner.nextInt();
	int n = scanner.nextInt();
	int stops[] = new int[n];
	for (int i = 0; i < n; i++) {
	    stops[i] = scanner.nextInt();
	}

	System.out.println(computeMinRefills(dist, tank, stops));
	scanner.close();
    }
}
