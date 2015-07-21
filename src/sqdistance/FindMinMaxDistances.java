package sqdistance;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * Main entry point to compute the closest 10 and furthest 20 points from
 * (-200,300) and (1000,25) respectevely. Assumes a file points is located in
 * the same directory the program is run from
 * 
 * It will print the points to stdout
 * 
 * @author ulf
 *
 */
public class FindMinMaxDistances {

	private Point closestTo = new Point(-200, 300);
	private Point furthestFrom = new Point(1000, 25);

	public static void main(String[] args) throws IOException {
		new FindMinMaxDistances().run();
	}

	public void run() throws IOException {

		long t = System.currentTimeMillis();

		PointDistanceQueue closest = new PointDistanceQueue(closestTo, 10, Double::compare);
		PointDistanceQueue furthest = new PointDistanceQueue(furthestFrom, 20,
				(Double o1, Double o2) -> Double.compare(o1, o2) * -1);

		try (FileChannel ch = FileChannel.open(Paths.get("./points"))) {
			// add all items to queues
			Points.get(ch).forEach(p -> {
				closest.add(p);
				furthest.add(p);
			});
		}
		long total = System.currentTimeMillis() - t;

		System.out.println("Closest points to " + closestTo);
		for (PointAndDistance pointAndDistance : closest.getList()) {
			System.out.println(pointAndDistance.point + " - norm=" + pointAndDistance.distance);
		}
		System.out.println("Furthest points from " + furthestFrom);
		for (PointAndDistance pointAndDistance : furthest.getList()) {
			System.out.println(pointAndDistance.point + " - norm=" + pointAndDistance.distance);
		}
		System.out.println("Done in " + (total) + " ms");
	}
}