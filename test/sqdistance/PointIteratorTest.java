package sqdistance;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PointIteratorTest {

	@Test
	public void testReadingFirst3() {
		ArrayList<Point> expected = new ArrayList<Point>();
		expected.add(new Point(-715, 22165));
		expected.add(new Point(761, -23591));
		expected.add(new Point(-194, 6014));

		try (FileChannel ch = FileChannel.open(Paths.get("./points"))) {
			List<Point> points = Points.get(ch).limit(3).collect(java.util.stream.Collectors.toList());
			assertEquals(points.size(), expected.size());
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(points.get(i).x, expected.get(i).x);
				assertEquals(points.get(i).y, expected.get(i).y);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadingAll() {
		try (FileChannel ch = FileChannel.open(Paths.get("./points"))) {
			Points.get(ch).forEach(p -> {/*nop*/});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
