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

import Controller.*;

public class Client extends User {
	
	private Operation[] operation = new Operation[] {
			new ViewCars(),
			new RentCar(),
			new ReturnCar(),
			new ShowUserRent(2),
			new EditUserData(),
			new ChangePassword()
//			new Quit()
			};
	
	private JButton[] btns = new JButton[] {
			new JButton("View Cars",22),
			new JButton("Rent Car",22),
			new JButton("Return Car",22),
			new JButton("Show User Rent",22),
			new JButton("Edit User Data",22),
			new JButton("Change Password",22)
	};
	
	public Client() {
		super();
	}
	
	@Override
	public void showList(Database database, JFrame f) {
		
		JFrame frame = new JFrame("Client Panel - Welcome "+getFirstName());
		frame.setSize(400,btns.length*90);
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
					operation[j].Operation(database, frame, Client.this);
					
				}
				
			});
		}
		
		frame.add(panel,BorderLayout.NORTH);
		frame.setVisible(true);
		
//		System.out.println("Welcome Client!");
//		System.out.println("\n1. View Cars");
//		System.out.println("2. Rent Car");
//		System.out.println("3. Return Car");
//		System.out.println("4. Show My Rents");
//		System.out.println("5. Edit Data");
//		System.out.println("6. Change Password");
//		System.out.println("7. Quit");
		
//		int i = scan.nextInt();
//		if(i<1 || i>7) {
//			showList(database,scan);
//			return;
//		}
//		operation[i-1].Operation(database, scan, this);
//		if(i!=7) showList(database,scan);
	}
}
