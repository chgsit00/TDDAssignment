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
			points = Arrays.asList(plist).stream().filter(p -> p != null).collect(Collectors.toList());
		} else {
			points = new ArrayList<>();
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
			return points.containsAll(otherPoints) && otherPoints.containsAll(points);
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
		for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
			Point point = iterator.next();
			stringBuilder.append(point.toString());
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

	public double slope() throws RegressionFailedException {
		if (!slopeDetermine) {
			if (!canSlopeCalculated()) {
				throw new RegressionFailedException();
			}
			slope = calculateSlope();
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
			intercept = calculateIntercept();
		}
		return intercept;
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

	private double calculateSlope() {
		List<Double> xValues = points.stream().map(v -> v.getX()).collect(Collectors.toList());
		List<Double> yValues = points.stream().map(v -> v.getY()).collect(Collectors.toList());
		double top = getXYAverage(points) - (getAverage(xValues) * getAverage(yValues));
		double bottom = getXup2Average(xValues) - (getAverage(xValues) * getAverage(xValues));
		slopeDetermine = true;
		return (top / bottom);
	}

	private double calculateIntercept() {
		List<Double> xValues = points.stream().map(v -> v.getX()).collect(Collectors.toList());
		List<Double> yValues = points.stream().map(v -> v.getY()).collect(Collectors.toList());
		interceptDetermine = true;
		return (getAverage(yValues) - (slope * getAverage(xValues)));
	}

	private boolean canSlopeCalculated() {
		Point point = points.get(0);
		for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
			Point point1 = iterator.next();
			if (!point.equals(point1)) {
				return true;
			}
		}
		return false;
	}

	private boolean canInterceptCalculated() {
		Point point = points.get(0);
		for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
			Point point1 = iterator.next();
			if (point.getX() != point1.getX()) {
				return true;
			}
		}
		return false;
	}

}
