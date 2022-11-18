package test;

public class Plane implements PrettyPrintable {
	String type;
	double mass;
	
	public Plane(String t, double m) {
		this.type = t;
		this.mass = m;
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getMass() {
		return this.mass;
	}
	
	public void print() {
		System.out.println(this.type + ": mass=" + this.mass);
	}

	
}
