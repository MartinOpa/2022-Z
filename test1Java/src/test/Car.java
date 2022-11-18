package test;

public class Car extends Vehicle {
	int doorCount;

	public Car(String regNum, int cap, int doors) {
		super(regNum, cap);
		this.doorCount = doors;
	}

	public int getDoorCount() {
		return this.doorCount;
	}
	
	public void print() {
		super.print();
		System.out.println("door count=" + this.doorCount);
	}
}
