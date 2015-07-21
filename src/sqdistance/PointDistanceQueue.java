package sqdistance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A priority like queue with a max size. Elements are ordered using a
 * comparator by their distance to a reference point. Any element added after
 * max size is reached causes the last (according to comparator) to be dropped.
 * 
 * @author ulf
 *
 */
public class PointDistanceQueue {

	private final List<PointAndDistance> q;
	private final int size;
	private final Comparator<Double> comparator;
	private final Point reference;

	/**
	 * @param reference
	 *            The reference point from which distances are computed
	 * @param size
	 *            the size of the queue
	 * @param comparator
	 *            A comparator for the distance, the natural ordering is a min
	 *            queue.
	 */
	public PointDistanceQueue(Point reference, int size, Comparator<Double> comparator) {
		this.q = new ArrayList<PointAndDistance>(size + 1);
		this.size = size;
		this.reference = reference;
		this.comparator = comparator;
	}

	/**
	 * Add an element to the queue
	 * 
	 * @param p
	 *            the element to add
	 */
	public void add(Point p) {
		double dist = p.norm(reference);
		int j = 0;
		for (j = 0; j < q.size(); j++) {
			PointAndDistance c = q.get(j);
			if (comparator.compare(dist, c.distance) < 0) {
				break;
			}
		}
		q.add(j, new PointAndDistance(p, dist));
		if (q.size() == size + 1) {
			q.remove(size);
		}
	}

	/**
	 * Get the list of points contained in the queue
	 * 
	 * @return the points and distances in the queue
	 */
	public List<PointAndDistance> getList() {
		return q;
	}

}
