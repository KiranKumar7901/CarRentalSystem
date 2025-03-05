package Model;

import java.util.Scanner;

import Controller.*;

public class Client extends User {
	
	private Operation[] operation = new Operation[] {new ViewCars(),
			new RentCar(),
			new ReturnCar(),
			new ShowUserRent(2),
			new EditUserData(),
			new ChangePassword(),
			new Quit()
			};
	
	public Client() {
		super();
	}
	
	@Override
	public void showList(Database database, Scanner scan) {
		
		System.out.println("Welcome Client!");
		System.out.println("\n1. View Cars");
		System.out.println("2. Rent Car");
		System.out.println("3. Return Car");
		System.out.println("4. Show My Rents");
		System.out.println("5. Edit Data");
		System.out.println("6. Change Password");
		System.out.println("7. Quit");
		
		int i = scan.nextInt();
		if(i<1 || i>7) {
			showList(database,scan);
			return;
		}
		operation[i-1].Operation(database, scan, this);
		if(i!=7) showList(database,scan);
	}
}
