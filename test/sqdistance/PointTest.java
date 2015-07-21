package sqdistance;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testNorm() {
		Point a = new Point(10, 10);
		Point b = new Point(10, 20);
		assertEquals(a.norm(b), 10, 0);
		assertEquals(a.norm2(b), 100, 0);
	}

}
