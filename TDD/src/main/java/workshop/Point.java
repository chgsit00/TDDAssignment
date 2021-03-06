package workshop;

import java.awt.geom.AffineTransform;
import java.util.Locale;
import java.util.Objects;

public class Point {
	private double x;
	private double y;

	public Point() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public Point(double x, double y) {
		assert (!Double.isNaN(x) && !Double.isNaN(y));
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		assert (!Double.isNaN(x));
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		assert (!Double.isNaN(y));
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		// Easiest case
		if (o == this) {
			return true;
		}
		// if the given object isn't a point, there is no need for further
		// operations
		if (o instanceof Point) {
			Point p = (Point) o;
			// Make use of the hashCode method
			if (p.hashCode() == this.hashCode()) {
				return true;
			}
			return (p.x == this.x && p.y == this.y);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// Hash should represent the point's coordinates
		// So two points with the same coordinates should generate the same
		// hash-value
		return Objects.hash(this.x, this.y);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("( ");
		stringBuilder.append(String.format(Locale.ROOT, "%+2.4E", this.x));
		stringBuilder.append(", ");
		stringBuilder.append(String.format(Locale.ROOT, "%+2.4E", this.y));
		stringBuilder.append(" )");
		return stringBuilder.toString();
	}

	public double norm() {
		// Pythagoras
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public void rotate(double theta) throws AngleOutOfRangeException {
		// theta must be an number
		if (Double.isNaN(theta) || theta < -180.0 || theta > 180.0) {
			throw new AngleOutOfRangeException();
		}
		double[] pt = {this.x, this.y};
		AffineTransform.getRotateInstance(Math.toRadians(theta), 0, 0)
		  .transform(pt, 0, pt, 0, 1); // specifying to use this double[] to hold coords
		double newX = pt[0];
		double newY = pt[1];
		this.x = newX;
		this.y = newY;
	}

	public void displace(Point p) {
		if(p == null){
			return;
		}
		this.x += p.x;
		this.y += p.y;
	}
}
