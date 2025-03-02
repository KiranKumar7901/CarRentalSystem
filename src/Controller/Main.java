package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Admin;
import Model.Client;
import Model.Database;
import Model.User;

public class Main {
	
	public static void main(String[] args) {
		Database database = new Database();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to Car Rental System!!!");
		System.out.println("Enter your Email");
		String email = scan.next();
		System.out.println("Enter your Password");
		String password = scan.next();
		
		ArrayList<User> users = new ArrayList<>();
		
		try {
			String select = "Select * from `users`;";
			ResultSet rs = database.getStatement().executeQuery(select);
			while(rs.next()) {
				User user;
				int ID = rs.getInt("ID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String em = rs.getString("Email");
				String phoneNumber = rs.getString("PhoneNumber");
				String pass = rs.getString("Password");
				int type = rs.getInt("Type");
				
				switch(type) {
					case 0:
						user = new Client();
						break;
					case 1:
						user = new Admin();
						break;
					default:
						user = new Client();
						break;
				}
				
				user.setID(ID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(em);
				user.setPhoneNumber(phoneNumber);
				user.setPassword(pass);
				users.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		for(User u : users) {
			if(u.getEmail().equals(email)&&u.getPassword().equals(password)) {
				System.out.println("Welcome "+u.getFirstName()+" !");
				u.showList(database, scan);
			}
		}
	}
}
