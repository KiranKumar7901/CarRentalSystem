package Controller;

import java.sql.*;
import java.util.*;

import Model.*;

public class ViewCars implements Operation {

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		System.out.println();
		String select = "SELECT * from `cars`;";
		ArrayList<Car> cars = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while(rs.next()) {
				Car car = new Car();
				car.setID(rs.getInt("ID"));
				car.setBrand(rs.getString("Brand"));
				car.setModel(rs.getString("Model"));
				car.setColor(rs.getString("Color"));
				car.setYear(rs.getInt("Year"));
				car.setPrice(rs.getDouble("Price"));
				car.setAvailable(rs.getInt("Available"));
				cars.add(car);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(Car c : cars) {
			if(c.getAvailable()<2) {
				System.out.println("ID: "+c.getID());
				System.out.println("Brand: "+c.getBrand());
				System.out.println("Model: "+c.getModel());
				System.out.println("Color: "+c.getColor());
				System.out.println("Year: "+c.getYear());
				System.out.println("Price: "+c.getPrice());
				if(c.getAvailable()==0) {
					System.out.println("Status: \tAvailable");
				}
				else{
					System.out.println("Status: \tNot Available");
				}
				System.out.println("---------------------------------");
			}
		}
		
	}

}
