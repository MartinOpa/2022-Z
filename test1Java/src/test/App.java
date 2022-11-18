package test;

import java.util.Random;

public class App {
	public static void main(String[] args) {	
		Vehicle veh1 = new Vehicle("1TJ3268");
		Vehicle veh2 = new Vehicle("1TB8476", 5);
		
		Random random = new Random();
		
		Vehicle Vehicle1 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle2 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle3 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle4 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle5 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle6 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle7 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle8 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle9 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		Vehicle Vehicle10 = new Vehicle("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1);
		
		Car Car1 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car2 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car3 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car4 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car5 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car6 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car7 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car8 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car9 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		Car Car10 = new Car("vsb-"+Integer.toString(random.nextInt(3000)+1000), random.nextInt(99)+1, random.nextInt(3)+2);
		
		Plane Plane1 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane2 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane3 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane4 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane5 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane6 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane7 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane8 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane9 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		Plane Plane10 = new Plane("Airbus-A"+Integer.toString(random.nextInt(320)+480), random.nextDouble(577000));
		
		PrettyPrintable[] array = {Vehicle1, Car1, Plane1, Vehicle2, Car2, Plane2, Vehicle3, Car3, Plane3, 
				Vehicle4, Car4, Plane4, Vehicle5, Car5, Plane5, Vehicle6, Car6, Plane6, Vehicle7, Car7, Plane7,
				Vehicle8, Car8, Plane8, Vehicle9, Car9, Plane9, Vehicle10, Car10, Plane10};
		
		for (int i = 0; i < array.length; i++) {
			  array[i].print();
			  System.out.println("\n");
			}
		
		System.exit(0);
	}
}
