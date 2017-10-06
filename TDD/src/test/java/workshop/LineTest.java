package workshop;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTest {
	
	//-----------------------------------------------
	// generates some points for different tests
	//-----------------------------------------------
	
	private Point[] getPointsExampleData() {
		Point[] points = new Point[3];
		points[0] = new Point(2.4, -2.4);
		points[1] = new Point(-0.2, 8.9);
		points[2] = new Point(-4.4, -2.4);
		return points;
	}
	
	//-----------------------------------------------
	// Constructor Tests
	//-----------------------------------------------
		
	@Test
	public void testDefaultConstructor() {
		Line line = new Line();
		assertTrue(line.length() == 0);
	}

	@Test
	public void testAlternateConstructor() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		assertEquals(3, line.length());
	}
	
	@Test
	public void testAlternateConstructorWithOneNullObject() {
		Point[] points = getPointsExampleData();
		points[0] = null;
		Line line = new Line(points);
		assertEquals(2, line.length());
	}
	
	@Test
	public void testConstructorWithNull() {
		Line line = new Line(null);
		assertTrue(line.length() == 0);
	}

	//-----------------------------------------------
	// length() and add() Tests
	//-----------------------------------------------
	
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

	public void testLengthAfterAdd() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		assertEquals(3, line.length());
		line.add(new Point(2.3543, 8.345));
		assertEquals(4, line.length());
	}
	
	//-----------------------------------------------
	// equals() Tests
	//-----------------------------------------------
	
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
		// comparing a Line object to a Point object should return false...
		assertFalse(line.equals(new Point(3.9, 4.2)));
	}

	@Test
	public void testEqualsWithNull() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		assertFalse(line.equals(null));
	}
	
	//-----------------------------------------------
	// hashCode() Tests
	//-----------------------------------------------
	
	@Test
	public void testHashCodeWithoutPoints() {
		Line line = new Line();
		Line line2 = new Line();
		assertEquals(line.hashCode(), line2.hashCode());
	}

	@Test
	public void testHashCodeWithSamePoints() {
		Line line = new Line(getPointsExampleData());
		Line line2 = new Line(getPointsExampleData());
		assertEquals(line.hashCode(), line2.hashCode());
	}
	
	@Test
	public void testHashCodeWithDifferentPoints() {
		Point[] points = getPointsExampleData();
		Line line = new Line(points);
		Line line2 = new Line();
		assertNotEquals(line.hashCode(), line2.hashCode());
	}

	//-----------------------------------------------
	// toString() Tests
	//-----------------------------------------------
	
	@Test
	public void testToString() {
		Point[] points = new Point[3];
		points[0] = new Point(0.00000021, 12345.1246);
		points[1] = new Point(-0.2556785, 1.1246365);
		points[2] = new Point(-235.25853467, -1.1246);
		Line line = new Line(points);
		String expectedOutout = "(( +2.1000E-07, +1.2345E+04 ),\n ( -2.5568E-01, +1.1246E+00 ),\n ( -2.3526E+02, -1.1246E+00 ))";
		assertEquals(expectedOutout, line.toString());
	}

	//-----------------------------------------------
	// isValid() Tests
	//-----------------------------------------------
	
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
	
	@Test
	public void testIsValidWithTwoPointsWithSameXCoordinates() {
		Point[] points = new Point[2];
		points[0] = new Point(2.1, 23.34);
		points[1] = new Point(2.1, 21.23);
		Line line = new Line(points);
		assertFalse(line.isValid());
	}
	//-----------------------------------------------
	// Slope Tests
	//-----------------------------------------------
	
	@Test
	public void testSlopeWithTwoValidPoints() throws RegressionFailedException{
		// very simple tests
		Point[] points = new Point[2];
		points[0] = new Point(0, 0);
		points[1] = new Point(1, 1);
		Line line = new Line(points);
		double slope = line.slope();
		assertEquals(1, slope, 0);
	}
	
	@Test
	public void testSlopeWithValidPoints() throws RegressionFailedException{
		// more complex parameters
		Point[] points = new Point[6];
		points[0] = new Point(0.2, 0);
		points[1] = new Point(1, 3.12);
		points[2] = new Point(3.23, 1);
		points[3] = new Point(-1.5, 1.3);
		points[4] = new Point(4.9, 12.2);
		points[5] = new Point(3.32, 2);
		Line line = new Line(points);
		double slope = line.slope();
		assertEquals(1.2, slope, 0.03);
	}
	
	@Test(expected = RegressionFailedException.class)
	public void testSlopeWithSamePoints() throws RegressionFailedException{
		Point[] points = new Point[2];
		points[0] = new Point(0, 0);
		points[1] = new Point(0, 0);
		// these points have the same coordinates. 
		// So the slope can't be calculated with these two.
		Line line = new Line(points);
		line.slope();
	}
	
	//-----------------------------------------------
	// Intercept Tests
	//-----------------------------------------------
	
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
		// with these points the line lies on the y-axis. therefore slope can't be calculated. 
		// And without slope, there is no intercept.
		Line line = new Line(points);
		line.intercept();
	}
}
