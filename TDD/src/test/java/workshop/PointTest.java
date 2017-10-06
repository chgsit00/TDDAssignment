package workshop;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

	// ------------------
	// Default Constructor Tests
	// ------------------
	@Test
	public void testDefaultConstructor() {
		Point point = new Point();
		Assert.assertEquals(0.0, point.getX(), 0);
		Assert.assertEquals(0.0, point.getY(), 0);
	}

	// ------------------
	// Default Constructor Tests
	// ------------------
	@Test
	public void testAlternateConstructor() {
		double x = 1.7;
		double y = 2.7;
		Point point = new Point(x, y);
		Assert.assertEquals(x, point.getX(), 0);
		Assert.assertEquals(y, point.getY(), 0);
	}

	// ------------------
	// Getter and Setter Tests
	// ------------------
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

	// ------------------
	// Equals Test
	// ------------------
	@Test
	public void testEqualsSamePoints() {
		Point point1 = new Point(1.7, 2.7);
		Assert.assertTrue(point1.equals(point1));
	}
	
	@Test
	public void testEqualsDifferentPointsWithSameCoordinates() {
		Point point1 = new Point(1.7, 2.7);
		Point point2 = new Point(1.7, 2.7);
		Assert.assertTrue(point1.equals(point2));
	}

	@Test
	public void testEqualsDifferentPoints() {
		Point point1 = new Point(1.7, 3.7);
		Point point2 = new Point(1.7, 2.7);
		Assert.assertFalse(point1.equals(point2));
	}

	@Test
	public void testEqualsWithOnePointNull() {
		Point point1 = new Point(1.7, 3.7);
		Point point2 = null;
		Assert.assertFalse(point1.equals(point2));
	}

	// ------------------
	// HashCode Tests
	// ------------------
	@Test
	public void testHashCodeSamePoints() {
		Point point1 = new Point(1.7, 2.7);
		Point point2 = new Point(1.7, 2.7);
		Assert.assertEquals(point1.hashCode(), point2.hashCode());
	}

	@Test
	public void testHashCodeDifferentPoints() {
		Point point1 = new Point(1.7, 3.7);
		Point point2 = new Point(1.7, 2.7);
		Assert.assertNotEquals(point1.hashCode(), point2.hashCode());
	}

	// ------------------
	// ToString Test
	// ------------------
	@Test
	public void testToString() {
		Point point = new Point(1.7, 2.7);
		Assert.assertEquals("test the toString-Methode", "( +1.7000E+00, +2.7000E+00 )", point.toString());
	}

	// ------------------
	// Norm Test
	// ------------------
	@Test
	public void testNorm() {
		Point point = new Point(1.7, 2.7);
		double distance = 3.1906112267087634;
		Assert.assertEquals(distance, point.norm(), 0);
	}

	@Test
	public void testNormWithOneMinus() {
		Point point = new Point(-1.7, 2.7);
		double distance = 3.1906112267087634;
		Assert.assertEquals(distance, point.norm(), 0);
	}

	@Test
	public void testNormWithTwoMinus() {
		Point point = new Point(-1.7, -2.7);
		double distance = 3.1906112267087634;
		Assert.assertEquals(distance, point.norm(), 0);
	}

	// ------------------
	// Rotate Tests
	// ------------------
	@Test(expected = AngleOutOfRangeException.class)
	public void testRotateWithException() throws AngleOutOfRangeException {
		Point point = new Point(3.5, 0);
		// max rotation is +/-180 above/under that an AngleOutOfRangeException should
		// occur.
		point.rotate(200);
	}

	@Test
	public void testRotatePlus90() throws AngleOutOfRangeException {
		Point point = new Point(3.5, 0);
		point.rotate(90);
		double x = 0;
		double y = 3.5;
		Assert.assertEquals(x, point.getX(), 0);
		Assert.assertEquals(y, point.getY(), 0);
	}

	@Test
	public void testRotateMinus90() throws AngleOutOfRangeException {
		Point point = new Point(3.5, 0);
		point.rotate(-90);
		double x = 0;
		double y = -3.5;
		Assert.assertEquals(x, point.getX(), 0);
		Assert.assertEquals(y, point.getY(), 0);
	}

	// ------------------
	// Displace Tests
	// ------------------
	@Test
	public void testDisplaceRight() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(1.3, 0));
		double x = 3;
		double y = 2.7;
		Assert.assertEquals(Math.ulp(x), Math.ulp(point.getX()), 0);
		Assert.assertEquals(Math.ulp(y), Math.ulp(point.getY()), 0);
	}

	@Test
	public void testDisplaceLeft() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(-1.3, 0));
		double x = 0.4;
		double y = 2.7;
		Assert.assertEquals(Math.ulp(x), Math.ulp(point.getX()), 0);
		Assert.assertEquals(Math.ulp(y), Math.ulp(point.getY()), 0);
	}

	@Test
	public void testDisplaceTop() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(0, 2.3));
		double x = 1.7;
		double y = 5;
		Assert.assertEquals(Math.ulp(x), Math.ulp(point.getX()), 0);
		Assert.assertEquals(Math.ulp(y), Math.ulp(point.getY()), 0);
	}

	@Test
	public void testDisplaceDown() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(0, -2.3));
		double x = 1.7;
		double y = 0.4;
		Assert.assertEquals(Math.ulp(x), Math.ulp(point.getX()), 0);
		Assert.assertEquals(Math.ulp(y), Math.ulp(point.getY()), 0);
	}

	@Test
	public void testDisplaceTopRight() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(1.3, 2.3));
		double x = 3;
		double y = 5;
		Assert.assertEquals(Math.ulp(x), Math.ulp(point.getX()), 0);
		Assert.assertEquals(Math.ulp(y), Math.ulp(point.getY()), 0);
	}

	@Test
	public void testDisplaceDownLeft() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(-1.3, -2.3));
		double x = 0.4;
		double y = 0.4;
		Assert.assertEquals(Math.ulp(x), Math.ulp(point.getX()), 0);
		Assert.assertEquals(Math.ulp(y), Math.ulp(point.getY()), 0);
	}

	@Test
	public void testDisplaceWithoutChange() {
		Point point = new Point(1.7, 2.7);
		point.displace(new Point(0, 0));
		double x = 1.7;
		double y = 2.7;
		Assert.assertEquals(Math.ulp(x), Math.ulp(point.getX()), 0);
		Assert.assertEquals(Math.ulp(y), Math.ulp(point.getY()), 0);
	}
}
