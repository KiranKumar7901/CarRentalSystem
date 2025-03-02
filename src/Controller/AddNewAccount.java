package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class AddNewAccount implements Operation {
	
	private int accType;
	public AddNewAccount(int accType) {
		this.accType = accType;
	}
	
	@Override
	public void Operation(Database database, Scanner scan, User user) {
		System.out.println("Enter First Name: ");
		String firstName = scan.next();
		System.out.println("Enter Last Name: ");
		String lastName = scan.next();
		System.out.println("Enter Email");
		String email = scan.next();
		System.out.println("Enter Phone Number");
		String phoneNumber = scan.next();
		System.out.println("Enter Password");
		String password = scan.next();
		System.out.println("Confirm Password");
		String confirmPassword = scan.next();
		while(!password.equals(confirmPassword)) {
			System.out.println("Password doesn't match");
			System.out.println("Enter Password");
			password = scan.next();
			System.out.println("Confirm Password");
			confirmPassword = scan.next();
		}
		
		try {
			ResultSet rs = database.getStatement().executeQuery("SELECT Count(*);");
			rs.next();
			
			int ID = rs.getInt("Count(*)")-1;
			
			String insert = "Insert into `users`(`ID`,`FirstName`,`LastName`,`Email`,`PhoneNumber`,`Password`,`Type`) values ('"+ID+"','"+firstName+"','"+lastName+"','"+email+"','"+phoneNumber+"','"+password+"','"+accType+"');";
			database.getStatement().execute(insert);
			System.out.println("Account created successfully");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
