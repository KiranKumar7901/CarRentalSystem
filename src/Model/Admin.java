package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.AddNewAccount;
import Controller.AddNewCar;
import Controller.ChangePassword;
import Controller.DeleteCar;
import Controller.EditUserData;
import Controller.ShowAllRents;
import Controller.ShowSpecUserRent;
import Controller.UpdateCar;
import Controller.ViewCars;

public class Admin extends User{
	
	private Operation[] operations = new Operation[] {
			new AddNewCar(),
			new ViewCars(),
			new UpdateCar(),
			new DeleteCar(),
			new AddNewAccount(1),
			new AddNewAccount(0),
			new ShowAllRents(),
			new ShowSpecUserRent(),
			new EditUserData(),
			new ChangePassword()
//			new Quit()
			};
	
	private JButton[] btns = new JButton[] {
		new JButton("Add New Car",22),
		new JButton("View Cars",22),
		new JButton("Update Cars",22),
		new JButton("Delete Cars",22),
		new JButton("Add New Admin",22),
		new JButton("Add New Client",22),
		new JButton("Show Rents",22),
		new JButton("Show User's Rent",22),
		new JButton("Edit User Data",22),
		new JButton("Change Password",22)
	};
	
	public Admin() {
		super();
	}
	
	@Override
	public void showList(Database database, JFrame f) {
		
		JFrame frame = new JFrame("Admin Panel - Welcome "+getFirstName());
		frame.setSize(400,600);
		frame.setLocationRelativeTo(f);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());	
		
		JPanel panel = new JPanel(new GridLayout(btns.length,1,15,15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		
		for(int i=0;i<btns.length;i++) {
			final int j=i;
			JButton button = btns[i];
			panel.add(button);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					operations[j].Operation(database, frame, Admin.this);
					
				}
				
			});
		}
		
		frame.add(panel,BorderLayout.NORTH);
		frame.setVisible(true);
		
		
		
//		System.out.println("\n01. Add New Car");
//		System.out.println("02. View Cars");
//		System.out.println("03. Update Car");
//		System.out.println("04. Delete Car");
//		System.out.println("05. Add New Admin");
//		System.out.println("06. Show Rents");
//		System.out.println("07. Show User Rents");
//		System.out.println("08. Edit My Data");
//		System.out.println("09. Change Password");
//		System.out.println("10. Quit");
		
//		int i = scan.nextInt();
//		if(i<1 || i>10) {
//			showList(database,f);
//			return;
//		}
//		operations[i-1].Operation(database,f,this);
//		if(i!=10) showList(database,f);
	}
}
