package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class Quit implements Operation{

	@Override
	public void Operation(Database database, Scanner scan, User user) {
		System.out.println("Thanks for visiting us");
		scan.close();
		
	}

}
