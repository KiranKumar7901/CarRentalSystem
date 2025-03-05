package Controller;

import java.util.*;
import java.sql.*;

import Model.*;

public class UpdateCar implements Operation 
{

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		
		System.out.println("Enter car ID(int): (-1 to show all cars");
		int ID = scan.nextInt();
		while(ID==-1) {
			new ViewCars().Operation(database, scan, user);
			System.out.println("Enter car ID(int): (-1 to show all cars");
			ID = scan.nextInt();			
		}
		
		try {
			ResultSet rs1 = database.getStatement().executeQuery("SELECT * from `cars` where `ID`='"+ID+"';");
			rs1.next();
			Car car = new Car();
			car.setID(rs1.getInt("ID"));
			car.setBrand(rs1.getString("Brand"));
			car.setModel(rs1.getString("Model"));
			car.setColor(rs1.getString("Color"));
			car.setYear(rs1.getInt("Year"));
			car.setPrice(rs1.getInt("Price"));
			car.setAvailable(rs1.getInt("Available"));
			if(car.getAvailable()>1) {
				System.out.println("Car doesn't exist");
				return;
			}
			
			System.out.println("Enter Brand: (-1: "+car.getBrand()+" )");
			String brand = scan.next();
			if(brand.equals("-1")) brand = car.getBrand();
			
			System.out.println("Enter Model: (-1: "+car.getModel()+" )");
			String model = scan.next();
			if(model.equals("-1")) model = car.getModel();
			
			System.out.println("Enter Color: (-1: "+car.getColor()+" )");
			String color = scan.next();
			if(color.equals("-1")) color = car.getColor();
			
			System.out.println("Enter Year: (-1: "+car.getYear()+" )");
			int year = scan.nextInt();
			if(year==-1) year = car.getYear();
			
			System.out.println("Enter Price: (-1: "+car.getPrice()+" )");
			double price = scan.nextDouble();
			if(price==-1) price = car.getPrice();
			
			String update = "UPDATE `cars` SET `Brand`='"+brand+"',`Model`='"+model+"',`Color`='"+color+"',`Year`='"+year+"',`Price`='"+price+"' WHERE `ID`='"+ID+"';";	
			database.getStatement().execute(update);
			System.out.println("Car Updated Successfully");
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		
	}

}
