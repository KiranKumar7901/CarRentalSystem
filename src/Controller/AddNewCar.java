package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.*;

public class AddNewCar implements Operation {
	
	@Override
	public void Operation(Database database,Scanner scan, User user) {
		
		System.out.println("Enter Brand Name: ");
		String brand = scan.next();
		System.out.println("Enter Model Name: ");
		String model = scan.next();
		System.out.println("Enter Color Name: ");
		String color = scan.next();
		System.out.println("Enter Year: ");
		int year = scan.nextInt();
		System.out.println("Enter Price per hour(in double): ");
		double price = scan.nextInt();
		int available = 0;
		
		try {
			ResultSet rs = database.getStatement().executeQuery("SELECT Count(*) from `cars`;");
			rs.next();
			
			int ID = rs.getInt("Count(*)");
			String insert = "INSERT INTO `cars`(`ID`, `Brand`, `Model`, `Color`, `Year`, `Price`, `Available`) VALUES ('"+ID+"','"+brand+"','"+model+"','"+color+"','"+year+"','"+price+"','"+available+"')";
			database.getStatement().execute(insert);
			System.out.println("Car Added");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
