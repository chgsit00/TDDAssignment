package uk.ac.brunel.ee;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTest {
	
	private Point[] getPointsExampleData() {
		Point[] points = new Point[3];
		points[0] = new Point(2.4, -2.4);
		points[1] = new Point(-0.2, 8.9);
		points[2] = new Point(-4.4, -2.4);
		return points;
	}
	
	//------------------
	// Constructor Tests
	//------------------
		
	@Test
	public void testDefaultConstructor() {
		Line line = new Line();
		assertTrue(line.length() == 0);
	}

	@Test
	public void testConstructor() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		assertEquals(3, line.length());
	}
	
	@Test
	public void testConstructorWithNull() {
		new Line(null);
	}

	//------------------
	// Length and Add Tests
	//------------------
	
	@Test
	public void testAdd() {
		Line line = new Line();
		line.add(new Point());
		assertEquals(1, line.length());
	}

	@Test
	public void testAddWithNull() {
		Line line = new Line();
		line.add(null);
		assertEquals(0, line.length());
	}
	
	public void testLength() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		assertEquals(3, line.length());
	}

	//------------------
	// equals Tests
	//------------------
	
	@Test
	public void testEqualsWithExactlySameLineObject() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		assertTrue(line.equals(line));
	}

	@Test
	public void testEqualsWithTwoLineObjectsWithSamePoints() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		Line line2 = new Line(points);
		assertTrue(line.equals(line2));
	}

	@Test
	public void testEqualsWithTwoLineObjectsWithSamePointsInDiffentOrder() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		Point[] points2 = new Point[3];
		points2[0] = new Point(2.4, -2.4);
		points2[2] = new Point(-0.2, 8.9);
		points2[1] = new Point(-4.4, -2.4);
		Line line2 = new Line(points2);
		assertTrue(line.equals(line2));
	}

	@Test
	public void testEqualsWithTwoLinesWithDifferentPoints() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		Point[] points2 = new Point[3];
		points2[0] = new Point(2.3, -1.4);
		points2[2] = new Point(-0.2, 2.9);
		points2[1] = new Point(-42.4, -2.4);
		Line line2 = new Line(points2);
		assertFalse(line.equals(line2));
	}

	@Test
	public void testEqualsWithFalseObject() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		assertFalse(line.equals(new Point(3.9, 4.2)));
	}

	//------------------
	// hashCode Tests
	//------------------
	
	@Test
	public void testHashCodeWithTrueExpected() {
		Line line = new Line();
		Line line2 = new Line();
		assertEquals(line.hashCode(), line2.hashCode());
	}

	@Test
	public void testHashCodeWithFalseExpected() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		Line line2 = new Line();
		assertNotEquals(line.hashCode(), line2.hashCode());
	}

	//------------------
	// toString Tests
	//------------------
	
	@Test
	public void testToString() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		String expectedOutout = "(( 2.4, -2.4 ),\n ( -0.2, 8.9 ),\n ( -4.4, -2.4 ))";
		assertEquals(expectedOutout, line.toString());
	}

	//------------------
	// isValid Tests
	//------------------
	
	@Test
	public void testIsValidWithZeroPoints() {
		Line line = new Line();
		assertFalse(line.isValid());
	}

	@Test
	public void testIsValidWithOnePoint() {
		Line line = new Line();
		line.add(new Point(2.1, 4.3));
		assertFalse(line.isValid());
	}

	@Test
	public void testIsValidWithManyPoints() throws RegressionFailedException {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		line.slope();
		line.intercept();
		assertTrue(line.isValid());
	}

	@Test
	public void testIsValidWithTwoPointsWithSameCoordinates() {
		Point[] points = new Point[2];
		points[0] = new Point(2.1, 2.1);
		points[1] = new Point(2.1, 2.1);
		Line line = new Line(points);
		assertFalse(line.isValid());
	}
	
	//------------------
	// Slope Tests
	//------------------
	
	@Test
	public void testSlopeWithValidPoints() throws RegressionFailedException{
		Point[] points = new Point[2];
		points[0] = new Point(0, 0);
		points[1] = new Point(1, 1);
		Line line = new Line(points);
		double slope = line.slope();
		assertEquals(1, slope, 0);
	}
	
	@Test(expected = RegressionFailedException.class)
	public void testSlopeWithInvalidPoints() throws RegressionFailedException{
		Point[] points = new Point[2];
		points[0] = new Point(0, 0);
		points[1] = new Point(0, 0);
		Line line = new Line(points);
		line.slope();
	}
	
	//------------------
	// Intercept Tests
	//------------------
	
	@Test
	public void testInterceptIWithValidPoints() throws RegressionFailedException{
		Point[] points = new Point[2];
		points[0] = new Point(0, 0);
		points[1] = new Point(1, 1);
		Line line = new Line(points);
		double intercept = line.intercept();
		assertEquals(0, intercept, 0);
	}

	@Test(expected = RegressionFailedException.class)
	public void testInterceptIWithInvalidPoints() throws RegressionFailedException{
		Point[] points = new Point[2];
		points[0] = new Point(0, 0);
		points[1] = new Point(0, 1);
		Line line = new Line(points);
		line.intercept();
	}
}
