package Controller;

import java.sql.*;
import java.util.*;

import Model.*;

public class DeleteCar implements Operation {

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		System.out.println("Enter ID: (-1 to show all cars");
		int ID = scan.nextInt();
		while(ID==-1) {
			new ViewCars().Operation(database, scan, user);
			System.out.println("Enter ID: (-1 to show all cars");
			ID = scan.nextInt();
		}
		try {
			String update = "UPDATE `Cars` SET `Available`='2' where `ID`='"+ID+"';";
			database.getStatement().execute(update);
			System.out.println("Car Deleted Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
