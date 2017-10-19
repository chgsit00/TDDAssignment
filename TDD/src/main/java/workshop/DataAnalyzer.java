// Example of how to use the lineRead package to read the
// data file.
// The file consists of a number of lines. Each line is defined by
// a number of points. The points are x and y co-ordinates.
// There are a variable number of points including 1 point for
// which a fit is not possible.
// This file provides a working example of reading the file.
// There is no requirement that your programme should follow this
// template.

package workshop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.brunel.ee.RereadException;
import uk.ac.brunel.ee.UnreadException;
import uk.ac.brunel.ee.lineRead;

public class DataAnalyzer {
	private static int validLines = 0;
	private static int invalidLines = 0;
	private static double numberOfPoints = 0;
	private static List<Double> allSlopes;
	private static List<Double> allIntercepts;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataAnalyzer dataAnalyzer = new DataAnalyzer();
		List<Line> lines = dataAnalyzer.getAllLinesWithAllPoints("data_long.dat");
		// start needed to analyze the processing time
		Date start = new Date();
		// counting valid und invalid lines as well as all slopes and intercepts
		dataAnalyzer.getInformations(lines);
		// prints the needed information
		dataAnalyzer.printInformations();
		// calculating the proccessing time++++
		Date end = new Date();
		long begin = start.getTime();
		long fin = end.getTime();
		System.out.println("processing time is " + (fin - begin) + " milliseconds");
		// ++++++++++++++++++++++++++++++++++++++
	}

	// PrintInformations-Method
	// calculates the last informations and prints all needed data
	private void printInformations() {
		System.out.println("Total Lines: " + (validLines + invalidLines));
		System.out.println("Valid Lines: " + validLines);
		System.out.println("Invalid Lines: " + invalidLines);
		System.out.println("Average Points per valid Line: " + (numberOfPoints / validLines));
		System.out.println("Average Slope per valid Line: " + calculateMean(allSlopes));
		System.out.println("Standard deviation of slope: " + calculateDeviation(allSlopes));
		System.out.println("Average y-Intercept per valid Line: " + calculateMean(allIntercepts));
		System.out.println("Standard deviation of intercept: " + calculateDeviation(allIntercepts));

	}

	// CalculateMean-Method
	private double calculateMean(List<Double> list) {
		return list.stream().mapToDouble(p -> p.doubleValue()).sum() / list.size();
	}

	// CalculateDeviation-Method
	private double calculateDeviation(List<Double> list) {
		double temp = 0;
		for (Double double1 : list) {
			temp += Math.pow((double1.doubleValue() - calculateMean(list)), 2);
		}
		return Math.sqrt(temp / list.size());
	}

	// GetInformations-Method
	// gathers all slopes and intercepts as well as the valid and invalid lines
	private void getInformations(List<Line> lines) {
		allIntercepts = new ArrayList<>();
		allSlopes = new ArrayList<>();
		for (Line line : lines) {
			if (line.isValid()) {
				validLines++;
				numberOfPoints += line.length();
				try {
					allSlopes.add(line.slope());
					allIntercepts.add(line.intercept());
				} catch (RegressionFailedException e) {
					e.printStackTrace();
				}
			} else {
				invalidLines++;
			}
		}
	}

	// GetAllLinesWithAllPoints-Method
	// gets the path of the file which should be read in. This method uses the
	// provided library.
	private List<Line> getAllLinesWithAllPoints(String path) {
		List<Line> lines = new ArrayList<>();
		double x, y;
		// Map that saves the number of points of a line and the time to read in these
		// points.
		Map<Integer, Long> time = new HashMap<>();
		// date to measure the complete read in time
		Date start = new Date();

		// Open the file and initialise
		lineRead reader = new lineRead(path);

		// Loop over all the lines in the data set
		while (reader.nextLine()) {
			// lineStart is used to analyze the time needed to read in the points of every
			// line
			Date lineStart = new Date();
			Line line = new Line();
			boolean np = true;
			// Loop over all the points associated with the current line
			while (np) {
				try {
					np = reader.nextPoint();
				} catch (UnreadException UE) {
					System.out.println(UE);
					System.exit(0);
				}
				// If there is another point read it.
				if (np) {
					try {
						x = reader.getX();
						y = reader.getY();
						line.add(new Point(x, y));
					} catch (RereadException RE) {
						System.out.println(RE);
						System.exit(0);
					}
				}

			}
			lines.add(line);
			// calculating the time to read in the line and the length of the line
			Date lineEnd = new Date();
			long lineTime = lineEnd.getTime() - lineStart.getTime();
			int length = line.length();
			long long1 = time.get(length) == null ? lineTime : (time.get(length) + lineTime) / 2;
			// store the two values in the map
			time.put(length, long1);
		}
		// Sort out the summary of the run
		Date end = new Date();
		long begin = start.getTime();
		long fin = end.getTime();
		System.out.println("read time is " + (fin - begin) + " milliseconds");
		// print the points and the time to analyze the time per point
		List<Integer> list = new ArrayList<>(time.keySet());
		Collections.sort(list);
		for (Integer integer : list) {
			System.out.println(integer + " : " + time.get(integer) + " ms");
		}
		return lines;
	}
}
