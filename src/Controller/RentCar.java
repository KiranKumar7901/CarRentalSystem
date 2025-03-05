package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class RentCar implements Operation {

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		
		System.out.println("Enter Car ID(int): (-1 to show all cars");
		int carID = scan.nextInt();
		
		while(carID==-1) {
			new ViewCars().Operation(database, scan, user);
			System.out.println("Enter Car ID(int): (-1 to show all cars");
			carID = scan.nextInt();
		}
		
		System.out.println("Enter hours(int):");
		int hours = scan.nextInt();
		
		try {			
			ResultSet rs0 = database.getStatement().executeQuery("SELECT * from `cars` where `ID`='"+carID+"';");
			rs0.next();
			Car car = new Car();
			car.setID(rs0.getInt("ID"));
			car.setBrand(rs0.getString("Brand"));
			car.setModel(rs0.getString("Model"));
			car.setColor(rs0.getString("Color"));
			car.setYear(rs0.getInt("Year"));
			car.setPrice(rs0.getInt("Price"));
			car.setAvailable(rs0.getInt("Available"));
			if(car.getAvailable()!=0) {
				System.out.println("Car is not available");
				return;
			}
			ResultSet rs1 = database.getStatement().executeQuery("SELECT Count(*) from `rents`;");
			rs1.next();
				
			int ID = rs1.getInt("Count(*)");	
			
			double total = car.getPrice()*hours;
			Rent rent = new Rent();
			
			String insert = "INSERT INTO `rents`(`ID`, `User`, `Car`, `DateTime`, `Hours`, `Total`, `Status`) VALUES ('"+ID+"','"+user.getID()+"','"+car.getID()+"','"+rent.getDateTime()+"','"+hours+"','"+total+"','0')";
			database.getStatement().execute(insert);
			System.out.println("Car Rented Successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
