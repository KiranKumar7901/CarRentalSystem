package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class EditUserData implements Operation {

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		
		System.out.println("Enter First Name: (-1 to keep "+user.getFirstName()+")");
		String firstName = scan.next();
		if(firstName.equals("-1")) firstName = user.getFirstName();
		
		System.out.println("Enter Last Name: (-1 to keep "+user.getLastName()+")");
		String lastName = scan.next();
		if(lastName.equals("-1")) lastName = user.getLastName();
		
		System.out.println("Enter Email: (-1 to keep "+user.getEmail()+")");
		String email = scan.next();
		if(email.equals("-1")) email = user.getEmail();
		
		System.out.println("Enter Phone Number: (-1 to keep "+user.getPhoneNumber()+")");
		String phoneNumber = scan.next();
		if(phoneNumber.equals("-1")) phoneNumber = user.getPhoneNumber();
		
		String update = "UPDATE `users` SET `FirstName`='"+firstName+"',`LastName`='"+lastName+"',`Email`='"+email+"',`PhoneNumber`='"+phoneNumber+"' WHERE `ID`='"+user.getID()+"';";
		try {
			database.getStatement().execute(update);
			System.out.println("Data Updated Successfully");
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPhoneNumber(phoneNumber);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
