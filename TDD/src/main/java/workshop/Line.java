package workshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Line {
	private List<Point> points;
	private double slope;
	private double intercept;
	private boolean slopeDetermine = false;
	private boolean interceptDetermine = false;

	public Line() {
		points = new ArrayList<>();
	}

	public Line(Point[] plist) {
		if (null != plist) {
			points = Arrays.asList(plist);
		}
	}

	public void add(Point p) {
		if (null != p) {
			points.add(p);
			slopeDetermine = false;
			interceptDetermine = false;
		}
	}

	public int length() {
		return points.size();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Line && ((Line) o).points.size() == points.size()) {
			List<Point> otherPoints = ((Line) o).points;
			return points.containsAll(otherPoints);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(points.stream().map(p -> p.hashCode()).mapToInt(p -> p.intValue()).sum());
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(");
		for (Iterator iterator = points.iterator(); iterator.hasNext();) {
			Point point = (Point) iterator.next();
			stringBuilder.append("( " + point.getX() + ", " + point.getY() + " )");
			if (iterator.hasNext()) {
				stringBuilder.append(",\n ");
			}
		}
		stringBuilder.append(")");
		return stringBuilder.toString();
	}

	public boolean isValid() {
		if (points.size() <= 1) {
			return false;
		}
		try {
			if (!interceptDetermine) {
				intercept();
			}
			if (!slopeDetermine) {
				slope();
			}
		} catch (RegressionFailedException e) {
			return false;
		}
		return true;
	}

	private double getAverage(List<Double> values) {
		return 1.0 / values.size() * values.stream().mapToDouble(v -> v.doubleValue()).sum();
	}

	private double getXup2Average(List<Double> values) {
		return 1.0 / values.size() * values.stream().mapToDouble(v -> v.doubleValue() * v.doubleValue()).sum();
	}

	private double getXYAverage(List<Point> points) {
		return 1.0 / points.size()
				* points.stream().map(p -> p.getX() * p.getY()).mapToDouble(v -> v.doubleValue()).sum();
	}

	public double slope() throws RegressionFailedException {
		if (!slopeDetermine) {
			if (!canSlopeCalculated()) {
				throw new RegressionFailedException();
			}
			List<Double> xValues = points.stream().map(v -> v.getX()).collect(Collectors.toList());
			List<Double> yValues = points.stream().map(v -> v.getY()).collect(Collectors.toList());
			double top = getXYAverage(points) - (getAverage(xValues) * getAverage(yValues));
			double bottom = getXup2Average(xValues) - (getAverage(xValues) * getAverage(xValues));
			slope = top / bottom;
			slopeDetermine = true;
		}
		return slope;
	}

	public double intercept() throws RegressionFailedException {
		if (!interceptDetermine) {
			if (!canInterceptCalculated()) {
				throw new RegressionFailedException();
			}
			if (!slopeDetermine) {
				slope();
			}
			List<Double> xValues = points.stream().map(v -> v.getX()).collect(Collectors.toList());
			List<Double> yValues = points.stream().map(v -> v.getY()).collect(Collectors.toList());
			intercept = getAverage(yValues) - (slope * getAverage(xValues));
			interceptDetermine = true;
		}
		return intercept;
	}

	private boolean canSlopeCalculated() {
		Point point = points.get(0);
		for (Iterator iterator = points.iterator(); iterator.hasNext();) {
			Point point1 = (Point) iterator.next();
			if (!point.equals(point1)) {
				return true;
			}
		}
		return false;
	}

	private boolean canInterceptCalculated() {
		Point point = points.get(0);
		for (Iterator iterator = points.iterator(); iterator.hasNext();) {
			Point point1 = (Point) iterator.next();
			if (point.getX() != point1.getX()) {
				return true;
			}
		}
		return false;
	}

}
