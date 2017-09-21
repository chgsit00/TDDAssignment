package uk.ac.brunel.ee;

public class Point {
	private double x;
	private double y;
	
	public Point(){
		// TODO
	}

	public Point(double x, double y){
		// TODO
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
	public boolean equals(Object o){
		//TODO
		return false;
	}
	
	@Override
	public int hashCode(){
		//TODO
		return 0;
	}
	
	@Override
	public String toString(){
		//TODO
		return "";
	}
	
	public double norm(){
		return 0.0;
	}
	
	public void rotate(double theta){
		//TODO
	}
	
	public void displace(Point p){
		//TODO
	}
}
