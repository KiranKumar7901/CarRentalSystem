package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.*;

public class ReturnCar implements Operation {

	@Override
	public void Operation(Database database, JFrame f, User user) {
		
		JFrame frame = new JFrame("Return Car");
		frame.setSize(600,260);
		frame.setLocationRelativeTo(f);
		frame.getContentPane().setBackground(new Color(250,206,27));
		frame.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Return Car",35);
		title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(2,2,15,15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new JLabel("Rent ID",22));
		String[] ids = new String[] {" "};
		ArrayList<Integer> idsArray = new ArrayList<>();
		try {
			ResultSet rs0 = database.getStatement().executeQuery("SELECT `ID` FROM `rents` WHERE `User`='"+user.getID()+"';");
			while(rs0.next()) {
				idsArray.add(rs0.getInt("ID"));
			}
			
		} catch (Exception e0) {
			JOptionPane.showMessageDialog(frame, e0.getMessage());
			frame.dispose();
		}
		
		ids = new String[idsArray.size()+1];
		ids[0]=" ";
		for(int i=1;i<=idsArray.size();i++) {
			ids[i] = String.valueOf(idsArray.get(i-1));
		}
		JComboBox id = new JComboBox(ids,22);
		panel.add(id);
		
		JButton showRents = new JButton("Show My Rents",22);
		showRents.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowUserRent(user.getID()).Operation(database, frame, user);
			}
			
		});
		panel.add(showRents);
		
		JButton confirm = new JButton("Confirm",22);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(id.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(frame, "Rent ID cannot be empty");
					return;
				}
				
				try {
					String select = "SELECT * FROM `rents` WHERE `ID`='"+id.getSelectedItem().toString()+"';";
					ResultSet rs = database.getStatement().executeQuery(select);
					rs.next();
					Rent r = new Rent();
					r.setID(rs.getInt("ID"));
					r.setUser(user);
					r.setDateTime(rs.getString("DateTime"));
					r.setHours(rs.getInt("Hours"));
					r.setTotal(rs.getDouble("Total"));
					r.setStatus(rs.getInt("Status"));
					
					if(r.getStatusToString().equals("Delayed")) {
//						System.out.println(r.getDelayedHours()+" delayed hours");
//						System.out.println("You have to pay 1000 as fine");
						JOptionPane.showMessageDialog(frame, r.getDelayedHours()+" delayed hours \nYou have to pay 1000$ as fine");
					}
					
					String update = "UPDATE `rents` SET `Status`='1' WHERE `ID`='"+id.getSelectedItem().toString()+"';";
					database.getStatement().execute(update);
//					System.out.println("Car Returned Successfully");
					JOptionPane.showMessageDialog(frame, "Car Returned Successfully");
					frame.dispose();
				} catch (SQLException e1) {
//					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			}
			
		});
		panel.add(confirm);
		
		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
		frame.requestFocus();

//		System.out.println("Enter Rent ID:(-1 to show all rents):");
//		int ID = scan.nextInt();
//		while(ID==-1) {
//			new ShowUserRent(user.getID()).Operation(database, scan, user);
//			System.out.println("Enter Rent ID:(-1 to show all rents):");
//			ID = scan.nextInt();
//		}
//		
//		
	}

}
