package uk.ac.brunel.ee;

import java.util.Objects;

public class Point {
	private double x;
	private double y;

	public Point() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Point) {
			Point p = (Point) o;
			return (p.x == this.x && p.y == this.y);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("( ");
		stringBuilder.append(this.x);
		stringBuilder.append(", ");
		stringBuilder.append(this.y);
		stringBuilder.append(" )");
		return stringBuilder.toString();
	}

	public double norm() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public void rotate(double theta) throws AngleOutOfRangeException {
		if (theta < -180.0 || theta > 180.0) {
			throw new AngleOutOfRangeException();
		}
		double newX = this.x * Math.cos(theta) - this.y * Math.sin(theta);
		double newY = this.x * Math.sin(theta) + this.y * Math.cos(theta);
		this.x = newX;
		this.y = newY;
	}

	public void displace(Point p) {
		this.x+=p.x;
		this.y+=p.y;
	}
}
