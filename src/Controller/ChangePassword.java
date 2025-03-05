package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class ChangePassword implements Operation{

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		
		System.out.println("Enter Old Password: ");
		String oldPassword = scan.next();
		if(!oldPassword.equals(user.getPassword())) {
			System.out.println("Password doesn't match");
			return;
		}
		
		System.out.println("Enter New Password: ");
		String newPassword = scan.next();
		System.out.println("Confirm Password: ");
		String confirmPassword = scan.next();
		while(!newPassword.equals(confirmPassword)) {
			System.out.println("Enter New Password: ");
			newPassword = scan.next();
			System.out.println("Confirm Password: ");
			confirmPassword = scan.next();	
		}
		
		try {
			String update = "UPDATE `users` SET `Password`='"+newPassword+"' where `ID`='"+user.getID()+"';";
			System.out.println("Password Changed Successfully");
			user.setPassword(newPassword);
			database.getStatement().execute(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
