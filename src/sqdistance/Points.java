package sqdistance;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utility class to read points off a binary file
 * 
 * @author ulf
 */
public class Points {

	/**
	 * Return a stream of Points from a FileChannel object. Internally it will
	 * mem map the file channel and read using a ShortBuffer. The returned
	 * Stream is never parallel.
	 * 
	 * @param channel
	 * @return a stream of Point instances
	 * @throws IOException
	 */
	public static Stream<Point> get(FileChannel channel) throws IOException {
		return StreamSupport.stream(new PointIterator(channel), false);
	}

}

/**
 * A simple Spliterator that read from a ShortBuffer provided by a FileChannel.
 * 
 * @author ulf
 *
 */
class PointIterator extends Spliterators.AbstractSpliterator<Point> {

	private ShortBuffer pointsBuffer;

	private int items;
	private int index;

	private final static int itemByteSize = 2;

	/**
	 * @param channel
	 * @param itemSize
	 *            is the size in bytes of each encoded item
	 * @throws IOException
	 */
	public PointIterator(FileChannel channel) throws IOException {
		super(channel.size() / (2 * itemByteSize), SIZED | Spliterator.IMMUTABLE | Spliterator.NONNULL);
		ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		pointsBuffer = buffer.asShortBuffer();
		items = (int) channel.size() / (2 * itemByteSize);
	}

	@Override
	public boolean tryAdvance(Consumer<? super Point> action) {
		if (index >= items) {
			return false;
		}
		Point p = new Point(pointsBuffer.get(), pointsBuffer.get());
		action.accept(p);
		index++;
		return true;
	}

}