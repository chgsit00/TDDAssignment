package uk.ac.brunel.ee;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

	@Test
	public void testDefaultConstructor() {
		Point point = new Point();
		Assert.assertEquals(0.0, point.getX(), 0);
		Assert.assertEquals(0.0, point.getY(), 0);
	}

	@Test
	public void testAlternateConstructor() {
		double x = 1.7;
		double y = 2.7;
		Point point = new Point(x, y);
		Assert.assertEquals(x, point.getX(), 0);
		Assert.assertEquals(y, point.getY(), 0);
	}

	@Test
	public void testGetSetX() {
		double x = 1.7;
		Point point = new Point();
		Assert.assertEquals(0.0, point.getX(), 0);
		point.setX(x);
		Assert.assertEquals(x, point.getX(), 0);
	}

	@Test
	public void testGetSetY() {
		double y = 1.7;
		Point point = new Point();
		Assert.assertEquals(0.0, point.getY(), 0);
		point.setY(y);
		Assert.assertEquals(y, point.getY(), 0);
	}

	@Test
	public void testEquals() {
		Point point1 = new Point(1.7, 2.7);
		Point point2 = new Point(1.7, 2.7);
		Assert.assertTrue(point1.equals(point2));
		Point point3 = new Point(1.7, 3.7);
		Point point4 = new Point(1.7, 2.7);
		Assert.assertFalse(point3.equals(point4));
	}

	@Test
	public void testHashCode() {
		Point point1 = new Point(1.7, 2.7);
		Point point2 = new Point(1.7, 2.7);
		Assert.assertTrue(point1.hashCode() == point2.hashCode());
		Point point3 = new Point(1.7, 3.7);
		Point point4 = new Point(1.7, 2.7);
		Assert.assertFalse(point3.hashCode() == point4.hashCode());
	}

	@Test
	public void testToString() {
		Point point = new Point(1.7, 2.7);
		Assert.assertEquals("test the toString-Methode", "( 1.7, 2.7 )", point.toString());
	}

	@Test
	public void testNorm() {
		Point point = new Point(1.7, 2.7);
		double distance = 3.1906112267087634;
		Assert.assertTrue(distance == point.norm());
	}

	@Test(expected = AngleOutOfRangeException.class)
	public void testRotateWithException() throws AngleOutOfRangeException {
		Point point = new Point(3.5, 0);
		point.rotate(200);
	}

	public void testRotatePlus90() throws AngleOutOfRangeException {
		Point point = new Point(3.5, 0);
		point.rotate(90);
		double x = 0;
		double y = 3.5;
		Assert.assertEquals(x, point.getX(), 0);
		Assert.assertEquals(y, point.getY(), 0);
	}

	public void testRotateMinus90() throws AngleOutOfRangeException {
		Point point = new Point(3.5, 0);
		point.rotate(-90);
		double x = 0;
		double y = -3.5;
		Assert.assertEquals(x, point.getX(), 0);
		Assert.assertEquals(y, point.getY(), 0);
	}

	@Test
	public void testDisplace() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(1.3, 2.3));
		double x = 3;
		double y = 5;
		Assert.assertEquals(x, point.getX(), 0);
		Assert.assertEquals(y, point.getY(), 0);
	}

}
