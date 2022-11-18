package test;

public class Vehicle implements PrettyPrintable {
	private String registrationNumber;
	private int capacity;

	public Vehicle(String regNum) {
		this(regNum, 1);
	}
	
	public Vehicle(String regNum, int cap) {
		this.registrationNumber = regNum;
		this.capacity = cap;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void print() {
		System.out.println(this.registrationNumber + ": capacity=" + this.capacity);
	}
}