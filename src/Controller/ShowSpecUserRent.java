package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class ShowSpecUserRent implements Operation{

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		System.out.println("Enter User ID(int): (-1 to show all users)");
		int ID = scan.nextInt();
		while(ID==-1) {
			printUsers(database);
			System.out.println("Enter User ID(int): (-1 to show all users)");
			ID = scan.nextInt();
		}
		
		new ShowUserRent(ID).Operation(database, scan, user);
		
	}
	private void printUsers(Database database) {
		try {
			ResultSet rs = database.getStatement().executeQuery("SELECT * from `users`;");
			while(rs.next()) {
				int accType = rs.getInt("Type");
				if(accType==0) {
					System.out.println("ID: "+rs.getInt("ID"));
					System.out.println("First Name: "+rs.getString("FirstName"));
					System.out.println("Last Name: "+rs.getString("LastName"));
					System.out.println("Email: "+rs.getString("Email"));
					System.out.println("Phone Number: "+rs.getString("PhoneNumber"));
					System.out.println("--------------------------------");
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
