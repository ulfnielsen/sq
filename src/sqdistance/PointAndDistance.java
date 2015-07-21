package sqdistance;

/**
 * Utility class to hold a point and a distance to some point
 * 
 * @author ulf
 *
 */
public class PointAndDistance {
	final Point point;
	final double distance;

	public PointAndDistance(Point p, double distance) {
		this.point = p;
		this.distance = distance;
	}
}
