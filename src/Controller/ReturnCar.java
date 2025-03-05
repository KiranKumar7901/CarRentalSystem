package Controller;

import java.sql.*;
import java.util.Scanner;

import Model.*;

public class ReturnCar implements Operation {

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		System.out.println("Enter Rent ID:(-1 to show all rents):");
		int ID = scan.nextInt();
		while(ID==-1) {
			new ShowUserRent(user.getID()).Operation(database, scan, user);
			System.out.println("Enter Rent ID:(-1 to show all rents):");
			ID = scan.nextInt();
		}
		
		try {
			String select = "SELECT * FROM `rents` WHERE `ID`='"+ID+"';";
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			Rent r = new Rent();
			r.setID(rs.getInt("ID"));
			r.setUser(user);
			r.setDateTime(rs.getString("DateTime"));
			r.setHours(rs.getInt("Hours"));
			r.setTotal(rs.getDouble("Total"));
			r.setStatus(rs.getInt("Status"));
			
			if(r.getStatusToString().equals("Delayed")) {
				System.out.println(r.getDelayedHours()+" delayed hours");
				System.out.println("You have to pay 1000 as fine");
			}
			
			String update = "UPDATE `rents` SET `Status`='1' WHERE `ID`='"+ID+"';";
			database.getStatement().execute(update);
			System.out.println("Car Returned Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
