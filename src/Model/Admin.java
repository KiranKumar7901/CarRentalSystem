package Model;

import java.util.Scanner;

import Controller.AddNewAccount;

public class Admin extends User{
	
	private Operation[] operations = new Operation[] {new AddNewAccount(1)};
	public Admin() {
		super();
	}
	
	@Override
	public void showList(Database database, Scanner scan) {
		System.out.println("\n1. Add New Car");
		System.out.println("2. View Cars");
		System.out.println("3. Update Car");
		System.out.println("4. Delete Car");
		System.out.println("5. Add New Admin");
		System.out.println("6. Show Rents");
		System.out.println("7. Quit");
		
		int i = scan.nextInt();
		operations[0].Operation(database,scan,this);
	}
}
