package sqdistance;

/**
 * Point is a class to hold a simple 2D point. No guarentees given for overflow
 * on norm functions
 * 
 * @author ulf
 *
 */
public final class Point {

	public final int x;
	public final int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Euclidean distance between this point and a.
	 * 
	 * @param a
	 * @return the distance between the two points
	 */
	public double norm(Point a) {
		return Math.sqrt((double)(this.x - a.x) * (this.x - a.x) + (this.y - a.y) * (this.y - a.y));
	}

	/**
	 * Euclidean distance between this point and a. Squared.
	 * 
	 * @param a
	 * @return the squared distance between the two points
	 */
	public double norm2(Point a) {
		return (double) (this.x - a.x) * (this.x - a.x) + (this.y - a.y) * (this.y - a.y);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
